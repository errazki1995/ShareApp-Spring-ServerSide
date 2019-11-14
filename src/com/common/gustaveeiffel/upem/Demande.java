package com.common.gustaveeiffel.upem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Demande extends UnicastRemoteObject implements Serializable{
	private int demandeid;
	private Date datedemande;
	private int utilisateurid;
	private Utilisateur utilisateur;
	private int produitid;
	private Produit produit;
	private int priorite;


	public Demande() throws RemoteException{

	}
	public Demande( Date datedemande, Utilisateur utilisateur,
			Produit produit, int priorite)throws RemoteException {
		super();

		this.datedemande = datedemande;
		this.utilisateurid = utilisateur.getUserid();
		this.utilisateur = utilisateur;
		this.produitid = produit.getProduitId();
		this.produit = produit;
		this.priorite = priorite;

	}
	//recuperer avec id

	public Demande(int demandeid, Date datedemande, Utilisateur utilisateur,
			Produit produit, int priorite)throws RemoteException {
		super();

		this.demandeid = demandeid;
		this.datedemande = datedemande;
		this.utilisateurid = utilisateur.getUserid();
		this.utilisateur = utilisateur;
		this.produitid = produit.getProduitId();
		this.produit = produit;
		this.priorite = priorite;

	}


	public Demande(String ...row) throws RemoteException{
		this.demandeid=toInt(row[0]);
		this.utilisateurid = toInt(row[2]);
		this.produitid= toInt(row[3]);
		this.priorite= toInt(row[4]);
		try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(row[1]);
			this.datedemande=new Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}


	}

	public  int toInt(String s)throws RemoteException {
		try {
			return Integer.parseInt(s);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public int getDemandeid()throws RemoteException {
		return demandeid;
	}


	public void setDemandeid(int demandeid)throws RemoteException {
		this.demandeid = demandeid;
	}


	public Date getDatedemande() throws RemoteException{
		return datedemande;
	}


	public void setDatedemande(Date datedemande)throws RemoteException {
		this.datedemande = datedemande;
	}


	public int getUtilisateurid() throws RemoteException{
		return utilisateurid;
	}


	public void setUtilisateurid(int utilisateurid) throws RemoteException{
		this.utilisateurid = utilisateurid;
	}


	public Utilisateur getUtilisateur() throws RemoteException{
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) throws RemoteException{
		this.utilisateur = utilisateur;
	}


	public int getProduitid() throws RemoteException{
		return produitid;
	}


	public void setProduitid(int produitid)throws RemoteException {
		this.produitid = produitid;
	}


	public Produit getProduit() throws RemoteException{
		return produit;
	}




	public int getPriorite() throws RemoteException{
		return priorite;
	}


	public void setPriorite(int priorite) throws RemoteException{
		this.priorite = priorite;
	}

	
	public void setProduit(Produit produit) throws RemoteException {
		this.produit= produit;
	}
	

}
