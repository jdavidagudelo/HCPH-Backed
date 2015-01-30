package com.artica.telesalud.tph.controller.exception;

import org.springframework.security.core.AuthenticationException;
/**
 * 
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class DefaultAuthenticationException extends AuthenticationException{

	public DefaultAuthenticationException(String msg) {
		super(msg);
	}
	private static final long serialVersionUID = 1L;

}
