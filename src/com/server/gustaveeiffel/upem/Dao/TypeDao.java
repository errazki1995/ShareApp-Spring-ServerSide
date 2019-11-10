package com.server.gustaveeiffel.upem.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.common.gustaveeiffel.upem.Type;

public interface TypeDao extends Remote {
	public boolean insererType(Type t)throws RemoteException; 
	public boolean supprimerType(int typeId) throws RemoteException;
	public List<Type> listType() throws RemoteException;
}
