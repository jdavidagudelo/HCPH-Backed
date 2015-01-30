package com.artica.telesalud.persona.dao;

import java.util.List;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.persona.model.impl.OperationDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;

public interface ResourceDAO {
	public List<ResourceDTO> getAll();
	public ResourceDTO insert(ResourceDTO resource);
	public ResourceDTO update(ResourceDTO resource);
	public ResourceDTO newBlankResource();
	public ResourceDTO delete(ResourceDTO resource);
	public List<DataConstraintViolation> validate(ResourceDTO resource);
	public List<OperationDTO> getOperationsFor(ResourceDTO resource);
	public ResourceDTO findByName(String name);
	public ResourceDTO findById(Integer id);
	public void addOperationTo(ResourceDTO resource, OperationDTO operation);
	public void removeOperationFrom(ResourceDTO resource, OperationDTO operation);
	public int deletePrivilegesFor(ResourceDTO resource);
}
