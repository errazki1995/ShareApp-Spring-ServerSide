package com.server.gustaveeiffel.upem.Dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.server.gustaveeiffel.upem.datasource.DataSource;
import com.server.gustaveeiffel.upem.datasource.Database;
import com.server.gustaveeiffel.upem.datasource.IDatabase;
import com.server.gustaveeiffel.upem.datasource.MySQLDataSource;

public class DatabaseLauncher extends UnicastRemoteObject implements Serializable, IDatabaseLauncher{

	private  IDatabase db;
	private DataSource ds;
	
	public DatabaseLauncher() throws RemoteException {
		try {
			ds = new MySQLDataSource("localhost","db_partage","root");
			db = new Database(ds);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.Dao.IDatabaseLauncher#dbinit()
	 */
	@Override
	public  IDatabase dbinit()throws RemoteException  {
		return db;
	}
}
