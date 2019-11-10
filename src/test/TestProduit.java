package test;

import java.util.Calendar;
import java.util.List;
import java.rmi.RemoteException;

import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.ProduitService;
import com.server.gustaveeiffel.upem.Dao.DatabaseLauncher;
import com.server.gustaveeiffel.upem.Dao.IDatabaseLauncher;
import com.server.gustaveeiffel.upem.business.ProduitServiceDefault;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class TestProduit {
	public TestProduit() throws RemoteException {
		//testAjoutProduit();
		//testChercherProduit();
		testmodifierProduit();	
		//supprimerProduit();

	}

	public IDatabase init() throws RemoteException {
		IDatabaseLauncher start = new DatabaseLauncher();
		return start.dbinit();
	}

	public void testAjouterRole() {

	}
	public void testAjoutProduit() throws RemoteException { 
		ProduitService produitservice = new ProduitServiceDefault();
		//creeons notre produit 
		Produit p = new Produit();
		//p.setProduitId(1);
		p.setPrix(0);
		//p.setDateAjout();
		p.setEstDispo(true);
		p.setNomProduit("Appareil Ipod pour musique");
		p.setNombreEmprunt(0);
		p.setNote("5/5");
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		p.setDateAjout(date);
		p.setCommentaire("charger la avant de seteindre");
		p.setUtilisateurId(1);
		p.setTypeProduit(3);
		produitservice.Ajouter(p);
	}

	public void testChercherProduit() throws RemoteException {
		ProduitService produitservice = new ProduitServiceDefault();
		List<Produit> produits= produitservice.chercherProduitParcle("spring");
		for(Produit p:produits) {
			System.out.println(p);
		}

	}

	public void testmodifierProduit() throws RemoteException {
		ProduitService produitservice = new ProduitServiceDefault();
		Produit nouveauProduit = new Produit();
		Produit p = new Produit();
		//p.setProduitId(1);
		p.setPrix(0);
		//p.setDateAjout();
		p.setEstDispo(true);
		p.setNomProduit("Appareil Ipod pour musique");
		p.setNombreEmprunt(0);
		p.setNote("5/5");
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		p.setDateAjout(date);
		p.setCommentaire("charger la avant de l�teindre");
		p.setUtilisateurId(1);
		p.setTypeProduit(1);
		produitservice.modifierProduit(4, p);

	}
	public void supprimerProduit() throws RemoteException {
		ProduitService produitservice = new ProduitServiceDefault();
		if(produitservice.deleteProduct("1")) {
			System.out.println("supprim� avec succ�s");
		}
		else {
			System.out.println("Erreur suppression!");
		}

	}

	public static void main(String[] args) throws RemoteException {
		new TestProduit();
	}
}
