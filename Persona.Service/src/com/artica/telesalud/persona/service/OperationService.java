package com.artica.telesalud.persona.service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.common.exception.DataConstraintViolationException;
import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.persona.dao.OperationDAO;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.model.impl.OperationDTO;

public class OperationService {
	
	private OperationDAO operationDao;
	
	public OperationService(String daoClassName, HashMap<String, String> params){
		operationDao = DAOFactoryInstantiator.instantiateDaoFactory(PersonaDAOFactory.class, 
			daoClassName, params).getOperationDao();
	}
	
	public List<OperationDTO> readAll(){
		return operationDao.getAll();
	}
	
	public OperationDTO newBlankOperation(){
		return operationDao.newBlankOperation();
	}
	
	public OperationDTO insertOperation(OperationDTO operation) throws DataConstraintViolationException{
		
		if( operation.getUuid() == null || operation.getUuid().isEmpty() )
			operation.setUuid(UUID.randomUUID().toString());
		
		assertValid(operation);
		
		return operationDao.insert(operation);
	}
	
	public OperationDTO updateOperation(OperationDTO operation) throws DataConstraintViolationException{
		
		assertValid(operation);
		
		return operationDao.update(operation);
	}
	
	public OperationDTO deleteOperation(OperationDTO operation){
		return operationDao.delete(operation);
	}
	
	public void assertValid(OperationDTO operation) throws DataConstraintViolationException{
		List<DataConstraintViolation> violations = operationDao.validate(operation);
		
		if( violations.size() > 0)
			throw new DataConstraintViolationException(violations);
		
	}

	public OperationDTO findByName(String operation) {

		return operationDao.findByName(operation);
	}
	
}
