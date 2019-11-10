package com.common.gustaveeiffel.upem;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
//
public class Type extends UnicastRemoteObject implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private int typeid;
private String nomType;
public Type() throws RemoteException {
	
}

public Type(String ...row)throws RemoteException {
	try {
		this.typeid=toInt(row[0]);
	} catch (RemoteException e) {
		e.printStackTrace();
	}
	this.nomType = row[1];
}



public int getTypeid() throws RemoteException{
	return typeid;
}
public void setTypeid(int typeid) throws RemoteException{
	this.typeid = typeid;
}
public String getNomType() {
	return nomType;
}
public void setNomType(String nomType) throws RemoteException{
	this.nomType = nomType;
}
public  int toInt(String s)throws RemoteException {
	try {
		return Integer.parseInt(s);
	}
	catch (Exception e) {
		return 0;
	}
}
}
