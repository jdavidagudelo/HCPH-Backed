package com.artica.telesalud.tph.message.service.interactions;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.artica.telesalud.tph.message.service.properties.IN900350UV02XPathProperties;

/**
 * Esta clase permite la creacion y el envio de mensajes en formato HL7
 * asociados a la interaccion matricular paciente, esta clase utiliza la
 * plantilla XML identificada con el codigo RCMR_IN900350UV02, y a partir de
 * lainformacion de la base de datos completa la informacion requerida en
 * el mensaje.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class IN900350UV02XMLParser extends XMLParser{
	private IN900350UV02XMLParser() {
	}

	private static final IN900350UV02XPathProperties XPATH_PROPERTIES = IN900350UV02XPathProperties
			.getInstance();
	private static final IN900350UV02XMLParser INSTANCE = new IN900350UV02XMLParser();

	public static IN900350UV02XMLParser getInstance() {
		return INSTANCE;
	}
	public Attribute getIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathIdExtension());
	}

	public Attribute getCreationTimeValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathCreationTimeValue());
	}

	public Attribute getInteractionIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathInteractionIdExtension());
	}

	public Attribute getProccessingCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathProccessingCodeCode());
	}
	
	public Attribute getProccessingModeCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathProccessingModeCodeCode());
	}

	public Attribute getReceiverDeviceIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathReceiverDeviceIdExtension());
	}

	public Attribute getReceiverDeviceSoftwareNameDisplayName(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathReceiverDeviceSoftwareNameDisplayName());
	}

	public Attribute getSenderDeviceIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceIdExtension());
	}

	public Attribute getSenderDeviceSoftwareNameDisplayName(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceSoftwareNameDisplayName());
	}

	public Attribute getControlActProcessSubjectEncounterEventClassCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventClassCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventMoodCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventMoodCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventIdExtension());
	}

	public Attribute getControlActProcessSubjectEncounterEventCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventCodeCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventCodeCodeSystem(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventCodeCodeSystem());
	}

	public Attribute getControlActProcessSubjectEncounterEventCodeCodeSystemName(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventCodeCodeSystemName());
	}

	public Attribute getControlActProcessSubjectEncounterEventCodeCodeSystemVersion(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventCodeCodeSystemVersion());
	}

	public Attribute getControlActProcessSubjectEncounterEventStatusCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventStatusCodeCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventEffectiveTimeValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventEffectiveTimeValue());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectTypeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectTypeCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientClassCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientClassCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientIdCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientIdCode());
	}

	

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonClassCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonClassCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonDeterminerCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonDeterminerCode());
	}

	public List<Element> getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonNameFamily(Document document) {
		return getElements(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonNameFamily());
	}

	public List<Element> getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonNameGiven(Document document) {
		return getElements(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonNameGiven());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonAdministrativeGenderCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonAdministrativeGenderCodeCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonAdministrativeGenderCodeDisplayName(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonAdministrativeGenderCodeDisplayName());
	}

	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonBirthTimeValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonBirthTimeValue());
	}

	public Attribute getControlActProcessSubjectEncounterEventAdmitterTimeValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventAdmitterTimeValue());
	}

	public Attribute getControlActProcessSubjectEncounterEventAdmitterAssignedPersonClassCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventAdmitterAssignedPersonClassCode());
	}

	public Attribute getControlActProcessSubjectEncounterEventAdmitterAssignedPersonIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventAdmitterAssignedPersonIdExtension());
	}

	public Attribute getControlActProcessSubjectEncounterEventAdmitterAssignedPersonTelecomValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventAdmitterAssignedPersonTelecomValue());
	}

	public Attribute getControlActProcessQueryAckQueryResponseCodeCode(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryAckQueryResponseCodeCode());
	}

	public Attribute getControlActProcessQueryAckResultCurrentQuantityValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryAckResultCurrentQuantityValue());
	}

	public Attribute getControlActProcessQueryAckResultRemainingQuantityValue(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryAckResultRemainingQuantityValue());
	}
	public List<Element> getControlActProcessSubjectEncounterEvent(Document document){
		return getElements(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEvent());
	}
	public List<Element> getControlActProcessSubject(Document document){
		return getElements(document, XPATH_PROPERTIES.getXpathControlActProcessSubject());
	}
	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonIdExtension(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonIdExtension());
	}
	public List<Element> getControlActProcess(Document document)
	{
		return getElements(document, XPATH_PROPERTIES.getXpathControlActProcess());
	}
	public List<Element> getControlActProcessQueryAck(Document document)
	{
		return getElements(document, XPATH_PROPERTIES.getXpathControlActProcessQueryAck());
	}
	public Attribute getControlActProcessSubjectEncounterEventSubjectPatientPatientPersonIdRoot(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectEncounterEventSubjectPatientPatientPersonIdRoot());
	}
}
