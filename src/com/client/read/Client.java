package com.client.read;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.ProduitService;
import com.common.gustaveeiffel.upem.Utilisateur;
import com.common.gustaveeiffel.upem.UtilisateurService;

public class Client {

	public Client() {
		
	}
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		UtilisateurService serv = (UtilisateurService)Naming.lookup("utilservice");
		ProduitService produitservice= (ProduitService) Naming.lookup("produitservice");
		//Utilisateur u= serv.chercherUtilisateurParId(1);
		Produit p = produitservice.chercherProduitParId(2);
		//String u = serv.testRMI();
		System.out.println(p);
	}
	
}
