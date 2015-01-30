package com.artica.telesalud.persona.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.common.exception.DataConstraintViolationException;
import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.persona.dao.PermissionDAO;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.dao.RoleDAO;
import com.artica.telesalud.persona.model.impl.OperationDTO;
import com.artica.telesalud.persona.model.impl.PermissionDTO;
import com.artica.telesalud.persona.model.impl.PrivilegeDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;
import com.artica.telesalud.persona.model.impl.RoleDTO;
import com.artica.telesalud.persona.service.user.RoleIsParentException;

public class RoleService {
	
	private RoleDAO roleDao;
	private PermissionDAO permissionDao;
	
	public RoleService(String daoClassName, HashMap<String, String> params){
		roleDao = DAOFactoryInstantiator.instantiateDaoFactory(PersonaDAOFactory.class, 
			daoClassName, params).getRoleDao();
		permissionDao = DAOFactoryInstantiator.instantiateDaoFactory(PersonaDAOFactory.class, 
				daoClassName, params).getPermissionDao();
	}
	
	public List<RoleDTO> readAll(){
		return roleDao.getAll();
	}
	
	public RoleDTO newBlankRole(){
		return roleDao.newBlankRole();
	}
	
	public RoleDTO insertRole(RoleDTO role) throws DataConstraintViolationException{
		if( role.getUuid() == null || role.getUuid().isEmpty() )
			role.setUuid(UUID.randomUUID().toString());
		
		assertValid(role);
		
		return roleDao.insert(role);
	}
	
	public RoleDTO updateRole(RoleDTO role) throws DataConstraintViolationException{
		assertValid(role);
		return roleDao.update(role);
	}
	
	public RoleDTO deleteRole(RoleDTO role) throws RoleIsParentException{
		if( roleDao.getChildren(role).size() > 0 ){
			throw new RoleIsParentException(role);
		}
		return roleDao.delete(role);
	}
	
	public List<RoleDTO> getDescendentsOf(RoleDTO role){
		
		if( role == null || role.getRoleId() == null || role.getRoleId().intValue() == 0 )
			return new ArrayList<RoleDTO>(0);
		
		ArrayList<RoleDTO> descendents = new ArrayList<RoleDTO>();
		
		List<RoleDTO> children = roleDao.getChildren(role);
		
		for( RoleDTO child : children ){
			descendents.add(child);
			descendents.addAll(getDescendentsOf(child));
		}
		
		return descendents;
	}
	
	public List<RoleDTO> getAncestorsOf(RoleDTO role){
		ArrayList<RoleDTO> ancestors = new ArrayList<RoleDTO>();
		
		RoleDTO ancestor = roleDao.getParent(role);
		while( ancestor != null ){
			ancestors.add(ancestor);
			ancestor = roleDao.getParent(ancestor);
		}
			
		return ancestors;
	}
	
	public boolean isAncestorOf(RoleDTO role, RoleDTO ancestor){
		RoleDTO p = roleDao.getParent(role);
		while( p != null ){
			if( p.equals(ancestor) )
				return true;
			p = roleDao.getParent(p);
		}
		return false;
	}
	
	public List<PrivilegeDTO> getInheritedPrivileges(RoleDTO role){
		
		List<PrivilegeDTO> inheritedPrivileges = new ArrayList<PrivilegeDTO>();
		
		if( role == null )
			return inheritedPrivileges;
		
		RoleDTO parent = roleDao.getParent(role);
		
		if( parent != null ){
			inheritedPrivileges.addAll( getPrivileges(parent));
			inheritedPrivileges.addAll(getInheritedPrivileges(parent));			
		}
		
		return inheritedPrivileges;
	}
	
	public boolean hasPrivilege(RoleDTO role, ResourceDTO resource, OperationDTO operation){
		
		if( resource == null || operation == null )
			return false;
		
		for(PrivilegeDTO p : getPrivileges(role))
			if( p.getResource().equals(resource.getResourceId()) && p.getOperation().equals(operation.getOperationId()) )
					return true;
		
		return false;
	}
	
	public List<PrivilegeDTO> getPrivileges(RoleDTO role){
		if( role == null )
			return new ArrayList<PrivilegeDTO>(0);
		return roleDao.getPrivilegesFor(role);
	}
	
	public boolean inheritsPrivilege(RoleDTO role, ResourceDTO resource, OperationDTO operation){
		if( roleDao.getParent(role) == null )
			return false;
		
		if( hasPrivilege(roleDao.getParent(role), resource, operation) )
			return true;
		else
			return inheritsPrivilege(roleDao.getParent(role), resource, operation);
	}
	
	public PermissionDTO newBlankPermission(){
		return permissionDao.newBlankPermission();
	}
	
	
	public void assertValid(RoleDTO role) throws DataConstraintViolationException{
		List<DataConstraintViolation> violations = roleDao.validate(role);
		
		if( violations.size() > 0)
			throw new DataConstraintViolationException(violations);
		
	}

	public RoleDTO findById(int id) {
		return roleDao.findById(id);
	}
	
	public RoleDTO findByName(String name){
		return roleDao.findByName(name);
	}
	
	public List<ResourceDTO> getPartAccessibleResources(RoleDTO role){
		return roleDao.getPartAccesibleResources(role);
	}
	
	public void setPrivileges(RoleDTO role, List<PrivilegeDTO> newPrivileges){
		List<PrivilegeDTO> actualPrivileges = roleDao.getPrivilegesFor(role);
		
		for(PrivilegeDTO actual : actualPrivileges)
			if( !newPrivileges.contains(actual))
				roleDao.removePrivilegeFrom(role, actual);
		
		for(PrivilegeDTO newP : newPrivileges)
			if( !actualPrivileges.contains(newP))
				roleDao.addPrivilegeTo(role, newP);
	}
}
