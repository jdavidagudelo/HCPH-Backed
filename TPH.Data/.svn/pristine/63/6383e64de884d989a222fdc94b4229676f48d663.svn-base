package com.artica.telesalud.tph.dao;

import java.util.Date;
import java.util.List;

import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.encounter.EncounterType;
import com.artica.telesalud.tph.model.patient.Patient;

public interface EncounterDAO {
	
	public List<Encounter> getAll();
	public Encounter insert(Encounter encounter, Integer creator);
	public Encounter update(Encounter encounter);
	public Encounter newEncounter();;
	public Encounter delete(Encounter encounter);
	public EncounterType getEncounterType(Integer encounterType);
	public List<Encounter> getEncounters(Patient patient, int maxResult, int offset);
	public Long getTotalEncounters(Patient patient);
	public Encounter finish(Integer encounter);
	public Encounter getEncounter(Integer encounterId);
	public List<Encounter> getEncounters(Patient patient, Date high, Date low);
}
