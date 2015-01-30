package com.artica.telesalud.tph.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.dao.CityDAO;
import com.artica.telesalud.tph.dao.EncounterDAO;
import com.artica.telesalud.tph.dao.PersonDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.patient.Patient;

public class EncounterService {

	private EncounterDAO encounterDAO;
	private CityDAO cityDAO;
	private PersonDAO personDAO;
	
	public EncounterService(String daoClassName, HashMap<String, String> params){
		
		encounterDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
				daoClassName, params).getEncounterDAO();
		personDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
				daoClassName, params).getPersonDAO();
		cityDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
				daoClassName, params).getCityDAO();
		 
	}
	
	public Encounter getEncounterById(Integer encounterId) {
		return encounterDAO.getEncounter(encounterId);
	}
	
	public Encounter createEmergencyEncounter(Patient patient, Integer providerId, Integer locationId, Integer creator) throws TelesaludException {
		
		ConceptsCode codes = new ConceptsCode();
		
		Encounter encounter = this.newBlankEncounter();
		encounter.setEncounterType(encounterDAO.getEncounterType(codes.cEncuentroEmergencia()));
		encounter.setPatient(patient);
		if(providerId!=null)
			encounter.setProvider(personDAO.findPerson(providerId));
		
		if(locationId!=null)
			encounter.setLocation(cityDAO.getCity(locationId));
		encounter.setEncounterDatetime(new Date());
		
		return encounterDAO.insert(encounter, creator);
	}
	
	public Encounter newBlankEncounter(){
		return encounterDAO.newEncounter();
	}
	
	public List<Encounter> obtenerEncuentros(Patient paciente, int maxResult, int offset){
		return encounterDAO.getEncounters(paciente, maxResult, offset);
	}
	
	public Long getTotalEncuentros(Patient patient){
		return encounterDAO.getTotalEncounters(patient);
	}
	
	public void finishEncounter(Integer encounter){
		encounterDAO.finish(encounter);
	}
	
	public void cerrarEncuentro(Encounter encounter, Integer usuario){
		encounter.setChangedBy(usuario);
		encounter.setState(Encounter.STATE_TERMINATED);
		encounterDAO.update(encounter);
	}
	public List<Encounter> getEncounters(Patient patient, Date high, Date low)
	{
		return encounterDAO.getEncounters(patient, high, low);
	}
	public List<Encounter> getAll()
	{
		return encounterDAO.getAll();
	}
}
