package com.artica.telesalud.tph.service;

import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.dao.HospitalDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Hospital;

public class HospitalService {
	
	HospitalDAO hospitalDAO;
	
	public HospitalService(String daoClassName, HashMap<String, String> params){
			
			hospitalDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getHospitalDAO();
			
	}
	
	public List<Hospital> obtenerHospitalesVoided() {
		return hospitalDAO.getAllVoided();
	}
	
	
	public List<Hospital> obtenerHospitales(){	
		return hospitalDAO.getAll();	
	}
	
	public Hospital obtener(Integer hospitalId){
		return hospitalDAO.get(hospitalId);
	}
	
	public void update(Hospital hospital) {
		hospitalDAO.update(hospital);
	}
	
	public void save(Hospital hospital) {
		hospitalDAO.insert(hospital);
	}
	
}
