package com.server.gustaveeiffel.upem.Dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.Utilisateur;
import com.server.gustaveeiffel.upem.datasource.Database;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class UtilisateurDaoJDBC implements UtilisateurDao,Serializable {
	public IDatabase db;
	public UtilisateurDaoJDBC() throws RemoteException {
		IDatabaseLauncher launcher= new DatabaseLauncher();
		
		this.db=  launcher.dbinit();
		if(db==null) {
			System.out.println("db is null");
		}
		else {
			System.out.println("OK");
		}
	}

	public boolean insererUtilisateur(Utilisateur u) throws RemoteException {
		boolean succes=false;
		System.out.println(u);
		if(chercherParPseudo(u.getPseudo())==null) {
			if(db.Insert("utilisateur",u.getUserid(),u.getNom(),u.getPrenom(),u.getPseudo(),
					u.getMotdepasse(),u.getRoleid(),u.getNombreEmprunt(),u.getSexe())>0)return succes=true;
		}else {
			return succes;
		}

		return succes;
	}

	public Utilisateur chercherParPseudo(String pseudo) throws RemoteException {
		String data[][] = db.select("utilisateur","pseudo",pseudo);
		System.out.println(data.length);
		if(data.length>1) {
			return new Utilisateur(data[1]);

		}
		return null;
	}


	@Override
	public List<Utilisateur> cherchercherUtilisateurParPseudo(String pseudo) throws RemoteException {
		String data[][] = db.select("utilisateur","pseudo",pseudo);
		List<Utilisateur> users = new Vector<Utilisateur>();
		if(data==null) return null;
		for(int i=1;i<data.length;i++) {
			users.add(new Utilisateur(data[i]));
		}
		return users;			
	}



	@Override
	public void informerUtilisateur(IUtilisateur u) {
		/*informer l'utilisateur suivant que le projet demand�
		 *est bien libre
		 */

	}

public boolean supprimerUtilisateur(int id)  throws RemoteException{
		if (db.Delete("utilisateur","utilisateurid", id)==0) return false;
		return true;
	}


	public boolean modifierUtilisateur(int id, Utilisateur u) throws RemoteException {
		if(db.Update("utilisateur",id,u.getNom(),u.getPrenom(),u.getPseudo(),u.getMotdepasse(),u.getRoleid(),
				u.getNombreEmprunt(),u.getSexe())>0) return true;
		return false;
	}

	public List<Utilisateur> listeUtilisateurs() throws RemoteException {
		String data[][] = db.select("utilisateur");
		List<Utilisateur> users = new Vector<>();
		if(data==null) return null;
		for(int i=1;i<data.length;i++) {
			users.add(new Utilisateur(data[i]));
		}
		return users;
	}

	public List<Utilisateur> chercherUtilisateurParNom(String nom) throws RemoteException {
		String data[][] = db.select("utilisateur","nom",nom);
		List<Utilisateur> users = new Vector<>();
		if(data==null) return null;
		for(int i=1;i<data.length;i++) {
			users.add(new Utilisateur(data[i]));
		}
		return users;	
	}

	@Override
	public Utilisateur chercherUtilisateurParId(int id) throws RemoteException {

		String data[][] = db.select("utilisateur","utilisateurid",id);
		Utilisateur u = null;
		if(data.length<=1) return u;
		else {
			u = new Utilisateur(data[1]);
		}
		System.out.println("server:"+ u);
		return u;
	}


  public String testRMI() {
		String data[][]=null;
		try {
			data = db.select("utilisateur","nom","Lohier");
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	  if(db==null) {
		  return "database is null Return" +data.length;
	  }
	  else
	  return " Database instanciation Returned";
  }



}
