package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IUtilisateur  extends Remote {

public  int toInt(String s) throws RemoteException;
public int getUserid()throws RemoteException;
public void setUserid(int userid) throws RemoteException;
public String getNom()throws RemoteException;
public void setNom(String nom)throws RemoteException;
public String getPrenom() throws RemoteException;
public void setPrenom(String prenom)throws RemoteException;
public int getNombreEmprunt()throws RemoteException;
public void setNombreEmprunt(int nombreEmprunt)throws RemoteException;
public int getUserRoleId()throws RemoteException;
public void setUserRoleId(int roleid)throws RemoteException;
public int getRoleid()throws RemoteException;
public void setRoleid(int roleid)throws RemoteException;
public String getPseudo()throws RemoteException;
public void setPseudo(String pseudo)throws RemoteException;
public String getMotdepasse()throws RemoteException;
public void setMotdepasse(String motdepasse)throws RemoteException;
public String getSexe()throws RemoteException;
public void setSexe(String sexe)throws RemoteException;

public IRole getRole()throws RemoteException;
public void setRole(IRole role)throws RemoteException;
}
