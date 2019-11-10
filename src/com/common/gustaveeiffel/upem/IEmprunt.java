package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;

public interface IEmprunt extends Remote{

public int getEmpruntid() throws RemoteException;
public void setEmpruntid(int empruntid)throws RemoteException;
public Date getDateEmprunt()throws RemoteException ;
public void setDateEmprunt(Date dateEmprunt)throws RemoteException ;
public Date getDateRetour() throws RemoteException;
public void setDateRetour(Date dateRetour) throws RemoteException;
public int getProduitid()throws RemoteException;
public void setProduitid(int produitid)throws RemoteException;
public int getUtilisateurid()throws RemoteException;
public void setUtilisateurid(int utilisateurid)throws RemoteException;
public IProduit getProduit()throws RemoteException;
public void setProduit(IProduit produit)throws RemoteException;
public IUtilisateur getUtilisateur()throws RemoteException;
public void setUtilisateur(IUtilisateur utilisateur)throws RemoteException;
	
}
