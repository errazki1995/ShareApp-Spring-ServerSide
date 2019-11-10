package com.server.gustaveeiffel.upem.datasource;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class Database extends UnicastRemoteObject implements IDatabase,Serializable{
	private DataSource dataSource;
	private Connection db;
	private DatabaseMetaData dbm;

	public Database()  throws RemoteException{

	}

	public Database(DataSource dataSource) throws RemoteException{
		setDataSource(dataSource);
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#getDataSource()
	 */
	@Override
	public IDatasource getDataSource()  throws RemoteException{
		return dataSource;
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#setDataSource(com.server.gustaveeiffel.upem.datasource.DataSource)
	 */
	@Override
	public void setDataSource(DataSource dataSource)  throws RemoteException{
		this.dataSource = dataSource;
		db = dataSource.getConnection();
		try {
			dbm = db.getMetaData();
		} catch (Exception e) {
			System.out.println("Erreur : "+e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#delimit(java.lang.String)
	 */
	@Override
	public String delimit(String name)  throws RemoteException{
		if (name.contains(" ")) {
			name = dataSource.startDelimiter()+name+dataSource.endDelimiter();
		}
		return name;
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#executeQuery(java.lang.String)
	 */
	@Override
	public String[][] executeQuery(String query) throws RemoteException
	{
		System.out.println("Query : " + query);	
		try {
			Statement sql = db.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
					);
			ResultSet rs = sql.executeQuery(query);
			ResultSetMetaData rsm = rs.getMetaData();//getMetaData info sur la table resultat

			int cols = rsm.getColumnCount();
			rs.last();//se place a la fin
			int rows = rs.getRow();//return le numero de ligne courante
			rs.beforeFirst();//retour a notre place
			String data[][]=null;
			if(rows>0)	{
				data = new String[rows + 1][cols];
			}
			else {
				data= new String[1][cols];
			}

			//premiere ligne avec les noms des champs
			for (int i = 0; i < cols; i++) {
				data[0][i] = rsm.getColumnName(i + 1);
			}

			int row = 0;

			while(rs.next())
			{
				row++;
				for (int i = 0; i < cols; i++) {
					data[row][i] = rs.getString(i + 1);
				}
				//System.out.println(rs.getString(1) + " - " + rs.getString(2));
			}

			return data;

			//sql.executeUpdate(); requete DML de mise a jour ( Update, Delete,  )
			//sql.executequerry Select
			//sql.execute();  requetes DDL(create, alter, drop, use, ...)

		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#select(java.lang.String)
	 */
	@Override
	public String[][] select(String tableName) throws RemoteException{
		return executeQuery("SELECT * FROM "+delimit(tableName));
	}


	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#select(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public String[][] select(String tableName, String key, Object value) throws RemoteException{

		String query = "SELECT * FROM "+delimit(tableName)+" WHERE "+delimit(key)+"  ='"+value+"'";
		return executeQuery(query);
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#selectLike(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String[][] selectLike(String tableName, String key, String value) throws RemoteException{

		String query = "SELECT * FROM "+delimit(tableName)+" WHERE "+delimit(key)+"  like '%"+value+"%'";
		return executeQuery(query);
	}


	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#Insert(java.lang.String, java.lang.Object)
	 */
	@Override
	public int Insert(String tableName, Object ...row) throws RemoteException
	{
		try {
			Statement sql = db.createStatement();
			StringBuffer query = new StringBuffer("INSERT INTO "+delimit(tableName)+" VALUES( '"+row[0]+"' ");

			for (int i = 1; i < row.length; i++) {
				query.append(",'"+row[i]+"' ");
			}
			query.append(" );");

			return sql.executeUpdate(query.toString());	
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#getColumnNames(java.lang.String)
	 */
	@Override
	public String[] getColumnNames(String tableName) throws RemoteException
	{
		try {
			Statement sql = db.createStatement();
			ResultSet rs = sql.executeQuery("SELECT * FROM "+delimit(tableName));
			ResultSetMetaData rsm = rs.getMetaData();//getMetaData info sur la table resultat
			int cols = rsm.getColumnCount();

			String columns[] = new String[cols];
			for (int i = 0; i < cols; i++) {
				columns[i] = rsm.getColumnName(i + 1);
			}

			rs.close();
			return columns;
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			return null;
		}
	}


	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#UpdateQuery(java.lang.String)
	 */
	@Override
	public int UpdateQuery(String query)  throws RemoteException{
		try {
			Statement sql = db.createStatement();

			return sql.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}


	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#Update(java.lang.String, java.lang.Object)
	 */
	@Override
	public int Update(String tableName, Object ...row) throws RemoteException
	{
		tableName = delimit(tableName);
		try {
			Statement sql = db.createStatement();
			String cols[] = getColumnNames(delimit(tableName));

			StringBuffer query = new StringBuffer("UPDATE "+tableName+" SET "+delimit(cols[1])+" = '"+row[1]+"'");

			for (int i = 2; i < cols.length; i++) {
				query.append(", "+ delimit(cols[i]) + " ='"+row[i]+"' ");
			}

			query.append(" WHERE "+delimit(cols[0])+" = '"+row[0]+ "' ");

			return sql.executeUpdate(query.toString());
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			return -1;
		}
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#Delete(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public int Delete(String tableName, String key, Object value) throws RemoteException
	{
		try {
			Statement sql = db.createStatement();
			String query = "DELETE FROM "+delimit(tableName)+" WHERE "+delimit(key)+" = '"+value+"'";

			return sql.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.server.gustaveeiffel.upem.datasource.IDatabasee#getTableNames()
	 */
	@Override
	public String[] getTableNames()  throws RemoteException{
		try {
			ResultSet rs = dbm.getTables(null, null, null, new String[] {"TABLE"/*, "VIEW", "SYSTEM TABLE"*/});

			/*
			int n = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= n; i++) {
				System.out.println(rs.getMetaData().getColumnName(i));
			}*/

			Vector<String> v = new Vector<>();
			while(rs.next()) {
				v.add(rs.getString(3));
			}
			String t[] = new String[v.size()];
			v.toArray(t);

			return t;
		} catch (Exception e) {
			System.out.println("Error : "+e.getMessage());
			return null;
		}
	}
}
