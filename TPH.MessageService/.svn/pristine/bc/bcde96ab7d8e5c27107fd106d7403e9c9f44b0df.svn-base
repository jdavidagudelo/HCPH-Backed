package com.artica.telesalud.tph.message.service.interactions;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.artica.telesalud.tph.message.service.properties.IN000002UV01XPathProperties;

/**
 * Esta clase permite la creacion y el envio de mensajes en formato HL7
 * asociados a la interaccion matricular paciente, esta clase utiliza la
 * plantilla XML identificada con el codigo RCMR_IN000002UV01, y a partir de
 * lainformacion de la base de datos completa la informacion requerida en
 * el mensaje.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class IN000002UV01XMLParser extends XMLParser{
	private IN000002UV01XMLParser() {
	}

	private static final IN000002UV01XPathProperties XPATH_PROPERTIES = IN000002UV01XPathProperties
			.getInstance();
	private static final IN000002UV01XMLParser INSTANCE = new IN000002UV01XMLParser();

	public static IN000002UV01XMLParser getInstance() {
		return INSTANCE;
	}
	public List<Element> getControlActProcessText(Document document)
	{
		return getElements(document,
						XPATH_PROPERTIES
								.getXpathControlActProcessText());
	}
	
	public Attribute getIdExtensionAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathIdExtension());
	}

	public Attribute getCreationTimeValueAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathCreationtimeValue());
	}

	public Attribute getInteractionIdValueAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathInteractionIdExtension());
	}

	public Attribute getProccessingCodeCodeAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathProccessingCodeCode());
	}

	public Attribute getProccessingModeCodeCodeAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathProccessingModeCodeCode());
	}

	public List<Element> getControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameGiven(
			Document document) {
		return getElements(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameGiven());
	}

	public List<Element> getControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameFamily(
			Document document) {
		return getElements(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonNameFamily());
	}

	public Attribute getReceiverDeviceIdExtensionAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathReceiverDeviceIdExtension());
	}

	public Attribute getReceiverDeviceSoftwareNameDisplayNameAttribute(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
				.getXpathReceiverDeviceSoftwareNameDisplayName());
	}

	public Attribute getSenderDeviceIdExtensionAttribute(Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathSenderDeviceIdExtension());
	}

	public Attribute getSenderDeviceSoftwareNameDisplayNameAttribute(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES.getXpathSenderDeviceSoftwareNameDisplayName());
	}

	public Attribute getControlActProcessSubjectTargetIdExtensionAttribute(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetIdExtension());
	}

	public Attribute getControlActProcessSubjectTargetCodeCodeAttribute(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetCodeCode());
	}

	public Attribute getControlActProcessSubjectTargetCodeDisplayNameAttribute(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetCodeDisplayName());
	}

	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCode(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCode());
	}

	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCodeSystem(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeCodeSystem());
	}

	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeDisplayName(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonAdministrativeGenderCodeDisplayName());
	}

	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonBirthTimeValue(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonBirthTimeValue());
	}

	public Attribute getControlActProcessSubjectTargetRecordTargetPatientIdExtension(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientIdExtension());
	}

	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdExtension(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
								.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdExtension());
	}

	public Attribute getControlActProcessSubjectTargetEffectiveTimeValue(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetEffectiveTimeValue());
	}

	public Attribute getControlActProcessSubjectTargetAuthorTimeValue(
			Document document) {
		return getAttribute(document,XPATH_PROPERTIES
				.getXpathControlActProcessSubjectTargetAuthorTimeValue());
	}
	public Attribute getControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdRoot(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessSubjectTargetRecordTargetPatientPatientPersonIdRoot());
	}
}
