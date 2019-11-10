package test;

import java.rmi.RemoteException;
import java.util.Calendar;

import com.server.gustaveeiffel.upem.Dao.DatabaseLauncher;
import com.server.gustaveeiffel.upem.Dao.IDatabaseLauncher;
import com.server.gustaveeiffel.upem.business.ProduitServiceDefault;
import com.server.gustaveeiffel.upem.business.UtilisateurServiceDefault;
import com.server.gustaveeiffel.upem.datasource.IDatabase;
import com.common.gustaveeiffel.upem.*;

public class TestDemande {
	public TestDemande() throws RemoteException {
		//testAjoutDemande();
		//testmodifierDemande();
		testAjouterAttente();
	}

	public IDatabase initDb() throws RemoteException {
		IDatabaseLauncher start = new DatabaseLauncher();
		return start.dbinit();
	}

	void testmodifierDemande() throws RemoteException {
		ProduitService service = new ProduitServiceDefault();
		service.modifierDemande(2,1, 1);

	}

	void testAjoutDemande() throws RemoteException {
		ProduitService service = new ProduitServiceDefault();
		UtilisateurService utilservice = new UtilisateurServiceDefault();

		Utilisateur u = utilservice.cherchercherUtilisateurParPseudo("emilia22").get(0);

		System.out.println(u);

		Produit p = service.chercherProduitParId(2);

		System.out.println(p);
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Demande d = new Demande(date,u,p,1);
		if(service.ajouterDemande(d,p.getProduitId(),u.getUserid())) {
			System.out.println("Ajouter");
		}
		else System.out.println("Pas");

	}


	void testAjouterAttente() throws RemoteException {
		ProduitService service = new ProduitServiceDefault();
		UtilisateurService utilservice = new UtilisateurServiceDefault();

		Utilisateur u = utilservice.chercherUtilisateurParId(10);

		System.out.println(u);

		Produit p = service.chercherProduitParId(2);

		System.out.println(p);
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		Demande d = new Demande(date,u,p,1);

		service.ajouterEnAttente(p, u.getUserid());
	}
	public static void main(String[] args) throws RemoteException {
		new TestDemande();
	}

}

