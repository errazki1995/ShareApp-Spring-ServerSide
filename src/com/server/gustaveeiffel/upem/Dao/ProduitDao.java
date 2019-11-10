package com.server.gustaveeiffel.upem.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.common.gustaveeiffel.upem.Demande;
import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.Produit;

public interface ProduitDao extends Remote {
	public boolean insertProduit(Produit p) throws RemoteException;
	public boolean modifierProduit(int id,Produit p)throws RemoteException;
	public List<Produit> chercherProduitparMotcle(String motcle) throws RemoteException;
	public Produit chercherProduitParId(int id) throws RemoteException;
	public boolean supprimerProduit(String id) throws RemoteException;
	public List<Produit> listeProduits() throws RemoteException;
	public boolean restituer(Produit p , IUtilisateur u) throws RemoteException;
	public List<Demande> listeDemandes(int produit) throws RemoteException;
	public boolean insererDemande(Demande d) throws RemoteException;
	public boolean modifierDemande(int produit,int utilisateurid,int priorite) throws RemoteException;
	public Demande recupererDemande(int produit,int utilisateurid) throws RemoteException;

}
