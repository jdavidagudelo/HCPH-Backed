package com.artica.telesalud.tph.controller.constants;
/**
 * Contiene las URIs de los servicios web RESTFul definidos en la clase MessageController.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public interface MessageRestURIConstants {
	public static final String MESSAGE_SERVICE_URI="MessageController";
	public static final String MESSAGE_SERVICE_INTEROPERAR_PACIENTE_URI="/interoperarPaciente/{lesionadoId}";
	public static final String MESSAGE_SERVICE_INTEROPERAR_PACIENTE_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String MESSAGE_SERVICE_SEND_EMR_DOCUMENT_URI="/sendEMRDocument/{encounterId}";
	public static final String MESSAGE_SERVICE_SEND_EMR_DOCUMENT_PDF_URI="/sendEMRDocumentPdf/{encounterId}";
	public static final String MESSAGE_SERVICE_SEND_EMR_DOCUMENT_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String MESSAGE_SERVICE_SEND_EMR_DOCUMENT_PDF_ENCOUNTER_ID_PARAM_NAME="encounterId";
	public static final String MESSAGE_SERVICE_REQUEST_ANTECEDENTES_PACIENTE_PATIENT_ID_URI="/requestAntecedentesPaciente/{patientId}";
	public static final String MESSAGE_SERVICE_REQUEST_ANTECEDENTES_PACIENTE_IDENTIFIER_URI="/requestAntecedentesPaciente/{identifier}/{identifierType}/{gender}";
	public static final String MESSAGE_SERVICE_REQUEST_ANTECEDENTES_PATIENT_ID_PARAM_NAME="patientId";
	public static final String MESSAGE_SERVICE_REQUEST_ANTECEDENTES_IDENTIFIER_PARAM_NAME="identifier";
	public static final String MESSAGE_SERVICE_REQUEST_ANTECEDENTES_IDENTIFIER_TYPE_PARAM_NAME="identifierType";
	public static final String MESSAGE_SERVICE_REQUEST_ANTECEDENTES_GENDER_PARAM_NAME="gender";
	public static final String MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_URI="/getAntecedentesAmp/{lesionadoId}";
	public static final String MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_IDENTIFIER_URI="/getAntecedentesAmpIdentifier/{identifier}/{identifierTypeId}";
	public static final String MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_IDENTIFIER_IDENTIFIER_PARAM_NAME="identifier";
	public static final String MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_IDENTIFIER_IDENTIFIER_TYPE_ID_PARAM_NAME="identifierTypeId";
	public static final String MESSAGE_SERVICE_GET_EHRS_URI="/getEhrs";
	
	
	public static final String MESSAGE_SERVICE_GET_ANTECEDENTES_AMP_LESIONADO_ID_PARAM_NAME="lesionadoId";
	public static final String MESSAGE_SERVICE_GET_PDF_DOCUMENT_URI="/getPdfDocument/{lesionadoId}";
	public static final String MESSAGE_SERVICE_GET_PDF_DOCUMENT_LESIONADO_ID_PARAM_NAME="lesionadoId";
}
