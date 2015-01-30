package com.artica.telesalud.common.exception;

import org.apache.log4j.Logger;

public class TelesaludException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	public TelesaludException(){
//		super();	
//		
//		Logger log = Logger.getLogger(TelesaludException.class);
//		log.error("Hubo un error");
//	}
	
	public TelesaludException(String msg){
		super(msg);
		
		Logger log = Logger.getLogger(TelesaludException.class);
		log.error(msg);
		
		System.out.println(msg);
		
	}
	
	public TelesaludException(Throwable exception){
		super(exception);
		
		Logger log = Logger.getLogger(TelesaludException.class);
		log.error(exception.getMessage(), exception);
		
		exception.printStackTrace();
	}
	
	
	public TelesaludException (String msg, Throwable exception, Class<?> clase){
		super(msg, exception);
		
		Logger log = Logger.getLogger(clase);
		log.error(exception.getMessage(), exception);
		
		exception.printStackTrace();
	}

}
