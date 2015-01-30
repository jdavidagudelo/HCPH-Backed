package com.artica.telesalud.tph.controller.constants;
/**
 * Contiene las URIs de los servicios web RESTFul definidos en la clase ResponsableAtencionController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public interface ResponsableAtencionRestURIConstants {
	public static final String RESPONSABLE_ATENCION_SERVICE_URI="ResponsableAtencionController";
	public static final String RESPONSABLE_ATENCION_SERVICE_GET_RESPONSABLES_ATENCION_URI= "/getResponsablesAtencion/{lesionadoId}";
	public static final String RESPONSABLE_ATENCION_SERVICE_GET_RESPONSABLES_ATENCION_LESIONADO_ID_PARAM_NAME= "lesionadoId";
	public static final String RESPONSABLE_ATENCION_SERVICE_SAVE_RESPONSABLE_ATENCION_URI="/saveResponsableAtencion/{userId}";
	public static final String RESPONSABLE_ATENCION_SERVICE_SAVE_RESPONSABLE_ATENCION_USER_ID_PARAM_NAME="userId";
	public static final String RESPONSABLE_ATENCION_SERVICE_GET_USERS_URI="/getUsers";
	
}
