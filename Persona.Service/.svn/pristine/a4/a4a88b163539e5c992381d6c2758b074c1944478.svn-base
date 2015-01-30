package com.artica.telesalud.persona.service;

import java.util.HashMap;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.dao.PrivilegeDAO;
import com.artica.telesalud.persona.model.impl.OperationDTO;
import com.artica.telesalud.persona.model.impl.PrivilegeDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;

public class PrivilegeService {
	
	private PrivilegeDAO privilegeDao;
	
	public PrivilegeService(String daoClassName, HashMap<String, String> params){
		privilegeDao = DAOFactoryInstantiator.instantiateDaoFactory(PersonaDAOFactory.class, 
			daoClassName, params).getPrivilegeDao();
	}
		
	public PrivilegeDTO newBlankPrivilege(){
		return privilegeDao.newBlankPrivilege();
	}
	
	public PrivilegeDTO insertPrivilege(PrivilegeDTO privilege){
		return privilegeDao.insert(privilege);
	}
	
	public PrivilegeDTO updatePrivilege(PrivilegeDTO privilege){
		
		return privilegeDao.insert(privilege);
	}
	
	public PrivilegeDTO deletePrivilege(PrivilegeDTO privilege){
		return privilegeDao.delete(privilege);
	}
	
	public PrivilegeDTO getPrivilegeFor(ResourceDTO resource, OperationDTO operation){
		//TODO
		return null;
	}
	
}
