package com.artica.telesalud.tph.controller.constants;

/**
 * Interfaz que contiene las URIs de los servicios web RESTFul generados en la
 * clase ConceptController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 *
 */
public interface ConceptRestURIConstants {

	public static final String CONCEPT_SERVICE_OBTENER_CAUSAS_URI = "/obtenerCausasAtencion";
	public static final String CONCEPT_SERVICE_URI = "ConceptController";
	public static final String CONCEPT_SERVICE_GET_ESTADOS_CIVILES_URI="/getEstadosCiviles";
	public static final String CONCEPT_SERVICE_GET_TIPOS_CIERRE_URI = "/getTiposCierre";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_OTROS_URI = "/getProcedimientosOtros";
	public static final String CONCEPT_SERVICE_GET_EXPOSICIONES_URI = "/getExposiciones";
	public static final String CONCEPT_SERVICE_GET_RESPUESTAS_OCULARES_URI="/getRespuestasOculares";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_SOPORTE_VITAL_AVANZADO_URI = "/getProcedimientosSoporteVitalAvanzado";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_SOPORTE_VITAL_BASICO_URI="/getProcedimientosSoporteVitalBasico";
	public static final String CONCEPT_SERVICE_GET_UNIDADES_VOLUMEN_URI="/getUnidadesVolumen";
	public static final String CONCEPT_SERVICE_GET_VIAS_ADMINISTRACION_URI="/getViasAdministracion";
	public static final String CONCEPT_SERVICE_GET_INSUMOS_URI="/getInsumos";
	public static final String CONCEPT_SERVICE_GET_ASEGURADORAS_URI="/getAseguradoras";
	public static final String CONCEPT_SERVICE_GET_EPSS_URI="/getEpss";
	public static final String CONCEPT_SERVICE_GET_PLANES_BENEFICIOS_URI="/getPlanesBeneficios";
	public static final String CONCEPT_SERVICE_GET_TIPOS_ANTECEDENTES_URI="/getTiposAntecedentes";
	public static final String CONCEPT_SERVICE_GET_CONTROLES_APH_URI ="/getControlesAph";
	public static final String CONCEPT_SERVICE_GET_MEDIOS_SOLICITUD_TELEASISTENCIA_URI="/getMediosSolicitudTeleasistencia";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_URI="/getProcedimientos/{query}";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_QUERY_PARAM_NAME="query";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_HALLAZGOS_URI="/getProcedimientosHallazgos/{query}";
	public static final String CONCEPT_SERVICE_GET_PROCEDIMIENTOS_HALLAZGOS_QUERY_PARAM_NAME="query";
	public static final String CONCEPT_SERVICE_GET_ZONAS_URI="/getZonas";
	public static final String CONCEPT_SERVICE_GET_DIAGNOSTICOS_URI="/getDiagnosticos/{query}";
	public static final String CONCEPT_SERVICE_GET_DIAGNOSTICOS_QUERY_PARAM_NAME_URI="query";
	public static final String CONCEPT_SERVICE_GET_LESIONES_URI="/getLesiones";
	public static final String CONCEPT_SERVICE_GET_TIPO_EMERGENCIAS_URI="/getTipoEmergencias";
	public static final String CONCEPT_SERVICE_GET_CAUSAS_OBSTRUCCION_VIA_AEREA_URI="/getCausasObstruccionViaAerea";
	public static final String CONCEPT_SERVICE_GET_PERMEABILIDADES_VIA_AEREA_URI="/getPermeabilidadesViaAerea";
	public static final String CONCEPT_SERVICE_GET_ESTADOS_RESPIRACION_URI="/getEstadosRespiracion";
	public static final String CONCEPT_SERVICE_GET_PRESENCIAS_RESPIRACION_URI="/getPresenciasRespiracion";
	public static final String CONCEPT_SERVICE_GET_CIANOSIS_RESPIRACION_URI="/getCianosisRespiracion";
	public static final String CONCEPT_SERVICE_GET_RESULTADOS_ATENCION_URI="/getResultadosAtencion";
	public static final String CONCEPT_SERVICE_GET_NEGACIONES_PACIENTE_URI="/getNegacionesPaciente";
	public static final String CONCEPT_SERVICE_GET_RESPONDIENTES_URI="/getRespondientes";
}
