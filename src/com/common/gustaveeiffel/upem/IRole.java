package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRole extends Remote{
	public  int toInt(String s) throws RemoteException;
	public String getNomRole()throws RemoteException;
	public void setNomRole(String nomRole)throws RemoteException;
	public int getRoleid()throws RemoteException;
}
