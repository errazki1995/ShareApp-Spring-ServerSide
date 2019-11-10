package com.server.gustaveeiffel.upem.business;


import java.io.Serializable;
import java.rmi.RemoteException;

import com.common.gustaveeiffel.upem.IRole;
import com.common.gustaveeiffel.upem.Role;
import com.common.gustaveeiffel.upem.RoleService;
import com.server.gustaveeiffel.upem.Dao.RoleDao;
import com.server.gustaveeiffel.upem.Dao.RoleDaoJDBC;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class RoleServiceDefault implements RoleService,Serializable{
 private RoleDao dao;
	
 
 public RoleServiceDefault() throws RemoteException {
	 dao = new RoleDaoJDBC();
 }
 
	public boolean AjouterRole(Role r) throws RemoteException {
		if(dao.insererRole(r)) return true;
		return false;
	}

	public boolean supprimer(int id) throws RemoteException {
		if(dao.supprimerRole(id)) return true;
		return false;
	}



}
