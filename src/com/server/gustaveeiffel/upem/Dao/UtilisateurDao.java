package com.server.gustaveeiffel.upem.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.Utilisateur;

public interface UtilisateurDao extends Remote {

	public boolean insererUtilisateur(Utilisateur u) throws RemoteException;
	public void informerUtilisateur(IUtilisateur u) throws RemoteException;
	public boolean supprimerUtilisateur(int id) throws RemoteException;
	public boolean modifierUtilisateur(int id,Utilisateur u) throws RemoteException;
	public List<Utilisateur> cherchercherUtilisateurParPseudo(String pseudo) throws RemoteException;
	public List<Utilisateur> chercherUtilisateurParNom(String nom) throws RemoteException;
	public Utilisateur chercherUtilisateurParId(int id) throws RemoteException;
	public List<Utilisateur> listeUtilisateurs() throws RemoteException;
	public String testRMI(); 
}
