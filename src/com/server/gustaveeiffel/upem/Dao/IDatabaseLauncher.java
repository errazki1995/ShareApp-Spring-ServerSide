package com.server.gustaveeiffel.upem.Dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.server.gustaveeiffel.upem.datasource.IDatabase;

public interface IDatabaseLauncher extends Remote{

	IDatabase dbinit() throws RemoteException;

}