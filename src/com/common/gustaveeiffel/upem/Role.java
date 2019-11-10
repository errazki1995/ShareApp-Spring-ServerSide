package com.common.gustaveeiffel.upem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Role extends UnicastRemoteObject implements Serializable{
private int roleid;
private String  nomRole;


public Role(String ...row) throws RemoteException {
	this.roleid=toInt(row[0]);
	this.nomRole = row[1];
}

public Role()throws RemoteException {
	
}
public  int toInt(String s) throws RemoteException {
	try {
		return Integer.parseInt(s);
	}
	catch (Exception e) {
		return 0;
	}
}
public String getNomRole() throws RemoteException {
	return nomRole;
}
public void setNomRole(String nomRole) throws RemoteException {
	this.nomRole = nomRole;
}
public int getRoleid()  throws RemoteException{
	return roleid;
}

}
