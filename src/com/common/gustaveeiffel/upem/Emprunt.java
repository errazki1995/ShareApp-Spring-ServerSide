package com.common.gustaveeiffel.upem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;

public class Emprunt  extends UnicastRemoteObject implements Serializable{
private int empruntid;
private Date dateEmprunt;
private Date dateRetour;
private int produitid;
private int utilisateurid;
private IProduit produit;
private IUtilisateur utilisateur;

public Emprunt() throws RemoteException {
	
}

public Emprunt(int empruntid, Date dateEmprunt, Date dateRetour, int produitid, int utilisateurid, IProduit produit,
		IUtilisateur utilisateur) throws RemoteException {
	super();
	this.empruntid = empruntid;
	this.dateEmprunt = dateEmprunt;
	this.dateRetour = dateRetour;
	this.produitid = produitid;
	this.utilisateurid = utilisateurid;
	this.produit = produit;
	this.utilisateur = utilisateur;
}

public int getEmpruntid()throws RemoteException {
	return empruntid;
}
public void setEmpruntid(int empruntid)throws RemoteException {
	this.empruntid = empruntid;
}
public Date getDateEmprunt()throws RemoteException {
	return dateEmprunt;
}
public void setDateEmprunt(Date dateEmprunt)throws RemoteException {
	this.dateEmprunt = dateEmprunt;
}
public Date getDateRetour()throws RemoteException {
	return dateRetour;
}
public void setDateRetour(Date dateRetour) throws RemoteException{
	this.dateRetour = dateRetour;
}
public int getProduitid() throws RemoteException{
	return produitid;
}
public void setProduitid(int produitid)throws RemoteException {
	this.produitid = produitid;
}
public int getUtilisateurid()throws RemoteException {
	return utilisateurid;
}
public void setUtilisateurid(int utilisateurid) throws RemoteException{
	this.utilisateurid = utilisateurid;
}
public IProduit getProduit() throws RemoteException{
	return produit;
}
public void setProduit(IProduit produit) throws RemoteException{
	this.produit = produit;
}
public IUtilisateur getUtilisateur() throws RemoteException{
	return utilisateur;
}
public void setUtilisateur(IUtilisateur utilisateur)throws RemoteException {
	this.utilisateur = utilisateur;
}

}
