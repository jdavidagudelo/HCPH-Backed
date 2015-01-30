package com.artica.telesalud.tph.dao.hibernate;

import java.util.HashMap;

import com.artica.telesalud.tph.dao.CityDAO;
import com.artica.telesalud.tph.dao.ConceptDAO;
import com.artica.telesalud.tph.dao.ConceptSetDAO;
import com.artica.telesalud.tph.dao.EncounterDAO;
import com.artica.telesalud.tph.dao.EventAddressDAO;
import com.artica.telesalud.tph.dao.EventoDAO;
import com.artica.telesalud.tph.dao.HospitalDAO;
import com.artica.telesalud.tph.dao.LesionadoDAO;
import com.artica.telesalud.tph.dao.LocationDAO;
import com.artica.telesalud.tph.dao.ObsDAO;
import com.artica.telesalud.tph.dao.PatientDAO;
import com.artica.telesalud.tph.dao.PatientIdentifierDAO;
import com.artica.telesalud.tph.dao.PatientIdentifierTypeDAO;
import com.artica.telesalud.tph.dao.PersonAddressDAO;
import com.artica.telesalud.tph.dao.PersonDAO;
import com.artica.telesalud.tph.dao.PersonNameDAO;
import com.artica.telesalud.tph.dao.PrimerRespondienteDAO;
import com.artica.telesalud.tph.dao.ResponsableAtencionDAO;
import com.artica.telesalud.tph.dao.StateProvinceDAO;
import com.artica.telesalud.tph.dao.TPHDAOFactory;
import com.artica.telesalud.tph.dao.TripulacionDAO;
import com.artica.telesalud.tph.dao.UserDAO;

public class HibernateTPHDAOFactory implements TPHDAOFactory {

	private String configFilePath;
	
	public static final String TPH_HIBERNATE_CONFIG_FILE = "RIESGO_HIBERNATE_CONFIG_FILE"; 
	
	
	
	public void configure(HashMap<String, String> params) {
		configFilePath = params.get(TPH_HIBERNATE_CONFIG_FILE);
	}

	
	
	public EventoDAO getEventoDAO() {
		return new HibernateEventoDAO(configFilePath);
	}
	
	
	public ConceptDAO getConceptDAO() {
		return new HibernateConceptDAO(configFilePath);
	}
	
	public StateProvinceDAO getStateProvinceDAO() {
		return new HibernateStateProvinceDAO(configFilePath);
	}

	public CityDAO getCityDAO() {
		return new HibernateCityDAO(configFilePath);
	}
	public EventAddressDAO getEventAddressDAO()
	{
		return new HibernateEventAddressDAO(configFilePath);
	}
	
	public LocationDAO getLocationDAO() {
		return new HibernateLocationDAO(configFilePath);
	}


	
	public TripulacionDAO getTripulacionDAO() {
		return new HibernateTripulacionDAO(configFilePath);
	}
	
	
	public LesionadoDAO getLesionadoDAO() {
		return new HibernateLesionadoDAO(configFilePath);
	}


	
	public PatientDAO getPatientDAO() {
		return new HibernatePatientDAO(configFilePath);
	}


	
	public PatientIdentifierDAO getPatientIdentifierDAO() {
		return new HibernatePatientIdentifierDAO(configFilePath);
	}


	
	public PatientIdentifierTypeDAO getPatientIdentifierTypeDAO() {
		return new HibernatePatientIdentifierTypeDAO(configFilePath);
	}


	
	public PersonNameDAO getPersonNameDAO() {
		return new HibernatePersonaNameDAO(configFilePath);
	}


	
	public PersonDAO getPersonDAO() {
		return new HibernatePersonDAO(configFilePath);
	}


	
	public PersonAddressDAO getPersonAddressDAO() {
		return new HibernatePersonaAddressDAO(configFilePath);
	}


	
	public EncounterDAO getEncounterDAO() {
		return new HibernateEncounterDAO(configFilePath);
	}
	
	
	public ObsDAO getObsDAO(){
		return new HibernateObsDAO(configFilePath);
	}

	
	public PrimerRespondienteDAO getPrimerRespondienteDAO() {
		return new HibernatePrimerRespondienteDAO(configFilePath);
	}

	
	public ResponsableAtencionDAO getResponsableAtencionDAO() {
		return new HibernateResponsableAtencionDAO(configFilePath);
	}

	
	public HospitalDAO getHospitalDAO() {
		return new HibernateHospitalDAO(configFilePath);
	}

	
	public UserDAO getUserDAO() {
		return new HibernateUserDAO(configFilePath);
	}

	
	public ConceptSetDAO getConceptSetDAO() {
		return new HibernateConceptSetDAO(configFilePath);
	}
	

}
