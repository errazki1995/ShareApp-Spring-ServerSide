package com.server.gustaveeiffel.upem.business;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import com.common.gustaveeiffel.upem.Type;
import com.common.gustaveeiffel.upem.TypeService;
import com.server.gustaveeiffel.upem.Dao.TypeDao;
import com.server.gustaveeiffel.upem.Dao.TypeDaoJDBC;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class TypeServiceDefault implements TypeService,Serializable {
TypeDao dao;
	

public TypeServiceDefault() throws RemoteException {
	dao= new TypeDaoJDBC();
}
	public boolean ajouterType(Type t) throws RemoteException {
		if(dao.insererType(t))return true;
		return false;
	}

	
	public boolean supprimer(int id) throws RemoteException {
		if(dao.supprimerType(id)) return true;
		return false;
	}

	public List<Type> listTypes() throws RemoteException {
		return dao.listType();
	}

}
