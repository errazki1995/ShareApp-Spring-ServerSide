package com.common.gustaveeiffel.upem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Utilisateur extends UnicastRemoteObject implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int userid;
private String nom;
private String prenom;
private int nombreEmprunt;
private int roleid;
private Role role;
private String pseudo;
private String motdepasse;
private String sexe;



public Utilisateur() throws RemoteException{
	
}
public Utilisateur(String nom, String prenom, int nombreEmprunt, int roleid, String pseudo,
		String motdepasse, String sexe) throws RemoteException {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.nombreEmprunt = nombreEmprunt;
	this.roleid = roleid;
	this.pseudo = pseudo;
	this.motdepasse = motdepasse;
	this.sexe = sexe;
}
public  Utilisateur(String ...row) throws RemoteException {
	
	this.userid=toInt((row[0]));
	this.nom=row[1];
	this.prenom=row[2];
	this.pseudo=row[3];
	this.motdepasse=row[4];
	this.roleid=toInt(row[5]);
	this.nombreEmprunt=toInt(row[6]);
	this.sexe=row[7];
}
public Utilisateur(Role r,String ...row) throws RemoteException {

	this.userid=toInt((row[0]));
	this.nom=row[1];
	this.prenom=row[2];
	this.pseudo=row[3];
	this.motdepasse=row[4];
	this.roleid=toInt(row[5]);
	this.nombreEmprunt=toInt(row[6]);
	this.sexe=row[7];
    setRole(r);
}

public  int toInt(String s) throws RemoteException{
	try {
		return Integer.parseInt(s);
	}
	catch (Exception e) {
		return 0;
	}
}
public int getUserid()throws RemoteException {
	return userid;
}
public void setUserid(int userid)throws RemoteException {
	this.userid = userid;
}
public String getNom() throws RemoteException {
	return nom;
}
public void setNom(String nom) throws RemoteException{
	this.nom = nom;
}
public String getPrenom()throws RemoteException {
	return prenom;
}
public void setPrenom(String prenom) throws RemoteException{
	this.prenom = prenom;
}
public int getNombreEmprunt() throws RemoteException{
	return nombreEmprunt;
}
public void setNombreEmprunt(int nombreEmprunt) throws RemoteException{
	this.nombreEmprunt = nombreEmprunt;
}
public int getUserRoleId() throws RemoteException{
	return roleid;
}
public void setUserRoleId(int roleid) throws RemoteException{
	this.roleid= roleid;
}
public int getRoleid() throws RemoteException{
	return roleid;
}
public void setRoleid(int roleid) throws RemoteException{
	this.roleid = roleid;
}
public String getPseudo() throws RemoteException{
	return pseudo;
}
public void setPseudo(String pseudo) throws RemoteException{
	this.pseudo = pseudo;
}
public String getMotdepasse() throws RemoteException{
	return motdepasse;
}
public void setMotdepasse(String motdepasse) throws RemoteException{
	this.motdepasse = motdepasse;
}
public String getSexe() throws RemoteException{
	return sexe;
}
public void setSexe(String sexe)throws RemoteException {
	this.sexe = sexe;
}

public Role getRole()throws RemoteException {
	return role;
}
public void setRole(Role role) throws RemoteException{
	this.role = role;
}
@Override
public String toString() {
	return "IUtilisateur [userid=" + userid + ", nom=" + nom + ", prenom=" + prenom + ", nombreEmprunt=" + nombreEmprunt
			+ ", roleid=" + roleid + ", pseudo=" + pseudo + ", motdepasse=" + motdepasse + ", sexe=" + sexe + "]";
}



}
