package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Date;
import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.IProduit;

public interface IDemande extends Remote {

	public  int toInt(String s) throws RemoteException;

	public int getDemandeid()throws RemoteException;


	public void setDemandeid(int demandeid)throws RemoteException;


	public Date getDatedemande()throws RemoteException;

	public void setDatedemande(Date datedemande)throws RemoteException;
	public int getUtilisateurid()throws RemoteException;


	public void setUtilisateurid(int utilisateurid)throws RemoteException;


	public IUtilisateur getUtilisateur()throws RemoteException;

	public void setUtilisateur(IUtilisateur utilisateur)throws RemoteException;


	public int getProduitid()throws RemoteException;


	public void setProduitid(int produitid)throws RemoteException;


	public IProduit getProduit()throws RemoteException;

	public void setProduit(IProduit produit)throws RemoteException;


	public int getPriorite()throws RemoteException;


	public void setPriorite(int priorite)throws RemoteException;
}
