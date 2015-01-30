package com.artica.telesalud.patientmasterindex.service.properties;

import java.util.ResourceBundle;

public class EhrProperties {
	private EhrProperties() {

	}

	private static final EhrProperties INSTANCE = new EhrProperties();
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.patientmasterindex.service.properties.Ehr");

	public static EhrProperties getInstance() {
		return INSTANCE;
	}

	public String getCoUdeaTelesaludHcemr() {
		return CONFIGURATION_RESOURCES.getString("co.udea.telesalud.hcemr");
	}

	public String getCoUdeaTelesaludHcteleasis() {
		return CONFIGURATION_RESOURCES
				.getString("co.udea.telesalud.hcteleasis");
	}
}
