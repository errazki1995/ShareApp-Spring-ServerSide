package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface TypeService extends Remote {

	public  boolean ajouterType(Type t) throws RemoteException;
	public boolean supprimer(int id) throws RemoteException;
	public List<Type> listTypes() throws RemoteException;
}
