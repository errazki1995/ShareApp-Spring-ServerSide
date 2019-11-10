package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UtilisateurService extends Remote{
	public boolean NouveauUtilisateur(Utilisateur u) throws RemoteException;
	public void informerUtilisateur(Utilisateur u) throws RemoteException;
	public boolean supprimerUtilisateur(int id) throws RemoteException;
	public boolean modifierUtilisateur(int id,Utilisateur u) throws RemoteException;
	public List<Utilisateur> cherchercherUtilisateurParPseudo(String pseudo) throws RemoteException;
	public List<Utilisateur> chercherUtilisateurParNom(String nom) throws RemoteException;
	public Utilisateur chercherUtilisateurParId(int id) throws RemoteException;
	//section administrateur 
	public List<Utilisateur> listeUtilisateurs() throws RemoteException;
	public String testRMI() throws RemoteException; 

}
