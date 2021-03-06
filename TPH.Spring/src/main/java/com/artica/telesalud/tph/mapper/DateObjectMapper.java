package com.artica.telesalud.tph.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * Clase usada para mapear de forma apropiada clases Java a objetos JSON teniendo en cuenta
 * el formato de la fecha.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
public class DateObjectMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DateObjectMapper() {
		//the date generated by the converter is in format 
	    configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);            
	}
}