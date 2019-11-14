package test;

import java.rmi.RemoteException;

import com.common.gustaveeiffel.upem.Utilisateur;
import com.common.gustaveeiffel.upem.UtilisateurService;
import com.server.gustaveeiffel.upem.Dao.Config;
import com.server.gustaveeiffel.upem.Dao.Iconfig;
import com.server.gustaveeiffel.upem.Dao.UtilisateurDao;
import com.server.gustaveeiffel.upem.business.UtilisateurServiceDefault;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class TestUtilisateur {


	public TestUtilisateur() throws RemoteException {
		testAjouterUtilisateur();
		//testerModifierUtilisateur();
	}

	public IDatabase init() throws RemoteException {
		Iconfig start = new Config();
		return start.dbinit();
	}

	public void testAjouterUtilisateur() throws RemoteException {//creer compte
		UtilisateurService service = new UtilisateurServiceDefault();
		Utilisateur u = new Utilisateur("Abderaffi","Errazki",0,2,"Errazki","motdepasse","Masculin","abderaffi_errazki@gmail.com");
		if(service.NouveauUtilisateur(u)) System.out.println("Utilisateur"+u.getPseudo()+ "Inscrit");
		else System.out.println("Erreur inscription");	
	}

	public void testerModifierUtilisateur() throws RemoteException {
		UtilisateurService service = new UtilisateurServiceDefault();
		Utilisateur u = new Utilisateur("Errazki","Ayoub",0,2,"errazki1992","motdepasse","Masculin","ayouberrazki@gmail.com");
		//cas du modification pseudo pour l'utilisateur on doit v�rifier c'est unique
		if(service.modifierUtilisateur(2,u)) System.out.println("Utilisateur "+u.getPseudo()+ " Modifi�");
		else System.out.println("Erreur modification");	
	}


	public static void main(String[] args) throws RemoteException {
		new TestUtilisateur();
	}

}
