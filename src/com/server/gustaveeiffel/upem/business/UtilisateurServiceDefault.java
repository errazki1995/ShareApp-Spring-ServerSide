package com.server.gustaveeiffel.upem.business;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.Utilisateur;
import com.common.gustaveeiffel.upem.UtilisateurService;
import com.server.gustaveeiffel.upem.Dao.UtilisateurDao;
import com.server.gustaveeiffel.upem.Dao.UtilisateurDaoJDBC;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

 
public class UtilisateurServiceDefault implements UtilisateurService,Serializable {
public  UtilisateurDao dao;

 public UtilisateurServiceDefault() throws RemoteException {
	 
		dao = new UtilisateurDaoJDBC(); 
		
}
	@Override
	public boolean NouveauUtilisateur(Utilisateur u) throws RemoteException {
		if(dao.insererUtilisateur(u)) return true;
		return false;
	}

	@Override
	public boolean supprimerUtilisateur(int id) throws RemoteException {
     if(dao.supprimerUtilisateur(id)) return true;
     return false;
	}

	@Override
	public boolean modifierUtilisateur(int id, Utilisateur u) throws RemoteException {
		if(dao.modifierUtilisateur(id, u)) return true;
		return false;
	}

	@Override
	public List<Utilisateur> cherchercherUtilisateurParPseudo(String pseudo) throws RemoteException {
		return dao.cherchercherUtilisateurParPseudo(pseudo);
	}

	@Override
	public List<Utilisateur> chercherUtilisateurParNom(String nom) throws RemoteException {
		return dao.chercherUtilisateurParNom(nom);
	}

	@Override
	public List<Utilisateur> listeUtilisateurs() throws RemoteException {
		return dao.listeUtilisateurs();
	}
	@Override
	public Utilisateur chercherUtilisateurParId(int id) throws RemoteException {
		return dao.chercherUtilisateurParId(id);
	}
	public String testRMI() throws RemoteException {
		return dao.testRMI();
	}
	@Override
	public void informerUtilisateur(Utilisateur u, Produit p) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
