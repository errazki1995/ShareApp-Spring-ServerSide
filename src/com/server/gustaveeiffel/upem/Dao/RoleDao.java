package com.server.gustaveeiffel.upem.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.common.gustaveeiffel.upem.IRole;
import com.common.gustaveeiffel.upem.Role;

public interface RoleDao extends Remote {

	public boolean insererRole(Role r)  throws RemoteException ;
	public boolean supprimerRole(int roleId) throws RemoteException;
	public Role chercherRoleParId(int id) throws RemoteException;
	
}
