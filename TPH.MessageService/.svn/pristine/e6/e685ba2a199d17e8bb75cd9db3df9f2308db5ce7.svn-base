package com.artica.telesalud.tph.message.service.interactions;

import org.jdom2.Attribute;
import org.jdom2.Document;

import com.artica.telesalud.tph.message.service.properties.IN900300UV02XPathProperties;

/**
 * Esta clase permite la creacion y el envio de mensajes en formato HL7
 * asociados a la interaccion matricular paciente, esta clase utiliza la
 * plantilla XML identificada con el codigo RCMR_IN900300UV02, y a partir de
 * lainformacion de la base de datos completa la informacion requerida en
 * el mensaje.
 * @author Juan David Agudelo Alvarez jdaaa2009@gmail.com
 *
 */
public class IN900300UV02XMLParser extends XMLParser{
	private IN900300UV02XMLParser() {
	}

	private static final IN900300UV02XPathProperties XPATH_PROPERTIES = IN900300UV02XPathProperties
			.getInstance();
	private static final IN900300UV02XMLParser INSTANCE = new IN900300UV02XMLParser();

	public static IN900300UV02XMLParser getInstance() {
		return INSTANCE;
	}
	public Attribute getControlActProcessQueryByParameterPayloadEncounterTimeframeValueLowValue(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPayloadEncounterTimeframeValueLowValue());
	}
	public Attribute getControlActProcessQueryByParameterPayloadEncounterTimeframeValueHighValue(Document document)
	{
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPayloadEncounterTimeframeValueHighValue());
		}
	
	public Attribute getControlActProcessQueryByParameterPayloadPatientIdValueExtension(Document document){
		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPayloadPatientIdValueExtension());
	}
	public Attribute getSenderDeviceIdExtension(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceIdExtension());
	}
	public Attribute getSenderDeviceSoftwareNameDisplayName(Document document) {
		return getAttribute(document, XPATH_PROPERTIES.getXpathSenderDeviceSoftwareNameDisplayName());
	}
	public Attribute getControlActProcessQueryByParameterPayloadPatientIdValueRoot(Document document)
	{

		return getAttribute(document, XPATH_PROPERTIES.getXpathControlActProcessQueryByParameterPayloadPatientIdValueRoot());
	}
}
