package com.server.launch;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import com.common.gustaveeiffel.upem.ProduitService;
import com.common.gustaveeiffel.upem.RoleService;
import com.common.gustaveeiffel.upem.TypeService;
import com.common.gustaveeiffel.upem.UtilisateurService;
import com.server.gustaveeiffel.upem.Dao.Config;
import com.server.gustaveeiffel.upem.Dao.Iconfig;
import com.server.gustaveeiffel.upem.business.ProduitServiceDefault;
import com.server.gustaveeiffel.upem.business.RoleServiceDefault;
import com.server.gustaveeiffel.upem.business.TypeServiceDefault;
import com.server.gustaveeiffel.upem.business.UtilisateurServiceDefault;

public class ServerLaunch {


	@SuppressWarnings("deprecation")
	public ServerLaunch() throws RemoteException  {
		
		try {

		//	System.setProperty("java.security.policy","file:/C:\\Users\\ayoub\\OneDrive\\Desktop\\Lab\\JustLearn\\Java\\ShareApp-Spring-2ed\\src\\java.policy");
		//	System.setProperty("java.security.policy","file:/C:\\Users\\ayoub\\OneDrive\\Desktop\\Lab\\JustLearn\\Java\\Rmi\\GustaveEiffel\\src\\java.policy");
			String policypath= "file:/C:\\Users\\ayoub\\OneDrive\\Desktop\\Lab\\JustLearn\\Java\\ShareApp-Spring-2ed\\src\\java.policy";
			System.setProperty("java.security.policy",policypath);
			
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new 
						RMISecurityManager());
			}
			UtilisateurService serviceutil= new UtilisateurServiceDefault();
			ProduitService serviceproduit = new ProduitServiceDefault();
			RoleService servicerole = new RoleServiceDefault();
			TypeService servicetype = new TypeServiceDefault();
			LocateRegistry.createRegistry(1099);
			Naming.rebind("utilservice", serviceutil);
			Naming.rebind("produitservice", serviceproduit);
			Naming.rebind("roleservice",servicerole);
			Naming.rebind("typeservice",servicetype);
			UtilisateurService stubutil = (UtilisateurService) UnicastRemoteObject.exportObject(serviceutil, 1099);
			ProduitService stubproduit = (ProduitService) UnicastRemoteObject.exportObject(serviceproduit, 1099);
			RoleService stubrole = (RoleService) UnicastRemoteObject.exportObject(servicerole,1099);
			TypeService stubtype = (TypeService) UnicastRemoteObject.exportObject(servicetype,1099);
			



			System.out.println("[+]Server Launched[+]");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws RemoteException {
		new ServerLaunch();
	}
}