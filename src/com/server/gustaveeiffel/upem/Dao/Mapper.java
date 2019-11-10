package com.server.gustaveeiffel.upem.Dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.common.gustaveeiffel.upem.IUtilisateur;
import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.Utilisateur;

public class Mapper implements Serializable{


	public static Produit ProductMapper(String ...row) throws NumberFormatException, RemoteException {
		Produit p = new Produit();
		p.setProduitId(Integer.parseInt(row[0]));
		p.setNomProduit(row[1]);
		p.setNote(row[2]);
		p.setCommentaire(row[3]);
		p.setNombreEmprunt(Integer.parseInt(row[4]));
		try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(row[8]);
			p.setDateAjout(new Date(utilDate.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		p.setTypeProduit((Integer.parseInt(row[5])));
		p.setUtilisateurId(Integer.parseInt(row[6]));
		if(row[7].equals("1")) {
			p.setEstDispo(true);
		}
		else {
			p.setEstDispo(false);
		}
		return p;	
	}


	public static Utilisateur UserMapper(String ...row) throws RemoteException{
		Utilisateur u = new Utilisateur();
		u.setUserid(toInt((row[0])));
		u.setNom(row[1]);
		u.setPrenom(row[2]);
		u.setPseudo(row[3]);
		u.setMotdepasse(row[4]);
		u.setRoleid(toInt(row[5]));
		u.setNombreEmprunt(toInt(row[6]));
		u.setSexe(row[7]);
		return u;
	}


	public static int toInt(String s) {
		try {
			return Integer.parseInt(s);
		}
		catch (Exception e) {
			return 0;
		}
	}

}




