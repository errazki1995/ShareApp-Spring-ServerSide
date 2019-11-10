package com.server.gustaveeiffel.upem.datasource;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDatabase extends Remote {

	IDatasource getDataSource() throws RemoteException;

	void setDataSource(DataSource dataSource) throws RemoteException;

	String delimit(String name) throws RemoteException;

	String[][] executeQuery(String query) throws RemoteException;

	String[][] select(String tableName) throws RemoteException;

	String[][] select(String tableName, String key, Object value) throws RemoteException;

	String[][] selectLike(String tableName, String key, String value) throws RemoteException;

	int Insert(String tableName, Object... row) throws RemoteException;

	String[] getColumnNames(String tableName) throws RemoteException;

	int UpdateQuery(String query) throws RemoteException;

	int Update(String tableName, Object... row) throws RemoteException;

	int Delete(String tableName, String key, Object value) throws RemoteException;

	String[] getTableNames() throws RemoteException;

}