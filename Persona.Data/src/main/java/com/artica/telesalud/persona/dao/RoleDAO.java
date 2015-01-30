package com.artica.telesalud.persona.dao;

import java.util.List;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.persona.model.impl.PrivilegeDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;
import com.artica.telesalud.persona.model.impl.RoleDTO;

public interface RoleDAO {
	public RoleDTO findByName(String name);
	public List<RoleDTO> getAll();
	public List<RoleDTO> getChildren(RoleDTO role);
	public RoleDTO insert(RoleDTO role);
	public RoleDTO update(RoleDTO role);
	public RoleDTO newBlankRole();
	public RoleDTO delete(RoleDTO role);
	public List<DataConstraintViolation> validate(RoleDTO role);
	public List<PrivilegeDTO> getPrivilegesFor(RoleDTO role);
	
	public void addPrivilegeTo(RoleDTO role, PrivilegeDTO privilege);
	public void removePrivilegeFrom(RoleDTO role, PrivilegeDTO privilege);
	public void removeAllPrivilegesFor(RoleDTO role);

	public List<ResourceDTO> getPartAccesibleResources(RoleDTO role);
	public RoleDTO findById(int id);
	public RoleDTO getParent(RoleDTO role);
}
