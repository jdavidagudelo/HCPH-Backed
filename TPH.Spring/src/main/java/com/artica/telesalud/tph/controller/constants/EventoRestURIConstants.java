package com.artica.telesalud.tph.controller.constants;

/**
 * Interface que contiene las URIs de los servicios web RESTFul generados en la clase
 * EventoController.
 * @author interoperabilidad
 *
 */
public interface EventoRestURIConstants {
	public static final String EVENTO_SERVICE_GET_EVENTO_URI="/getEvento/{eventoId}";
	public static final String EVENTO_SERVICE_GET_EVENTO_EVENTO_ID_PARAM_NAME="eventoId";
	public static final String EVENTO_SERVICE_URI = "EventoController";
	public static final String EVENTO_SERVICE_SAVE_EVENTO_URI = "/saveEvento/{userId}";
	public static final String EVENTO_SERVICE_SAVE_EVENTO_USER_ID_PARAM_NAME="userId";
	public static final String EVENTO_SERVICE_GET_EVENTOS_URI = "/getEventos/{userId}";
	public static final String EVENTO_SERVICE_GET_EVENTOS_USER_ID_PARAM_NAME = "userId";
	public static final String EVENTO_SERVICE_FINALIZAR_EVENTO_URI="/finalizarEvento/{eventId}";
	public static final String EVENTO_SERVICE_FINALIZAR_EVENTO_EVENT_ID_PARAM_NAME="eventId";

}
