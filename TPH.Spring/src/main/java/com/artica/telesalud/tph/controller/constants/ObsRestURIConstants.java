package com.artica.telesalud.tph.controller.constants;
/**
 * Contiene las URIs de los servicios web RESTFul definidos en la clase ObsController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public interface ObsRestURIConstants {
	public static final String OBS_SERVICE_URI="ObsController";
	
	public static final String OBS_SERVICE_GET_ANTECEDENTES_URI="/getAntecedentes/{patientId}";
	public static final String OBS_SERVICE_GET_ANTECEDENTES_PATIENT_ID_PARAM_NAME="patientId";
	public static final String OBS_SERVICE_GET_PROCEDIMIENTOS_URI="/getProcedimientos/{encounterId}";
	public static final String OBS_SERVICE_GET_PROCEDIMIENTOS_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_SIGNOS_VITALES_URI="/getSignosVitales/{encounterId}";
	public static final String OBS_SERVICE_GET_SIGNOS_VITALES_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_EVALUACION_URI="/getEvaluacion/{encounterId}";
	public static final String OBS_SERVICE_GET_EVALUACION_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_SAVE_ANTECEDENTE_URI="/saveAntecedente/{lesionadoId}/{userId}";
	public static final String OBS_SERVICE_SAVE_ANTECEDENTE_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String OBS_SERVICE_SAVE_ANTECEDENTE_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_PROCEDIMIENTOS_URI="/saveProcedimientos/{userId}/{obsParentId}";
	public static final String OBS_SERVICE_SAVE_PROCEDIMIENTOS_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_PROCEDIMIENTOS_OBS_PARENT_ID_PARAM_NAME="obsParentId";
	public static final String OBS_SERVICE_SAVE_EVALUACION_URI="/saveEvaluacion/{userId}";
	public static final String OBS_SERVICE_SAVE_EVALUACION_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_GET_HALLAZGOS_URI="/getHallazgos/{encounterId}";
	public static final String OBS_SERVICE_GET_HALLAZGOS_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_CIERRE_ATENCION_URI="/getCierreAtencion/{encounterId}";
	public static final String OBS_SERVICE_GET_CIERRE_ATENCION_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_TELEASISTENCIAS_URI="/getTeleasistencias/{encounterId}";
	public static final String OBS_SERVICE_GET_TELEASISTENCIAS_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_RESULTADO_URI="/getResultado/{lesionadoId}";
	public static final String OBS_SERVICE_GET_RESULTADO_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String OBS_SERVICE_GET_NOTAS_ACLARATORIAS_URI="/getNotasAclaratorias/{lesionadoId}";
	public static final String OBS_SERVICE_GET_NOTAS_ACLARATORIAS_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String OBS_SERVICE_SAVE_SOLICITUD_TELLEASISTENCIA_URI="/saveSolicitudTeleasistencia/{lesionadoId}/{userId}";
	public static final String OBS_SERVICE_SAVE_SOLICITUD_TELEASISTENCIA_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String OBS_SERVICE_SAVE_SOLICITUD_TELEASISTENCIA_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_NOTA_EVOLUCION_URI="/saveNotaEvolucion/{lesionadoId}/{teleasistenciaId}/{userId}";
	public static final String OBS_SERVICE_SAVE_NOTA_EVOLUCION_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String OBS_SERVICE_SAVE_NOTA_EVOLUCION_TELEASISTENCIA_ID_PARAM_NAME="teleasistenciaId";
	public static final String OBS_SERVICE_SAVE_NOTA_EVOLUCION_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_NOTA_ACLARATORIA_URI="/saveNotaAclaratoria/{userId}";
	public static final String OBS_SERVICE_SAVE_NOTA_ACLARATORIA_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String OBS_SERVICE_SAVE_NOTA_ACLARATORIA_NOTA_PARAM_NAME="nota";
	public static final String OBS_SERVICE_SAVE_NOTA_ACLARATORIA_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_CREATE_EVALUACION_URI ="/createEvaluacion/{encounterId}/{userId}";
	public static final String OBS_SERVICE_CREATE_EVALUACION_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_CREATE_EVALUACION_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_GET_EVALUACIONES_URI = "/getEvaluaciones/{encounterId}";
	public static final String OBS_SERVICE_GET_EVALUACIONES_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_HALLAZGOS_EVALUACION_URI="/getHallazgosEvaluacion/{encounterId}/{evaluacionId}";
	public static final String OBS_SERVICE_GET_HALLAZGOS_EVALUACION_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String OBS_SERVICE_GET_HALLAZGOS_EVALUACION_EVALUACION_ID_PARAM_NAME="evaluacionId";
	public static final String OBS_SERVICE_SAVE_EVALUACION_HISTORICO_URI="/saveEvaluacionHistorico/{userId}/{obsParentId}";
	public static final String OBS_SERVICE_SAVE_EVALUACION_HISTORICO_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_EVALUACION_HISTORICO_OBS_PARENT_ID_PARAM_NAME="obsParentId";
	public static final String OBS_SERVICE_SAVE_PROCEDIMIENTO_HISTORICO_URI="/saveProcedimientoHitorico/{userId}/{obsParentId}";
	public static final String OBS_SERVICE_SAVE_PROCEDIMIENTO_HISTORICO_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_PROCEDIMIENTO_HISTORICO_OBS_PARENT_ID_PARAM_NAME = "obsParentId";
	public static final String OBS_SERVICE_SAVE_HALLAZGO_HISTORICO_URI="/saveHallazgoHistorico/{userId}/{obsParentId}";
	public static final String OBS_SERVICE_SAVE_HALLAZGO_HISTORICO_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_HALLAZGO_HISTORICO_OBS_PARENT_ID_PARAM_NAME = "obsParentId";
	public static final String OBS_SERVICE_SAVE_SIGNOS_VITALES_URI="/saveSignosVitales/{userId}";
	public static final String OBS_SERVICE_SAVE_SIGNOS_VITALES_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_INSUMO_URI="/saveInsumo/{userId}/{obsParentId}";
	public static final String OBS_SERVICE_SAVE_INSUMO_USER_ID_PARAM_NAME="userId";
	public static final String OBS_SERVICE_SAVE_INSUMO_OBS_PARENT_ID_PARAM_NAME="obsParentId";
	
}
