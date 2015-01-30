package com.artica.telesalud.tph.message.service.exception;

import org.apache.log4j.Logger;

/**
 * Esta clase es utilizada para manejar excepciones causadas por el acceso a los
 * datos.
 * 
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 * @version 1
 * */
public class InvalidPatientException extends Exception {
	Logger log = Logger.getLogger(InvalidPatientException.class);

	public InvalidPatientException() {
		super();
		log.error(this.getMessage(), this);
		
	}

	public InvalidPatientException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		log.error(arg0, arg1);
	}

	public InvalidPatientException(String arg0) {
		super(arg0);
		log.error(arg0, this);
	}

	public InvalidPatientException(Throwable arg0) {
		super(arg0);
		log.error(arg0.getMessage(), arg0);
	}

	private static final long serialVersionUID = 1L;

}
