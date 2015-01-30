package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.evento.Hospital;


public interface HospitalDAO {
	
	public List<Hospital> getAll();
	public List<Hospital> getAllVoided();
	public Hospital insert(Hospital hospital);
	public Hospital update(Hospital hospital);
	public Hospital newHospital();
	public Hospital delete(Hospital hospital);
	public Hospital get(Integer hospitalId);
}
