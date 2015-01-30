package com.artica.telesalud.shared;


public class TelesaludGWTException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TelesaludGWTException(){
		super();		
	}
	
	public TelesaludGWTException(String msg){
		super(msg);
		
	}
	
	public TelesaludGWTException(Throwable exception){
		super(exception);
		
		exception.printStackTrace();
	}

}
