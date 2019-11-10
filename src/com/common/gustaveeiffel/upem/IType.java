package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IType extends Remote{
	public int getTypeid() throws RemoteException;
	public void setTypeid(int typeid)throws RemoteException;
	public String getNomType()throws RemoteException;
	public void setNomType(String nomType)throws RemoteException;
	public  int toInt(String s)throws RemoteException;
}
