package com.artica.telesalud.client.ui;


public class StatusMessenger {
	private ArticaMsgBox msgBox;
	
	private static StatusMessenger instance = new StatusMessenger();
	
	public static StatusMessenger getInstance(){
		return instance;
	}

	private StatusMessenger(){
		msgBox = new ArticaMsgBox();
		msgBox.setLocation(ArticaMsgBox.HORIZONTAL_LOCATION_RIGHT, ArticaMsgBox.VERTICAL_LOCATION_BOTTOM);
	}
	
	public void showErrorMessage(String msg){
		if( msg != null )
			msgBox.showMessage(msg, MessageBoxType.Error, false);
		else
			msgBox.hide();
	}
	
//	public void hideErrors(){
//		msgBox.hide();
//	}
}
