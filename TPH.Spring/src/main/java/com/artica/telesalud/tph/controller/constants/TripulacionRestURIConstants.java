package com.artica.telesalud.tph.controller.constants;
/**
 * Contiene las URIs de los servicios web RESTFul definidos en la clase TripulacionController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public interface TripulacionRestURIConstants {
	public static final String TRIPULACION_SERVICE_URI="TripulacionController";
	public static final String TRIPULACION_SERVICE_GET_TRIPULACIONES_EVENTO_URI="/getTripulacionesEvento/{eventoId}";
	public static final String TRIPULACION_SERVICE_GET_TRIPULACIONES_EVENTO_EVENTO_ID_PARAM_NAME="eventoId";
}
