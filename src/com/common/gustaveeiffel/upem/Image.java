package com.common.gustaveeiffel.upem;

import java.rmi.RemoteException;

public class Image {
private int imageid;
private String chemin;
private String nomfichier;
private int produitid;
private Produit produit;


public Image() {
	
}

public Image(String ...row) throws RemoteException {
	this.imageid = toInt(row[0]);
	this.chemin = row[1];
	this.nomfichier=row[2];
	this.produitid = toInt(row[3]);
	
	
}

public  int toInt(String s)throws RemoteException {
	try {
		return Integer.parseInt(s);
	}
	catch (Exception e) {
		return 0;
	}
}

public Image(int imageid, String chemin, String nomfichier, int produitid, Produit produit) {
	super();
	this.imageid = imageid;
	this.chemin = chemin;
	this.nomfichier = nomfichier;
	this.produitid = produitid;
	this.produit = produit;
}
public Image(int imageid, String chemin, String nomfichier, int produitid) {
	super();
	this.imageid = imageid;
	this.chemin = chemin;
	this.nomfichier = nomfichier;
	this.produitid = produitid;
}

public int getImageid() {
	return imageid;
}


public void setImageid(int imageid) {
	this.imageid = imageid;
}


public String getChemin() {
	return chemin;
}


public void setChemin(String chemin) {
	this.chemin = chemin;
}


public String getNomfichier() {
	return nomfichier;
}


public void setNomfichier(String nomfichier) {
	this.nomfichier = nomfichier;
}


public int getProduitid() {
	return produitid;
}


public void setProduitid(int produitid) {
	this.produitid = produitid;
}


public Produit getProduit() {
	return produit;
}


public void setProduit(Produit produit) {
	this.produit = produit;
}


}
