package com.artica.telesalud.tph.dao;

import java.util.Date;
import java.util.List;

import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.patient.Patient;

public interface ObsDAO {

	List<Obs> getAll();

	Obs insert(Obs obs);

	Obs newObs();

	Obs delete(Obs obs);
	public List<Obs> getObsList(Encounter encounter, Obs obsGroup, Integer concept);
	Obs getObs(Encounter encounter, Obs obsGroup, Integer concept);

	Obs update(Obs obs);

	Obs getObs(Encounter encounter, Integer concept);

	List<Obs> getObsByConcept(Patient patient, Integer concept);
	public List<Obs> getObsByDate(Patient patient, Integer concept, Date date);
	Boolean conceptContainValue(Patient patient, Integer concept, List<Integer> values);

	Obs getObs(Patient patient, Integer concept);

	Obs getObsRevisionParaclinico(Patient patient, Integer concept);

	List<Obs> getObservations(Patient patient, Integer concept, Integer maxResults);
	
	List<Obs> getObsByParent(Obs obs);
	
	List<Obs> getEncounterObservations(Encounter encounter);
	
	List<Obs> getSonObservations(Obs observation);
	
	Obs getLastDiagnosis(Encounter encounter);

	List<Obs> getObservationsValue(Patient patient, Integer concept, Integer maxResults);

	Obs getLastObservation(Integer encounter, Integer concept);
	
	List<Obs> getObservations(Encounter encounter, Integer concept, Integer maxResults);

	Obs getObs(Integer obsId);

	void deleteChildren(Obs obs);

	List<Obs> getListObs(Encounter encounter, Obs obsGroup, Integer concept);
	public List<Obs> getListObs(Patient patient, Obs obsGroup, Integer concept);
	public Obs getObsClinicHistory(Patient patient, String ehrUuid, Integer historyAMPConceptId);
	
	public List<Obs> getObsPatient(Patient patient, Integer concept);
	
}
