package com.server.gustaveeiffel.upem.Dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import com.server.gustaveeiffel.upem.datasource.IDatabase;
import com.common.gustaveeiffel.upem.Demande;
import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.Image;
import com.common.gustaveeiffel.upem.Notification;
import com.common.gustaveeiffel.upem.Produit;

public class ProduitDaoJDBC  implements ProduitDao,Serializable {

	private IDatabase db;

	public ProduitDaoJDBC() throws RemoteException {
		Iconfig launcher= new Config();

		this.db= launcher.dbinit();
	}

	@Override
	public boolean insertProduit(Produit p,List<Image> images) throws RemoteException {
		System.out.println(p);
		boolean verifierstatus=true;
		int dispo=0;
		if(p.getEstDispo()) {
			dispo=1;
		}
		if(images!=null) {
			for(Image image: images) {
				if(!insererImage(Config.getImagePath(),image.getNomfichier(),p.getProduitId())) verifierstatus=false;
			}
		}
		else if (images==null) {
			if(!insererImage(Config.getImagePath(),Config.default_image_name,p.getProduitId())) verifierstatus=false;

		}
		
		if(db.Insert("produit",p.getProduitId(),p.getNomProduit(),p.getNote(),p.getCommentaire(),
				p.getNombreEmprunt(),p.getTypeProduit()
				,p.getUtilisateurId(),dispo,p.getDateAjout())<=0) verifierstatus= false;
	return verifierstatus;	// si true tout est bien
	}



	public List<Produit> chercherProduitparMotcle(String motcle)  throws RemoteException{
		String data[][] =db.selectLike("produit","nom", motcle);
		if (data == null) return null;
		List<Produit> produits = new Vector<>();
		for (int i = 1; i < data.length; i++) {
			produits.add(new Produit(data[i]));
		}
		return produits;
	}


	public boolean supprimerProduit(String id) throws RemoteException{
		if( db.Delete("Produit","produitid" , id)==0) return false; 
		return true;
	}



	public List<Produit> listeProduits() throws RemoteException{
		String data[][] = db.select("Produit");
		if (data == null) return null;
		List<Produit> produits = new Vector<>();

		for (int i = 1; i < data.length; i++) {
			produits.add( new Produit(data[i]));
		}
		return produits;
	}


	public boolean restituer(Produit p, IUtilisateur u) {
		//on doit savoir si l'utilisateur possedait le produit 
		return false;
	}

	@Override
	public boolean modifierProduit(int id,Produit p) throws RemoteException{

		try {
			if(db.Update("produit",id,p.getNomProduit(),p.getNote(),p.getCommentaire(),p.getNombreEmprunt(),p.getTypeProduit()
					,p.getUtilisateurId(),p.getEstDispo(),p.getDateAjout())>0) return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Produit chercherProduitParId(int id)  throws RemoteException{
		String data[][] = db.select("Produit","produitid",id);
		Produit p = null;
		if(data[1]==null) return null;
		else {
			p = new Produit(data[1]);
		}
		return p;
	}




	@Override
	public List<Demande> listeDemandes(int produitid) throws RemoteException {
		String data[][] = db.select("demande","produitid",produitid);
		List<Demande> demandes = new Vector<Demande>();
		if(data==null) return null;
		else for(int i=1;i<data.length;i++) {
			demandes.add(new Demande(data[i]));
		}
		return demandes;
	}

	@Override
	public boolean insererDemande(Demande d) throws RemoteException{
		try {
			if(db.Insert("demande",null,d.getDatedemande(),d.getUtilisateurid(),d.getProduitid(),d.getPriorite())>0) return true;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean modifierDemande(int produit, int utilisateurid, int priorite) throws RemoteException{
		if(db.UpdateQuery("update demande set priorite="+priorite+" where utilisateurid="+utilisateurid+" and produitid="+produit+"")>0) {
			return true;
		}
		return false;

	}

	@Override
	public Demande recupererDemande(int produitid, int utilisateurid)throws RemoteException {
		String data[][] = db.executeQuery("select * from demande where utilisateurid= "+utilisateurid
				+" and produitid="+produitid);
		Demande d = new Demande();
		if(data==null) return null;
		else{
			try {
				d = new Demande(data[1]);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			return d;

		}
	}


	@Override
	public boolean Emprunter(int produitid, int utilisateurid,Date dateRetour) throws RemoteException {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		if(db.Insert("emprunt",null,date,dateRetour,produitid,utilisateurid)>0)return true;
		return false;
	}

	@Override
	public boolean insererproduitImage(int produitid, String chemin, String nomfichier) throws RemoteException {
		if(db.Insert("images",null,chemin,nomfichier,produitid)>0)return true;
		return false;
	}

	@Override
	public List<Image> chargerImages(int produitid) throws RemoteException {
		String data[][] = db.select("images","produitid",produitid);
		List<Image> listImages = new Vector<Image>();
		if(data==null)return null;  
		else for(int i=1;i<data.length;i++) {
			listImages.add(new Image(data[i]));
		}
		return listImages;

	}


	public  boolean insererImage(String chemin,String nom,int produitid) throws RemoteException {
		if(db.Insert(null,chemin,nom,produitid)>0) return true;
       return false;


	}

	@Override
	public Demande demandePriorite(int produitid) throws RemoteException {
		String data[][] = db.select("demande","priorite",0);
		if(data==null) return null;
		else return new Demande(data[1]);
	}
	@Override
	public void declarerPriorite(int produitid) throws RemoteException{
		db.executeQuery("update demande set priorite= priorite-1 where produitid="+produitid);
	}

	public boolean inserernotification(String notification,int utilid,int produitid) throws RemoteException {
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        if(db.Insert("notification",null,notification,utilid,date,0,produitid)>0) return true;
		return false;
	}

	@Override
	public Demande chercherDemandeParUtilisateur(int utilid, int produitid) throws RemoteException {
		String[][] data =  db.select2criteria("demande", "utilisateurid", utilid, "produitid",produitid);
		if(data==null) return null;
		else return new Demande(data[1]);
	}

	



}
