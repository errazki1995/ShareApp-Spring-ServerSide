package com.common.gustaveeiffel.upem;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RoleService extends Remote {

	public boolean AjouterRole(Role r)  throws RemoteException;
	public boolean supprimer(int id) throws RemoteException;
}
