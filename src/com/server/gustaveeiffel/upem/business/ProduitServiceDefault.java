package com.server.gustaveeiffel.upem.business;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import com.common.gustaveeiffel.upem.Demande;
import com.common.gustaveeiffel.upem.IRole;
import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.ProduitService;
import com.common.gustaveeiffel.upem.Role;
import com.common.gustaveeiffel.upem.Utilisateur;
import com.server.gustaveeiffel.upem.Dao.ProduitDao;
import com.server.gustaveeiffel.upem.Dao.ProduitDaoJDBC;
import com.server.gustaveeiffel.upem.Dao.RoleDao;
import com.server.gustaveeiffel.upem.Dao.RoleDaoJDBC;
import com.server.gustaveeiffel.upem.Dao.UtilisateurDao;
import com.server.gustaveeiffel.upem.Dao.UtilisateurDaoJDBC;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class ProduitServiceDefault implements ProduitService,Serializable {

	private ProduitDao dao;
	private UtilisateurDao utildao;
	private RoleDao roledao;
	public ProduitServiceDefault() throws RemoteException {
		dao = new ProduitDaoJDBC();
		utildao = new UtilisateurDaoJDBC();
		roledao = new RoleDaoJDBC();
	}

	@Override
	public boolean Ajouter(Produit p) throws RemoteException {
		if(dao.insertProduit(p)) return true;
		return false;
	}

	@Override
	public boolean Emprunter(int produitid, int utilisateurid) throws RemoteException {
		
		Produit p= dao.chercherProduitParId(produitid);
		if(p==null) return false;

		else {
			if(!p.getEstDispo()) {
				ajouterEnAttente(p,utilisateurid);
				return true;
			}
			else {
			}
		}
		return false;
	}

	@Override
	public boolean deleteProduct(String id) throws RemoteException{
		if(!dao.supprimerProduit(id)) return false;
		return true;
	}

	@Override
	public List<Produit> listeProduit() throws RemoteException{
		return dao.listeProduits();
	}

	@Override
	public List<Produit> chercherProduitParcle(String motcle) throws RemoteException{
		return dao.chercherProduitparMotcle(motcle);
	}


	@Override
	public void restituer(Produit p, Utilisateur u) throws RemoteException{

	}

	@Override
	public boolean modifierProduit(int id,Produit p) throws RemoteException{
		if(dao.modifierProduit(id,p)) return true;
		return false;
	}

	@Override
	public Produit chercherProduitParId(int id) throws RemoteException{
		return dao.chercherProduitParId(id);
	}

	public Utilisateur MapUtilisateurRole(int utilisateurid) throws RemoteException{
		Utilisateur utilisateur =(Utilisateur) utildao.chercherUtilisateurParId(utilisateurid);
		Role r = roledao.chercherRoleParId(utilisateur.getRoleid());
		utilisateur.setRole(r);
		return utilisateur;
	}

	public void sauvegarderPriorite(List<Utilisateur> utilisateurs,int produitid)throws RemoteException{
			
		for(int i=0;i<utilisateurs.size();i++) { 
			modifierDemande(produitid,utilisateurs.get(i).getUserid(), i);

		}
	}
	public List<Utilisateur> trierParNbEnprunts(List<Utilisateur> utilisateurs)throws RemoteException {
		try {
			utilisateurs.sort(Comparator.comparing(t -> {
				try {
					return t.getNombreEmprunt();
				} catch (RemoteException e) {
					e.printStackTrace();
					return null;
				}
			}));
		}
		catch(Exception e) {
			return null;
		}
		return utilisateurs;
	}

	@Override
	public List<Demande> listeDemandes(int produitid)throws RemoteException{
		return dao.listeDemandes(produitid);
	}

	@Override
	public void ajouterEnAttente(Produit p, int utilisateurid) throws RemoteException {
		Utilisateur utilisateurActuel =(Utilisateur)MapUtilisateurRole(utilisateurid);
		List<Utilisateur> listeEnseignants=new Vector<>();
		List<Utilisateur> listeEtudiants = new Vector<>();
		List<Demande> listedemandes = listeDemandes(p.getProduitId());
		if(listedemandes==null) {
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			Demande demande = new Demande(date, utilisateurActuel, p, 1);
			ajouterDemande(demande,p.getProduitId(),utilisateurActuel.getUserid());
		}
		if(listedemandes != null) {
			for(Demande d: listedemandes) {  
				Utilisateur userEnAttente =(Utilisateur)MapUtilisateurRole(d.getUtilisateurid());
				if(userEnAttente.getRole().getNomRole().equals("Enseignant")) {
					listeEnseignants.add(userEnAttente);
				}
				else if(userEnAttente.getRole().getNomRole().equals("Etudiant")) {
					listeEtudiants.add(userEnAttente);
				}
			}
			if(utilisateurActuel.getRole().getNomRole().equals("Enseignant") && listeEnseignants!=null && listeEtudiants!=null ) {
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Demande demande = new Demande(date, utilisateurActuel, p, 1);
				ajouterDemande(demande,p.getProduitId(), utilisateurActuel.getUserid());
				listeEnseignants.add(utilisateurActuel);
				List<Utilisateur> utilisateursTrie = trierParNbEnprunts(listeEnseignants);
				List<Utilisateur> listeEtudiantsTrie = trierParNbEnprunts(listeEtudiants);
				for(Utilisateur u : listeEtudiantsTrie) {
					utilisateursTrie.add(u);
				}
				sauvegarderPriorite(utilisateursTrie, p.getProduitId());
			}
			if(utilisateurActuel.getRole().getNomRole().equals("Etudiant") && listeEtudiants!=null &&listeEnseignants==null) {
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Demande demande = new Demande(date, utilisateurActuel, p, 1);
				ajouterDemande(demande,p.getProduitId(), utilisateurActuel.getUserid());
				listeEtudiants.add(utilisateurActuel);
				List<Utilisateur> EtudiantsTrie = trierParNbEnprunts(listeEtudiants);
				sauvegarderPriorite(EtudiantsTrie,p.getProduitId());
			}

			if(utilisateurActuel.getRole().getNomRole().equals("Etudiant") && listeEnseignants!=null && listeEtudiants==null ) {
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Demande demande = new Demande(date, utilisateurActuel, p, 1);
				ajouterDemande(demande,p.getProduitId(), utilisateurActuel.getUserid());// 0
				List<Utilisateur> enseignantstrie = trierParNbEnprunts(listeEnseignants);
				enseignantstrie.add(utilisateurActuel);
				sauvegarderPriorite(enseignantstrie, p.getProduitId());
			}

			if(utilisateurActuel.getRole().getNomRole().equals("Etudiant") && listeEnseignants!=null && listeEtudiants!=null ) {
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Demande demande = new Demande(date, utilisateurActuel, p, 1);
				ajouterDemande(demande,p.getProduitId(), utilisateurActuel.getUserid());
				listeEtudiants.add(utilisateurActuel);
				List<Utilisateur> utilisateursTrie = trierParNbEnprunts(listeEnseignants);
				List<Utilisateur> listeEtudiantsTrie = trierParNbEnprunts(listeEtudiants);
				for(Utilisateur u : listeEtudiantsTrie) {
					utilisateursTrie.add(u);
				}
				sauvegarderPriorite(utilisateursTrie, p.getProduitId());

			}
			if(utilisateurActuel.getRole().getNomRole().equals("Enseignant") && listeEtudiants==null &&listeEnseignants!=null) {
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Demande demande = new Demande(date, utilisateurActuel, p, 1);
				ajouterDemande(demande,p.getProduitId(), utilisateurActuel.getUserid());
				listeEnseignants.add(utilisateurActuel);
				List<Utilisateur> EnseignantTrie = trierParNbEnprunts(listeEnseignants);
				sauvegarderPriorite(EnseignantTrie,p.getProduitId());
			}
			if(utilisateurActuel.getRole().getNomRole().equals("Enseignant") && listeEnseignants==null && listeEtudiants!=null) {
				java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				Demande d = new Demande(date,utilisateurActuel,p,1);
				ajouterDemande(d, p.getProduitId(),utilisateurActuel.getUserid());
				List<Utilisateur> listeetudiantsTrie = trierParNbEnprunts(listeEtudiants);
				for(int i=0;i<listedemandes.size();i++) {
					modifierDemande(listedemandes.get(i).getProduit().getProduitId(),
							listeetudiantsTrie.get(i).getUserid() , listedemandes.get(i).getPriorite()+1);
				}
			}

		}
	}
	@Override
	public boolean ajouterDemande(Demande d,int produitid,int utilisateurid) throws RemoteException{
		System.out.println("In AjouterDemande function :"+ produitid);
		Produit p = dao.chercherProduitParId(produitid);

		if(p!=null) {
			Utilisateur u = utildao.chercherUtilisateurParId(utilisateurid);
			d.setProduitid(p.getProduitId());
			d.setUtilisateurid(u.getUserid());
			return dao.insererDemande(d);
		}
		else return false;
	}
	@Override
	public boolean modifierDemande(int produit, int utilisateurid, int priorite) throws RemoteException {
		return dao.modifierDemande(produit, utilisateurid, priorite);
	}


}
/*Lors de l'emprunt d'un utilisateur on doit savoir 
 *si'il est on top veut dire c'est a lui d'emprunter 
 *sinon on lui notifie qu'il reste dans la liste d'attente 
 *et notifie on meme temps l'utilisateur qui doit l'avoir pour le r�cuperer
 */

/*
 *recuperer le produit 
 * on chercher si y'a pas de demande sur le produit (estDispo false)
 *on emprunte pour cet utilisateur 
 *sinon on regarde qui est dans la liste d'attente
 *
 *
 */
