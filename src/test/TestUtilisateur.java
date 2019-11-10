package test;

import java.rmi.RemoteException;

import com.common.gustaveeiffel.upem.Utilisateur;
import com.common.gustaveeiffel.upem.UtilisateurService;
import com.server.gustaveeiffel.upem.Dao.DatabaseLauncher;
import com.server.gustaveeiffel.upem.Dao.IDatabaseLauncher;
import com.server.gustaveeiffel.upem.Dao.UtilisateurDao;
import com.server.gustaveeiffel.upem.business.UtilisateurServiceDefault;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class TestUtilisateur {


	public TestUtilisateur() throws RemoteException {
		testAjouterUtilisateur();
		//testerModifierUtilisateur();
	}

	public IDatabase init() throws RemoteException {
		IDatabaseLauncher start = new DatabaseLauncher();
		return start.dbinit();
	}

	public void testAjouterUtilisateur() throws RemoteException {//creer compte
		UtilisateurService service = new UtilisateurServiceDefault();
		Utilisateur u = new Utilisateur("Yassine","Cheggar",0,2,"Cheggar","motdepasse","Masculin");
		if(service.NouveauUtilisateur(u)) System.out.println("Utilisateur"+u.getPseudo()+ "Inscrit");
		else System.out.println("Erreur inscription");	
	}
	
	public void testerModifierUtilisateur() throws RemoteException {
		UtilisateurService service = new UtilisateurServiceDefault();
		Utilisateur u = new Utilisateur("Errazki","Ayoub",0,2,"errazki1992","motdepasse","Masculin");
		//cas du modification pseudo pour l'utilisateur on doit v�rifier c'est unique
		if(service.modifierUtilisateur(2,u)) System.out.println("Utilisateur "+u.getPseudo()+ " Modifi�");
		else System.out.println("Erreur modification");	
	}
	

	public static void main(String[] args) throws RemoteException {
		new TestUtilisateur();
	}

}
