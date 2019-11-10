package com.server.gustaveeiffel.upem.Dao;

import com.server.gustaveeiffel.upem.datasource.IDatabase;

import java.io.Serializable;
import java.rmi.RemoteException;

import com.common.gustaveeiffel.upem.IRole;
import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.Role;

public class RoleDaoJDBC implements RoleDao,Serializable {
	private IDatabase db;
	
	public RoleDaoJDBC() throws RemoteException {
		IDatabaseLauncher launcher= new DatabaseLauncher();
		
		this.db= launcher.dbinit();
	}
	@Override
	public boolean insererRole(Role r)  throws RemoteException{
		if(db.Insert("role","roleid","nomRole")>0)return true;
		return false;
	}

	@Override
	public boolean supprimerRole(int id)  throws RemoteException{
		if (db.Delete("role","roleid", id)==0) return false;
		return true;

	}

	@Override
	public Role chercherRoleParId(int id) throws RemoteException {
		String data[][] = db.select("role","roleid",id);
		Role r = null;
		if(data==null) return null;
		else {
			r = new Role(data[1]);
		}
		return r;		
	}



}
