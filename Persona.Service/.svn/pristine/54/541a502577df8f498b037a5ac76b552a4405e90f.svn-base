package com.artica.telesalud.persona.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.common.exception.DataConstraintViolationException;
import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.dao.ResourceDAO;
import com.artica.telesalud.persona.model.impl.OperationDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;

public class ResourceService {
	
	private ResourceDAO resourceDao;
	
	public ResourceService(String daoClassName, HashMap<String, String> params){
		resourceDao = DAOFactoryInstantiator.instantiateDaoFactory(PersonaDAOFactory.class, 
			daoClassName, params).getResourceDao();

	}
	
	public List<ResourceDTO> readAll(){
		return resourceDao.getAll();
	}
	
	public ResourceDTO newBlankResource(){
		return resourceDao.newBlankResource();
	}
	
	public ResourceDTO insertResource(ResourceDTO resource) throws DataConstraintViolationException{
		
		if( resource.getUuid() == null || resource.getUuid().isEmpty() )
			resource.setUuid(UUID.randomUUID().toString());
		
		assertValid(resource);
		
		return resourceDao.insert(resource);
	}
	
	public ResourceDTO updateResource(ResourceDTO resource) throws DataConstraintViolationException{
		
		assertValid(resource);
		
		return resourceDao.update(resource);
	}
	
	public ResourceDTO deleteResource(ResourceDTO resource){
		return resourceDao.delete(resource);
	}
	
	public void assertValid(ResourceDTO resource) throws DataConstraintViolationException{
		List<DataConstraintViolation> violations = resourceDao.validate(resource);
		
		if( violations.size() > 0)
			throw new DataConstraintViolationException(violations);
		
	}

	public List<OperationDTO> getOperationsFor(ResourceDTO resource) {
		if( resource == null || resource.getResourceId() == null )
			return new ArrayList<OperationDTO>(0);
		return resourceDao.getOperationsFor(resource);
	}
	
	public void setOperations(ResourceDTO resource, List<OperationDTO> newOperations){
		
		List<OperationDTO> actualOperations = resourceDao.getOperationsFor(resource);
		
		
		for(OperationDTO actual : actualOperations)
			if(!newOperations.contains(actual)){
				resourceDao.removeOperationFrom(resource, actual);
			}
		
		
		
		for(OperationDTO newOp: newOperations)
			if(!actualOperations.contains(newOp)){
				resourceDao.addOperationTo(resource, newOp);
			}
		
		
		
		
	}
	

	
	public Map<String, List<ResourceDTO>> mapByCategory(List<ResourceDTO> rList) {
		HashMap<String, List<ResourceDTO>> resources = new HashMap<String, List<ResourceDTO>>();

		if( rList == null)
			rList =  resourceDao.getAll();
		
		for(ResourceDTO r : rList){
			List<ResourceDTO> forCategory = resources.get(r.getCategory());
			if( forCategory == null ){
				forCategory = new Vector<ResourceDTO>();
				resources.put(r.getCategory(), forCategory);
			}			
			forCategory.add(r);
		}
		
		return resources;
	}
	
	public ResourceDTO findByName(String name){
		return resourceDao.findByName(name);
	}

	public ResourceDTO findById(Integer id){
		if( id == null )
			return null;
		return resourceDao.findById(id);
	}
	
}
