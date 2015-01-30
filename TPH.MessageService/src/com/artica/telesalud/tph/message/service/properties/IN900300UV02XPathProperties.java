package com.artica.telesalud.tph.message.service.properties;

import java.util.ResourceBundle;

public class IN900300UV02XPathProperties {
	
	private IN900300UV02XPathProperties() {
	}

	private static final IN900300UV02XPathProperties INSTANCE = new IN900300UV02XPathProperties();
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.tph.message.service.properties.IN900300UV02XPath");

	public static IN900300UV02XPathProperties getInstance() {
		return INSTANCE;
	}
	
	public String getXpathControlActProcessQueryByParameterPayloadEncounterTimeframeValueLowValue(){
		return CONFIGURATION_RESOURCES.getString("xpath.controlActProcess.queryByParameterPayload.encounterTimeframe.value.low.value");
	}
	public String getXpathControlActProcessQueryByParameterPayloadEncounterTimeframeValueHighValue()
	{
		return CONFIGURATION_RESOURCES.getString("xpath.controlActProcess.queryByParameterPayload.encounterTimeframe.value.high.value");
		}
	
	public String getXpathControlActProcessQueryByParameterPayloadPatientIdValueExtension(){
		return CONFIGURATION_RESOURCES.getString("xpath.controlActProcess.queryByParameterPayload.patientId.value.extension");
	}

	public String getXpathSenderDeviceIdExtension() {
		return CONFIGURATION_RESOURCES.getString("xpath.sender.device.id.extension");		
	}

	public String getXpathSenderDeviceSoftwareNameDisplayName() {
		return CONFIGURATION_RESOURCES.getString("xpath.sender.device.softwareName.displayName");	
	}
	public String getXpathControlActProcessQueryByParameterPayloadPatientIdValueRoot()
	{
		return CONFIGURATION_RESOURCES.getString("xpath.controlActProcess.queryByParameterPayload.patientId.value.root");
	}
}
