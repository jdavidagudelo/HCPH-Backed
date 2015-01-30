package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.patient.PatientIdentifierType;

public interface PatientIdentifierTypeDAO {

	public PatientIdentifierType delete(PatientIdentifierType patientIdentifier);
	public PatientIdentifierType newBlankPatientIdentifierType();
	public PatientIdentifierType update(PatientIdentifierType patientIdentifierType);
	public PatientIdentifierType insert(PatientIdentifierType patientIdentifierType);
	public List<PatientIdentifierType> getAll();
	public PatientIdentifierType getPatientIdentifierType(Integer p);

}
