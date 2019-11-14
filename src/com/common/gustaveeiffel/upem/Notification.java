package com.common.gustaveeiffel.upem;

import java.rmi.RemoteException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Notification {

	private int notificationid;
	private String notification;
	private int utilisateurid;
	private Date dategenerer;
	private boolean vu;
	public Notification(int notificationid, String notification, int utilisateurid, Date dategenerer, boolean vu) {
		super();
		this.notificationid = notificationid;
		this.notification = notification;
		this.utilisateurid = utilisateurid;
		this.dategenerer = dategenerer;
		this.vu = vu;
	}

	public Notification() {

	}
	
	public Notification (String ...row) throws ParseException, RemoteException {
		this.notificationid = toInt(row[0]);
		this.notification = row[1];
		this.utilisateurid = toInt(row[2]);
		java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse(row[3]);
		this.dategenerer = new Date(utilDate.getTime());
		this.vu=(row[4].equals("true")?true:false);
	}
	
	public int getNotificationid() {
		return notificationid;
	}

	public void setNotificationid(int notificationid) {
		this.notificationid = notificationid;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public int getUtilisateurid() {
		return utilisateurid;
	}

	public void setUtilisateurid(int utilisateurid) {
		this.utilisateurid = utilisateurid;
	}

	public Date getDategenerer() {
		return dategenerer;
	}

	public void setDategenerer(Date dategenerer) {
		this.dategenerer = dategenerer;
	}

	public boolean isVu() {
		return vu;
	}

	public void setVu(boolean vu) {
		this.vu = vu;
	}

	public  int toInt(String s)throws RemoteException {
		try {
			return Integer.parseInt(s);
		}
		catch (Exception e) {
			return 0;
		}
	}

}
