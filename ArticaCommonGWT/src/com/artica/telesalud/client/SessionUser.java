package com.artica.telesalud.client;

import com.google.gwt.i18n.client.Dictionary;

public class SessionUser {
	
	private static SessionUser user;
	
	public static SessionUser getInstance(){
		if( user == null )
			user = new SessionUser();
		
		return user;
	}
	
	private Integer userId;
	private String userName;
	private Integer role;
	private Integer personId;
	private String completeName;
	private Integer locationId;
	private Integer speciality;
	
	
	protected SessionUser(){
		userId = 0;
		userName = "";
		role = 0;
		personId = 0;
		completeName = "";
		locationId = 0;
		speciality = 0;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getCompleteName() {
		return completeName;
	}
	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}
	
	public static void setUpFromDictionary(Dictionary var){
		SessionUser user = SessionUser.getInstance();
		user.setUserId( Integer.parseInt(var.get("userId")));
		user.setUserName(var.get("userName"));
		user.setRole(Integer.parseInt(var.get("role")));
		user.setPersonId(Integer.parseInt(var.get("personId")));
		user.setCompleteName(var.get("completeName"));
		
		if(!var.get("location").equals("null"))
			user.setLocationId(Integer.parseInt(var.get("location")));
		
		if(!var.get("speciality").equals("null"))
			user.setSpeciality(Integer.parseInt(var.get("speciality")));
	}
	
	public static void endSession(){
		endSession(false);
	}
	
	public static void endSession(boolean reload){		
		user = null;
		if(reload)
			reload();
	}
	
	public boolean isRole(Integer role){
		return this.getRole().equals(role);
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public Integer getSpeciality() { 
		return speciality;
	}

	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}
	
	private static native void reload() /*-{
    	$wnd.location.reload();
	}-*/; 
	
}
