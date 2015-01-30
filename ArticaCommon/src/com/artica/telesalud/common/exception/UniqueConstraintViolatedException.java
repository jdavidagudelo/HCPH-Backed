package com.artica.telesalud.common.exception;

public class UniqueConstraintViolatedException extends TelesaludException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UniqueConstraintViolatedException(String msg){
		super(msg);
	}
}
