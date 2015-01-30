package com.artica.telesalud.patientmasterindex.service.properties;

import java.util.ResourceBundle;

public class DomainsProperties {
	private DomainsProperties() {

	}

	private static final DomainsProperties INSTANCE = new DomainsProperties();
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.patientmasterindex.service.properties.Domains");

	public static DomainsProperties getInstance() {
		return INSTANCE;
	}
	public String getTelesaludAmp(){
		return CONFIGURATION_RESOURCES.getString("telesalud.amp");
	}
	public String getTelesaludEmr(){
		return CONFIGURATION_RESOURCES.getString("telesalud.emr");
	}
}
