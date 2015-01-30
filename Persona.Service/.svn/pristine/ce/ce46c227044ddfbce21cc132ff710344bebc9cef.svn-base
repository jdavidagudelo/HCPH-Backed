package com.artica.telesalud.persona.service.user;

import com.artica.telesalud.common.exception.TelesaludException;

public class UserNotFoundException extends TelesaludException{

	public UserNotFoundException(String msg) {
		super(msg);

		
	}

	public UserNotFoundException(String msg, String user) {
		super(msg);
		
		userName = user;

		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	

	public String getUserName() {
		return userName;
	}
}
