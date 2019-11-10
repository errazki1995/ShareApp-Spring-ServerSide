package com.common.gustaveeiffel.upem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;


public class Produit extends UnicastRemoteObject implements Serializable{
	private int produitId;
	private String nomProduit;
	private int  typeProduit;
	private Boolean estDispo;
	private String commentaire;
	private String note;
	private int utilisateurId;
	private double prix;
	private int nombreEmprunt;
	private Date dateAjout;


	public Produit() throws RemoteException{

	}

	public Produit(int produitId,String nomProduit,int typProduit,String commentaire,String note,int utilisateurId,Date dateAjout) throws RemoteException {
		this.produitId=produitId;
		this.nomProduit= nomProduit;
		this.typeProduit = typProduit;
		this.commentaire= commentaire;
		this.note= note;
		this.utilisateurId = utilisateurId;
		Calendar calendar = Calendar.getInstance();
		dateAjout = new Date(calendar.getTime().getTime());
		//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		//to show the date | String theDate = formatter.format(date)
	}

	public Produit(String ...row) throws RemoteException {
		//	id,nom,note,commentaire,nbEmprunts,date,type,utilisateurid

		this.produitId = toInt(row[0]);
		this.nomProduit = row[1];
		this.note = row[2];
		this.commentaire = row[3];
		this.nombreEmprunt= toInt(row[4]);
		try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(row[8]);
			this.dateAjout=new Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.typeProduit=(toInt(row[5]));
		this.utilisateurId= toInt(row[6]);
		if(row[7].equals("1")) {
			this.estDispo= true;
		}
		else {
			this.estDispo= false;
		}
	}

public  int toInt(String s) throws RemoteException {
	try {
		return Integer.parseInt(s);
	}
	catch (Exception e) {
		return 0;
	}
}
	public int getProduitId() throws RemoteException {
		return produitId;
	}
	public void setProduitId(int produitId) throws RemoteException {
		this.produitId = produitId;
	}
	public String getNomProduit()  throws RemoteException{
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) throws RemoteException {
		this.nomProduit = nomProduit;
	}
	public int getTypeProduit()  throws RemoteException{
		return typeProduit;
	}
	public void setTypeProduit(int typeProduit)  throws RemoteException{
		this.typeProduit = typeProduit;
	}
	public Boolean getEstDispo() throws RemoteException {
		return estDispo;
	}
	public void setEstDispo(Boolean estDispo) throws RemoteException {
		this.estDispo = estDispo;
	}
	public String getCommentaire()  throws RemoteException{
		return commentaire;
	}
	public void setCommentaire(String commentaire)  throws RemoteException{
		this.commentaire = commentaire;
	}
	public String getNote() throws RemoteException {
		return note;
	}
	public void setNote(String note)  throws RemoteException{
		this.note = note;
	}
	public int getUtilisateurId()  throws RemoteException{
		return utilisateurId;
	}
	public void setUtilisateurId(int utilisateurId) throws RemoteException {
		this.utilisateurId = utilisateurId;
	}
	public double getPrix() throws RemoteException {
		return prix;
	}
	public void setPrix(double prix)  throws RemoteException{
		this.prix = prix;
	}
	public int getNombreEmprunt()  throws RemoteException{
		return nombreEmprunt;
	}
	public void setNombreEmprunt(int nombreEmprunt) throws RemoteException {
		this.nombreEmprunt = nombreEmprunt;
	}
	public Date getDateAjout()  throws RemoteException{
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout)  throws RemoteException{
		this.dateAjout = dateAjout;
	}

	@Override
	public String toString() {
		return "Produit [produitId=" + produitId + ", nomProduit=" + nomProduit + ", typeProduit=" + typeProduit
				+ ", estDispo=" + estDispo + ", commentaire=" + commentaire + ", note=" + note + ", utilisateurId="
				+ utilisateurId + ", prix=" + prix + ", nombreEmprunt=" + nombreEmprunt + ", dateAjout=" + dateAjout + "]";
	}

}
