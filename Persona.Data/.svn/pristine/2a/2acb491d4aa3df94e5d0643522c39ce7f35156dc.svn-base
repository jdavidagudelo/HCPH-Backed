package com.artica.telesalud.persona.dao;

import java.util.List;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.persona.model.impl.OperationDTO;

public interface OperationDAO {
	public List<OperationDTO> getAll();
	public OperationDTO insert(OperationDTO operation);
	public OperationDTO update(OperationDTO operation);
	public OperationDTO newBlankOperation();
	public OperationDTO delete(OperationDTO operation);
	public List<DataConstraintViolation> validate(OperationDTO operation);
	public OperationDTO findByName(String name);
}
