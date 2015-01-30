package com.artica.telesalud.persona.service;

import java.util.HashMap;

import com.artica.telesalud.persona.model.impl.OperationDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;
import com.artica.telesalud.persona.model.impl.RoleDTO;
import com.artica.telesalud.persona.model.impl.UserDTO;

public class ACLService {
	
	private RoleService roleSrv;
	private UserService userSrv;
	private ResourceService resourceSrv;
	private OperationService operationSrv;
	
	public ACLService(String daoClassName, HashMap<String, String> params){
		roleSrv = new RoleService(daoClassName, params);
		userSrv = new UserService(daoClassName, params);
		resourceSrv = new ResourceService(daoClassName, params);
		operationSrv = new OperationService(daoClassName, params);
		
	}
	
	public boolean assertAccess(Integer userId, String resource, String operation) {
		UserDTO user = userSrv.findUserById(userId);
		return assertAccessRole(user.getRoleId(), resource, operation);
		/*
		Role role = roleSrv.findById(user.getRole());
		
		Resource res = resourceSrv.findByName(resource);
		Operation op = operationSrv.findByName(operation);
		
		boolean allowed = roleSrv.hasPrivilege(role, res, op) || roleSrv.inheritsPrivilege(role, res, op);
		
		if( !allowed )
			throw new NotAllowedException();
		*/
		//Allowed, audit access?
	}
	
	public boolean assertAccessRole(Integer roleId, String resource, String operation){
		RoleDTO role = roleSrv.findById(roleId);
		
		ResourceDTO res = resourceSrv.findByName(resource);
		OperationDTO op = operationSrv.findByName(operation);
		
		boolean allowed = roleSrv.hasPrivilege(role, res, op) || roleSrv.inheritsPrivilege(role, res, op);
		
		return allowed;
		//if( !allowed )
		//	throw new NotAllowedException();
		
		//Allowed, audit access?
		
	}
}
