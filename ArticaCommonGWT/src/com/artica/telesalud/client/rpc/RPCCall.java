package com.artica.telesalud.client.rpc;

import com.artica.telesalud.client.SessionUser;
import com.artica.telesalud.client.ui.ArticaMsgBox;
import com.artica.telesalud.client.ui.MessageBoxType;
import com.artica.telesalud.shared.NotAllowedException;
import com.artica.telesalud.shared.SessionInvalidateException;
import com.google.gwt.user.client.rpc.AsyncCallback;


public abstract class RPCCall<T> {
	
	private RPCCallType type;
	private String message;
	
	private static ArticaMsgBox msgBox = new ArticaMsgBox();
	
	public RPCCall(RPCCallType type){
		this.type = type;
	}
	
	public RPCCall(String message) {
		super();
		this.message = message;
	}

	public void beforeTask(){
		if(type==null)
			msgBox.showWaiting(message);
		else if( type == RPCCallType.Save )
			msgBox.showWaiting("Saving...");
		else if( type == RPCCallType.Load )
			msgBox.showWaiting("Loading...");
	}
	
	public void afterTask(T result){
		msgBox.hideWaiting();
	}
	
	
	public abstract void task(AsyncCallback<T> callback);
	
	public void failed(Throwable caught){
		msgBox.hideWaiting();
		caught.printStackTrace();
		
		if( caught instanceof NotAllowedException){
			msgBox.showMessage("No tiene permisos suficientes para realizar esta acción", MessageBoxType.Error);
			return;
		}
		
		if( caught instanceof SessionInvalidateException){
			SessionUser.endSession();
			reload();
			return;
		}
		
		msgBox.showMessage(
				
				"Ha ocurrido una excepción al tratar de completar la solicitud. Intente de nuevo, si el problema persiste consulte al administrador del sistema", MessageBoxType.Error);
	}
	
	public abstract void onReturn(T result);
	
	public void execute(){
		beforeTask();
		
		task(new AsyncCallback<T>() {

			@Override
			public void onFailure(Throwable caught) {
				afterTask(null);
				failed(caught);				
			}

			@Override
			public void onSuccess(T result) {
				onReturn(result);
				afterTask(result);			
			}
		});
	}
	
	private native void reload() /*-{
	    $wnd.location.reload();
	}-*/; 
}
