package com.server.gustaveeiffel.upem.Dao;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.server.gustaveeiffel.upem.datasource.DataSource;
import com.server.gustaveeiffel.upem.datasource.Database;
import com.server.gustaveeiffel.upem.datasource.IDatabase;
import com.server.gustaveeiffel.upem.datasource.MySQLDataSource;

public class Config extends UnicastRemoteObject implements Serializable, Iconfig{

	private  IDatabase db;
	private DataSource ds;
	private static String serverurl="";
	private static String image_dump_path ="src/images/";
	public static String default_image_name="default.png";
	
	public Config() throws RemoteException {
		try {
			ds = new MySQLDataSource("localhost","db_partage","root");
			db = new Database(ds);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static String  getImagePath() {
		return serverurl!=""? image_dump_path :serverurl+image_dump_path ;
	}
	@Override
	public  IDatabase dbinit()throws RemoteException  {
		return db;
	}
}
