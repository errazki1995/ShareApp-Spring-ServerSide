package com.server.gustaveeiffel.upem.Dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

import com.common.gustaveeiffel.upem.Produit;
import com.common.gustaveeiffel.upem.Type;
import com.server.gustaveeiffel.upem.datasource.IDatabase;

public class TypeDaoJDBC implements TypeDao,Serializable{

	private IDatabase db;
	
	public TypeDaoJDBC() throws RemoteException {
		Iconfig launcher= new Config();
		
		this.db= launcher.dbinit();
	}

	public boolean insererType(Type t)  throws RemoteException{
		if(db.Insert("Type","typeid","nomtype")>0)return true;
		return false;
	}

	
	public boolean supprimerType(int typeId) throws RemoteException {
		if (db.Delete("Type","idType", typeId)==0) return false;
		return true;
	}

	
	public List<Type> listType() throws RemoteException {
		String data[][] = db.select("Type");
		if (data == null) return null;
		List<Type> types = new Vector<>();
		
		for (int i = 1; i < data.length; i++) {
			types.add( new Type(data[i]));
		}
		return types;
		}

}
