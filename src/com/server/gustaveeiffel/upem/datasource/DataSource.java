package com.server.gustaveeiffel.upem.datasource;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract  class DataSource extends UnicastRemoteObject implements Serializable, IDatasource{
	private String driver; //default port 3306
	private String bridge;//exmp http, ftp ...
	private String host;
	private String source;
	private String user;
	private String password;
	
	public DataSource() throws RemoteException{
		
	}

	public DataSource(String driver, String bridge, String host, String source, String user, String password) throws RemoteException{
		super();
		this.driver = driver;
		this.bridge = bridge;
		this.host = host;
		this.source = source;
		this.user = user;
		this.password = password;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getBridge() {
		return bridge;
	}

	public void setBridge(String bridge) {
		this.bridge = bridge;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
	public Connection getConnection() {
		try {
			// I. Chargement du Driver : 
			Class.forName(driver);
			// II. Connexion
			String url = getUrl() ;
			Connection db = DriverManager.getConnection(url, user, password);
			System.out.println(">> Connexion bien établie!");
			return db;
		}
		catch(Exception e){
			System.out.println("Erreur : "+ e.getMessage());
			return null;
		}
	}
	

	abstract public String getUrl();
	abstract public char startDelimiter();
	abstract public char endDelimiter();

}
