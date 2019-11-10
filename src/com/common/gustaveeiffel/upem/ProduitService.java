package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProduitService extends Remote {
	public boolean Ajouter(Produit p) throws RemoteException;

	public List<Produit> chercherProduitParcle(String motcle) throws RemoteException;
	public Produit chercherProduitParId(int id) throws RemoteException;
	public boolean modifierProduit(int id, Produit p) throws RemoteException;
	public boolean Emprunter(int produitid, int utilisateurid) throws RemoteException;
	public void restituer(Produit p, Utilisateur u) throws RemoteException;// quel produit et qui le poss�de
	public boolean deleteProduct(String id) throws RemoteException;
	public List<Produit> listeProduit() throws RemoteException;
    public void ajouterEnAttente(Produit p, int utilisateurid) throws RemoteException;
	//public boolean emprunterProduit(int produitid,int utilisateurid);
    public boolean ajouterDemande(Demande d, int produit, int utilisateurid) throws RemoteException;
    public boolean modifierDemande(int produit, int utilisateurid, int priorite) throws RemoteException;
    public List<Demande> listeDemandes(int produitid) throws RemoteException;

}
