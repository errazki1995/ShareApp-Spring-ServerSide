package com.server.gustaveeiffel.upem.datasource;

import java.io.Serializable;
import java.rmi.RemoteException;

public class MySQLDataSource extends DataSource implements Serializable {
	
	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String MYSQL_BRIDGE = "jdbc:mysql:";
	
	public MySQLDataSource()throws RemoteException {
			
	}

	public MySQLDataSource(String host, String source, String user, String password) throws RemoteException{
		super(MYSQL_DRIVER, MYSQL_BRIDGE, host, source, user, password);
	}

	public MySQLDataSource(String host, String source, String user) throws RemoteException{
		super(MYSQL_DRIVER, MYSQL_BRIDGE, host, source, user, "");
	}
	
	public MySQLDataSource(String host, String source) throws RemoteException{
		super(MYSQL_DRIVER, MYSQL_BRIDGE, host, source, "root", "toor");
	}
	
	public MySQLDataSource(String source)throws RemoteException {
		super(MYSQL_DRIVER, MYSQL_BRIDGE, "localhost", source, "root", "");
	}

	public String getUrl()
	{
		return MYSQL_BRIDGE + "//" + getHost() + "/" + getSource();
	}

	public char startDelimiter() {
		return '`';
	}

	public char endDelimiter() {
		return '`';
	}

}

