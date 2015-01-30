package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.patient.PatientIdentifier;

public interface PatientDAO {
	
	public List<Patient> getAll();
	public Patient insert(Patient patient);
	public Patient update(Patient patient);
	public Patient newBlankPatient();
	public Patient delete(Patient patient);
	public List<PatientIdentifier> getPatients(String identifier) throws TelesaludException;
	public Patient getPatient(Integer identifier) throws TelesaludException;

}
