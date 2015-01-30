package com.artica.telesalud.tph.message.service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.artica.telesalud.tph.message.service.exception.InvalidPatientException;
import com.artica.telesalud.tph.message.service.properties.DocumentTypeProperties;
import com.artica.telesalud.tph.message.service.properties.XMLPathProperties;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.person.Person;
/**
 * Clase utilizada para obtener los valores de los atributos y elementos requeridos en los
 * mensajes HL7 V3 generados por el sistema.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class HL7Formatter {
	private HL7Formatter() {
	}
	private static String EMPTY_STRING = "";
	private static final HL7Formatter INSTANCE = new HL7Formatter();
	private static final DocumentTypeProperties DOCUMENT_TYPE = DocumentTypeProperties.getInstance();
	public static HL7Formatter getInstance() {
		return INSTANCE;
	}

	/**
	 * Metodo utilizado para generar el UUID de un mensaje utilizando la libreria Java requerida para este proposito.
	 * @return String que corresponde a un identificador unico generado aleatoriamente.
	 */
	public String getUuidMensaje() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Metodo utilizado para generar un UUID para el contenido de un mensaje.
	 * @return String que corresponde a un identificador unico generado aleatoriamente.
	 */
	public String getUuidContenidoMensaje() {
		return UUID.randomUUID().toString();
	}
	/**
	 * Metodo utilizado para generar un UUID para el contenido de un mensaje.
	 * @return String que corresponde a un identificador unico generado aleatoriamente.
	 * @return
	 */
	public String getUuidQueryMensaje() {
		return UUID.randomUUID().toString();
	}
	/**
	 * Metodo usado para obtener el identificador de una persona de admisiones de un hospital.
	 * @param person persona que se desea procesar.
	 * @return identificacion de la persona o String vacio si no existe.
	 */
	public String getAdmitterId(Person person)
	{
		return person.getIdentification() == null? EMPTY_STRING : person.getIdentification();
	}
	/**
	 * Permite obtener el telefono de una persona en formato HL7 v3.
	 * @param person persona para la cual se desea obtener el telefono.
	 * @return el telefono de la person si posee uno, String vacio en caso contrario.
	 */
	public String getAdmitterTelecom(Person person)
	{
		return person.getPreferredAddress().getPhone1() == null?EMPTY_STRING : person.getPreferredAddress().getPhone1();
	}
	/**
	 * Permite obtener el numero de registro de una persona en formato HL7 v3
	 * @param person persona para la que se desea consultar el numero de registro.
	 * @return numero de registro de la persona.
	 */
	public String getAuthorRegister(Person person) {
		StringBuilder sb = new StringBuilder();
		return sb.append(person.getRegistro()).toString();
	}
	/**
	 * Permite obtener la direccion actual de un paciente.
	 * @param patient paciente para el cual se desea consultar la direccion.
	 * @return
	 */
	public String getPatientAddress(Patient patient)
	{
		return patient.getPerson().getPreferredAddress().getAddress() == null? EMPTY_STRING : patient.getPerson().getPreferredAddress().getAddress();
	}
	/**
	 * Permite obtener el telefono del autor de un documento.
	 * @param person autor de un documento.
	 * @return telefono del autor de un documento o String vacio en caso de que no tenga telefono registrado.
	 */
	public String getAuthorTelephon(Person person) {
		return person.getPreferredAddress().getPhone1() == null? EMPTY_STRING : person.getPreferredAddress().getPhone1();
	}

	/**
	 * Metodo para verificar la validez de un paciente.
	 * @param patient paciente que se desea validar.
	 * @return true si el paciente es valido, false en caso contrario.
	 */
	public Boolean validPatient(Patient patient) {
		return patient != null;
	}

	private static final XMLPathProperties XPATH_PROPERTIES = XMLPathProperties
			.getInstance();

	/**
	 * Metodo utilizado para obtener la fecha inicial de un encuentro de emergencias en formato HL7 v3.
	 * @param encounter encuentro de emergencias.
	 * @return fecha de creacion del encuentro en formato yyyyMMddHHmmss.
	 */
	public String getEncounterDateLow(Encounter encounter) {
		return getHL7Date(encounter.getEncounterDatetime());
	}

	/**
	 * Metodo utilizado para obtener la fecha de finalizacion del encuentro de emergencias en formato HL7 v3.
	 * @param encounter encuentro de emergencias.
	 * @return fecha de finalizacion del encuentro de emergencias en formato yyyyMMddHHmmss en caso de que ya haya sido
	 * finalizado o String vacio en caso contrario.
	 */
	public String getEncounterDateHigh(Encounter encounter) {
		if(encounter.getDateChanged() != null)
		{
			if(encounter.getState().equals(XPATH_PROPERTIES.getXmlEncounterStateEnded()))
			{
				return getHL7Date(encounter.getDateChanged());
			}
		}
		return XPATH_PROPERTIES.getXmlEncouterDateEndNotPresent();
	}

	/**
	 * Primer nombre del autor de un documento.
	 * @param person persona que creo un documento.
	 * @return primer nombre del autor de un documento.
	 */
	public String getAuthorGivenName(Person person) {
		return person.getPreferredName().getGivenName();
	}

	/**
	 * Apellido del autor de un documento.
	 * @param person persona para la cual se desea obtener el apellido.
	 * @return apellido de la persona.
	 */
	public String getAuthorFamilyName(Person person) {
		return person.getPreferredName().getFamilyName();
	}
	/**
	 * Obtiene la fecha efectiva de creacion de un paciente en formato HL7 v3
	 * @param date fecha que se desea formatear.
	 * @return fecha efectiva de creacion de un paciente en formato yyyyMMddHHmmss
	 */
	public String getHL7EffectiveTime(Date date)
	{
		return getHL7Date(date);
	}
	/**
	 * Obtiene la fecha de nacimiento de un paciente en formato HL7 v3
	 * @param date fecha que se desea obtener, debe estar en format yyyyMMdd.
	 * @return fecha de nacimiento de un paciente
	 */
	public Date getHL7BirthTime(String date) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				XPATH_PROPERTIES.getXmlTemplateBirthDateFormat());
		return dateFormat.parse(date);
	}
	/**
	 * Obtiene la fecha de nacimiento de un paciente en formato HL7 v3
	 * @param date fecha que se desea formatear
	 * @return fecha de nacimiento de un paciente en formato yyyyMMdd
	 */
	public String getHL7BirthTime(Date date) {
		if (date == null) {
			return XPATH_PROPERTIES.getXmlHl7DateDefault();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				XPATH_PROPERTIES.getXmlTemplateBirthDateFormat());
		StringBuilder sb = new StringBuilder();
		sb.append(dateFormat.format(date));
		return sb.toString();
	}
	/**
	 * Obtiene una fecha a partir del String ingresado como argumento.
	 * @param date fecha que se desea obtener, debe tener el formato yyyyMMddHHmmss.
	 * @return fecha obtenida a partir del argumento especificado.
	 * @throws ParseException
	 */
	public Date getHL7Date(String date) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				XPATH_PROPERTIES.getXmlTemplateDateFormat());
		return dateFormat.parse(date);
	}
	/**
	 * Obtiene una fecha en formato HL7 v3.
	 * @param date fecha que se desea formatear.
	 * @return fecha en el formato yyyyMMddHHmmss.
	 * @throws ParseException
	 */
	public String getHL7Date(Date date) {
		if (date == null) {
			return XPATH_PROPERTIES.getXmlHl7DateDefault();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				XPATH_PROPERTIES.getXmlTemplateDateFormat());
		StringBuilder sb = new StringBuilder();
		sb.append(dateFormat.format(date));
		return sb.toString();
	}

	/**
	 * Permite obtener el codigo del genero de un paciente.
	 * @param patient el paciente para el que se desea consultar el codigo de genero.
	 * @return M para masculino, F para femenino, UN en caso de estar indefinido.
	 * @throws InvalidPatientException
	 */
	public String getGenderCode(Patient patient) throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if (patient.getPerson().getGender() == null) {
			return XPATH_PROPERTIES.getXmlTemplateUnknownGenderValue();
		}
		return String.valueOf(patient.getPerson().getGender().charAt(0))
				.toUpperCase();
	}

	/**
	 * Identificador de la historia clinica del paciente.
	 * @param patient paciente para el cual se desea obtener un identificador.
	 * @return identificador de la historia clinica del paciente.
	 * @throws InvalidPatientException
	 */
	public String getHistoryCode(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if(patient.getPreferredIdentifier().getIdentifier() == null){
			throw new InvalidPatientException("El identificador del paciente no puede ser nulo");
		}
		StringBuilder sb = new StringBuilder().append(patient
				.getPreferredIdentifier().getIdentifier());
		return sb.toString();
	}

	/**
	 * Permite obtener el numero de documento del paciente.
	 * @param patient paciente para el cual se desea obtener el numero de documento.
	 * @return identificador del paciente.
	 * @throws InvalidPatientException
	 */
	public String getIdentifier(Patient patient) throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if(patient.getPreferredIdentifier().getIdentifier() == null)
		{
			throw new InvalidPatientException("El identificador del paciente no puede ser nulo");
		}
		return patient.getPreferredIdentifier().getIdentifier();
	}

	/**
	 * Fecha de creacion del paciente en formato HL7 v3.
	 * @param patient paciente al que se desea obtener la fecha de creacion.
	 * @return fecha de creacion del paciente en la base de datos en formato yyyyMMddHHmmss.
	 * @throws InvalidPatientException
	 */
	public String getFormattedEffectiveTime(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		Date date = patient.getDateCreated();
		return getHL7Date(date);
	}

	/**
	 * Fecha de nacimiento del paciente en formato HL7 v3
	 * @param patient paciente para el que se desea obtener la fecha de nacimiento.
	 * @return fecha de nacimiento del paciente en formato yyyyMMdd.
	 * @throws InvalidPatientException 
	 */
	public String getFormattedBirthTime(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		Date date = patient.getPerson().getBirthdate();
		return getHL7BirthTime(date);
	}
	/**
	 * fecha de creacion del documento en formato HL7 v3.
	 * @return fecha de creacion del documento en formato yyyyMMddHHmmss.
	 */
	public String getFormattedCreationTime() {
		Date date = new Date();
		return getHL7Date(date);
	}

	/**
	 * Primer nombre del paciente.
	 * @param patient paciente para el que se desea obtener el primer nombre.
	 * @return primer nombre del paciente ingresado como argumento.
	 * @throws InvalidPatientException
	 */
	public String getPersonNameGiven(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		return patient.getPerson().getPreferredName().getGivenName();
	}

	/**
	 * Segundo nombre del paciente.
	 * @param patient paciente para el que se desea obtener el segundo nombre.
	 * @return segundo nombre del paciente.
	 * @throws InvalidPatientException
	 */
	public String getPersonNameMiddle(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		return patient.getPerson().getPreferredName().getMiddleName();
	}

	/**
	 * Primer apellido del paciente.
	 * @param patient paciente al que se desea obtener el primer apellido.
	 * @return primer apellido del paciente ingresado como argumento.
	 * @throws InvalidPatientException
	 */
	public String getPersonNameFamily1(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		return patient.getPerson().getPreferredName().getFamilyName();
	}

	/**
	 * Segundo apellido del paciente.
	 * @param patient paciente al que se desea obtener el segundo apellido.
	 * @return segundo apellido del paciente ingresado como argumento.
	 * @throws InvalidPatientException
	 */
	public String getPersonNameFamily2(Patient patient)
			throws InvalidPatientException {
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		return patient.getPerson().getPreferredName().getFamilyName2();
	}

	/**
	 * Nombre del genero de un paciente.
	 * @param patient paciente para el que se desea obtener el nombre del genero.
	 * @return genero del paciente ingresado como argumento. MASCULINO, FEMENINO o UN en caso de estar indefinido.
	 * @throws InvalidPatientException
	 */
	public String getGenderCodeDisplayName(Patient patient)
			throws InvalidPatientException {

		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if (patient.getPerson().getGender() == null) {
			return XPATH_PROPERTIES.getXmlTemplateUnknownGenderValue();
		}
		return (patient.getPerson().getGender()).toUpperCase();
	}

	/**
	 * Codigo del genero ingresado como argumento.
	 * @param gender nombre completo de un genero.
	 * @return codigo del genero. M, F o UN en caso de estar indefinido.
	 */
	public String getGenderCode(String gender){
		if(gender == null || gender.isEmpty()){
			return XPATH_PROPERTIES.getXmlTemplateUnknownGenderValue();
		}
		return String.valueOf(gender.charAt(0)).toUpperCase();
	}
	/**
	 * Permite obtener el identificador del paciente.
	 * @param identifier identificador que se desea validar.
	 * @return identificador valido.
	 * @throws InvalidPatientException
	 */
	public String getPatientIdentifier(String identifier) throws InvalidPatientException{
		if(identifier == null || identifier.isEmpty()){
			throw new InvalidPatientException("El identificador del paciente no puede ser nulo");
		}
		return identifier;
	}
	/**
	 * Metodo utilizado para obtener el codigo HL7 asociado al tipo de identificacion ingresado como argumento.
	 * @param identifierTypeId identificador del tipo de documento de un paciente en la base de datos.
	 * @return el codigo del tipo de identificacion asociado al documento ingresado como argumento.
	 * @throws InvalidPatientException
	 */
	public String getPatientIdentifierType(String identifierTypeId) throws InvalidPatientException{
		if(identifierTypeId == null)
		{
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeAsId()))
		{
			return DOCUMENT_TYPE.getDocumentTypeAsCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeCcId()))
		{
			return DOCUMENT_TYPE.getDocumentTypeCcCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeCeId())){
			return DOCUMENT_TYPE.getDocumentTypeCeCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeMsId())){
			return DOCUMENT_TYPE.getDocumentTypeMsCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeNuId())){
			return DOCUMENT_TYPE.getDocumentTypeNuCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypePaId())){
			return DOCUMENT_TYPE.getDocumentTypePaCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeRcId())){
			return DOCUMENT_TYPE.getDocumentTypeRcCode();
		}
		else if(identifierTypeId.equals(DOCUMENT_TYPE.getDocumentTypeTiId())){
			return DOCUMENT_TYPE.getDocumentTypeTiCode();
		}
		return null;
	}
	/**
	 * Metodo utilizado para obtener el codigo HL7 asociado al tipo de identificacion del paciente ingresado como argumento.
	 * @param patient el paciente para el que se desea validar el tipo de identificacion.
	 * @return
	 * @throws InvalidPatientException
	 */
	public String getPatientIdentifierType(Patient patient) throws InvalidPatientException{
		if (!validPatient(patient)) {
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if(patient.getPreferredIdentifier() == null)
		{
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		if(patient.getPreferredIdentifier().getPatientIdentifierType() == null){
			throw new InvalidPatientException("El paciente no puede ser nulo");
		}
		String identifierTypeId = String.valueOf(patient.getPreferredIdentifier().getPatientIdentifierType().getPatientIdentifierTypeId());
		return getPatientIdentifierType(identifierTypeId);
	}
	/**
	 * Metodo utilizado para obtener el id del tipo de documento con el codigo HL7 especificado como
	 * argumento.
	 * @param patientIdentifierCode codigo del tipo de documento en formato HL7.
	 * @return el id del tipo de documento asociado al codigo HL7 ingresado como argumento.
	 * @throws InvalidPatientException
	 */
	public Integer getPatientIdentifierId(String patientIdentifierCode) throws InvalidPatientException{
		if(patientIdentifierCode == null)
		{
			throw new InvalidPatientException("El codigo del tipo de documento no puede ser nulo");
		}
		if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeAsCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeAsId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeCcCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeCcId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeCeCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeCeId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeMsCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeMsId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeNuCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeNuId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypePaCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypePaId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeRcCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeRcId());
		}
		else if(patientIdentifierCode.equals(DOCUMENT_TYPE.getDocumentTypeTiCode()))
		{
			return Integer.valueOf(DOCUMENT_TYPE.getDocumentTypeTiId());
		}
		return null;
	}
}
