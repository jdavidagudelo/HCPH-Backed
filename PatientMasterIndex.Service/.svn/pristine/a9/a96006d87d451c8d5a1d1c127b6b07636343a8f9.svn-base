package com.artica.telesalud.patientmasterindex.service;

import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.patientmasterindex.dao.EhrDAO;
import com.artica.telesalud.patientmasterindex.dao.hibernate.HibernateMasterPatientMasterIndexDAOFactory;
import com.artica.telesalud.patientmasterindex.model.Ehr;

public class EhrService {

	private EhrDAO ehrDAO;
	public EhrService(String daoClassName,
			HashMap<String, String> params) {

		ehrDAO = DAOFactoryInstantiator.instantiateDaoFactory(
				HibernateMasterPatientMasterIndexDAOFactory.class,
				daoClassName, params).getEhrDAO();
	}
	public Ehr get(String uuid)
	{
		return ehrDAO.get(uuid);
	}
	
	public List<Ehr> getExternalHistories(String uuid){
		return ehrDAO.getExternalHistories(uuid);
	}

	public List<Ehr> getAll(){
		return ehrDAO.getAll();
	}
	
}
