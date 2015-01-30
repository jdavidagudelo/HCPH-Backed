package com.artica.telesalud.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class ArticaCallback<T> implements AsyncCallback<T> {

	private RPCCall<T> call;
		
	@Override
	public void onFailure(Throwable caught) {
				
	}

	@Override
	public void onSuccess(T result) {
		call.afterTask(result);
	}
	
	public void setCall(RPCCall<T> call){
		this.call = call;
	}
	
	public abstract void onReturn(T result);

}
