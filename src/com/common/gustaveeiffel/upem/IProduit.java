package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

public interface IProduit extends Remote {
	public  int toInt(String s)throws RemoteException;
		public int getProduitId() throws RemoteException;
		public void setProduitId(int produitId)throws RemoteException;
		public String getNomProduit() throws RemoteException;
		public void setNomProduit(String nomProduit)throws RemoteException;
		public int getTypeProduit()throws RemoteException;
		public void setTypeProduit(int typeProduit)throws RemoteException;
		public Boolean getEstDispo()throws RemoteException;
		public void setEstDispo(Boolean estDispo)throws RemoteException;
		public String getCommentaire()throws RemoteException;
		public void setCommentaire(String commentaire)throws RemoteException;
		public String getNote()throws RemoteException;
		public void setNote(String note)throws RemoteException;
		public int getUtilisateurId()throws RemoteException;
		public void setUtilisateurId(int utilisateurId)throws RemoteException;
		public double getPrix()throws RemoteException;
		public void setPrix(double prix)throws RemoteException;
		public int getNombreEmprunt()throws RemoteException;
		public void setNombreEmprunt(int nombreEmprunt)throws RemoteException;
		public Date getDateAjout()throws RemoteException;
		public void setDateAjout(Date dateAjout)throws RemoteException;

}
