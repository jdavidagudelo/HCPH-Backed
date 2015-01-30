package com.artica.telesalud.tph.message.service.interactions;

import org.jdom2.Attribute;
import org.jdom2.Document;

import com.artica.telesalud.tph.message.service.properties.IN000031UV01XPathProperties;
/**
 * Esta clase permite la creacion y el envio de mensajes en formato HL7
 * asociados a la interaccion matricular paciente, esta clase utiliza la
 * plantilla XML identificada con el codigo RCMR_IN000031UV01, y a partir de
 * lainformacion de la base de datos completa la informacion requerida en
 * el mensaje.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class IN000031UV01XMLParser extends XMLParser{
	private IN000031UV01XMLParser() {
	}

	private static final IN000031UV01XPathProperties XPATH_PROPERTIES = IN000031UV01XPathProperties
			.getInstance();
	private static final IN000031UV01XMLParser INSTANCE = new IN000031UV01XMLParser();
	public static IN000031UV01XMLParser getInstance()
	{
		return INSTANCE;
	}
	public Attribute getIdExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathIdExtension());
	}
	public Attribute getCreationTimeValue(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathCreationTimeValue());
	}
	public Attribute getInteractionIdExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathInteractionIdExtension());
	}
	public Attribute getXpathProccessingCodeCode(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathProccessingCodeCode());
	}
	public Attribute getProccessingModeCodeCode(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathProccessingModeCodeCode());
	}
	public Attribute getControlActProcessQueryByParameterQueryIdExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterQueryIdExtension());
	}
	public Attribute getControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueLowValue(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueLowValue());
	}
	public Attribute getControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueHighValue(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterEncompassingEncounterEffectiveTimeValueHighValue());
	}
	public Attribute getControlActProcessQueryByParameterEncompassingEncounterIdValueExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterEncompassingEncounterIdValueExtension());
	}
	public Attribute getControlActProcessQueryByParameterPatientIdValueExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPatientIdValueExtension());
	}
	public Attribute getControlActProcessQueryByParameterPatientLivingSubjectAdministrativeGenderCodeRealmCodeCode(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPatientLivingSubjectAdministrativeGenderCodeRealmCodeCode());
	}
	public Attribute getControlActProcessQueryByParameterPatientLivingSubjectIdValueExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPatientLivingSubjectIdValueExtension());
	}
	public Attribute getReceiverDeviceIdExtension(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathReceiverDeviceIdExtension());
	}
	
	public Attribute getReceiverDeviceSoftwareNameDisplayName(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathReceiverDeviceSoftwareNameDisplayName());
	}
	public Attribute getSenderDeviceIdExtension(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceIdExtension());
	}
	public Attribute getSenderDeviceSoftwareNameDisplayName(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceSoftwareNameDisplayName());
	}
	public Attribute getControlActProcessQueryByParameterPatientIdValueRoot(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPatientIdValueRoot());
	}
}
