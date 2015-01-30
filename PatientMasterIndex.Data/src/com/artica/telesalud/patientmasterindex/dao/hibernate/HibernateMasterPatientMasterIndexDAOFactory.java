package com.artica.telesalud.patientmasterindex.dao.hibernate;

import java.util.HashMap;

import com.artica.telesalud.patientmasterindex.dao.EhrDAO;
import com.artica.telesalud.patientmasterindex.dao.PatientMasterIndexDAOFactory;

public class HibernateMasterPatientMasterIndexDAOFactory implements PatientMasterIndexDAOFactory{
	private String configFilePath;
	public static final String RIESGO_HIBERNATE_CONFIG_FILE = "RIESGO_HIBERNATE_CONFIG_FILE"; 

	@Override
	public void configure(HashMap<String, String> params) {
		configFilePath = params.get(RIESGO_HIBERNATE_CONFIG_FILE);
	}
	@Override
	public EhrDAO getEhrDAO() {
		return new HibernateEhrDAO(configFilePath);
	}
}
