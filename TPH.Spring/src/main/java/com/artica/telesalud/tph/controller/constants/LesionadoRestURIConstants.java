package com.artica.telesalud.tph.controller.constants;
/**
 * Contiene las URIs de los servicios web RESTFul generados en la clase LesionadoController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
public interface LesionadoRestURIConstants {
	public static final String LESIONADO_SERVICE_URI = "LesionadoController";
	public static final String LESIONADO_SERVICE_GET_LESIONADOS_EVENTO_URI = "/getLesionadosEvento/{eventoId}/{userId}";
	public static final String LESIONADO_SERVICE_GET_LESIONADOS_EVENTO_EVENTO_ID_PARAM_NAME = "eventoId";
	public static final String LESIONADO_SERVICE_GET_LESIONADOS_EVENTO_USER_ID_PARAM_NAME="userId";
	public static final String LESIONADO_SERVICE_GET_LESIONADO_URI="/getLesionado/{lesionadoId}";
	public static final String LESIONADO_SERVICE_GET_LESIONADO_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_URI="createLesionado/{eventoId}/{userId}";
	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_EVENTO_ID_PARAM_NAME="eventoId";
	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_USER_ID_PARAM_NAME="userId";
	public static final String LESIONADO_SERVICE_SAVE_LESIONADO_URI="/saveLesionado/{userId}";
	public static final String LESIONADO_SERVICE_CERRAR_HISTORIA_URI="/cerrarHistoria/{userId}";
	public static final String LESIONADO_SERVICE_CERRAR_HISTORIA_USER_ID_PARAM_NAME="userId";
	public static final String LESIONADO_SERVICE_SAVE_PATIENT_USER_ID_PARAM_NAME="userId";

	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_URI="createLesionadoWithLocalId/{eventoId}/{userId}/{eventLocalIdentifier}";
	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_EVENTO_ID_PARAM_NAME="eventoId";
	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_USER_ID_PARAM_NAME="userId";
	public static final String LESIONADO_SERVICE_CREATE_LESIONADO_WITH_LOCAL_ID_EVENT_LOCAL_IDENTIFIER_PARAM_NAME="eventLocalIdentifier";
	public static final String LESIONADO_SERVICE_SAVE_PRIMER_RESPONDIENTE_URI="/saveRespondiente/{userId}";
	public static final String LESIONADO_SERVICE_SAVE_PRIMER_RESPONDIENTE_USER_ID_PARAM_NAME="userId";
	public static final String LESIONADO_SERVICE_GET_EVENTOS_URI = "/getEventos/{userId}";
	public static final String LESIONADO_SERVICE_GET_EVENTOS_USER_ID_PARAM_NAME="userId";
	
}
