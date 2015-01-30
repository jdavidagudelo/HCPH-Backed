package com.artica.telesalud.tph.controller.exception;

import java.util.List;

public class InvalidRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<FieldErrorResource> errors;
    public InvalidRequestException(String message, List<FieldErrorResource> errors)
    {
    	super(message);
    	this.errors = errors;
    }

	/**
	 * @return the errors
	 */
	public List<FieldErrorResource> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<FieldErrorResource> errors) {
		this.errors = errors;
	}
    
}
