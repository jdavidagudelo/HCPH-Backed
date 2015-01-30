package com.artica.telesalud.persona.model.impl;
import java.io.Serializable;


public class PermissionDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer role;
	private Integer resource;
	private Integer operation;
	private PermissionId id = new PermissionId();
	
	public Integer getRole(){
		return this.role;
	}
	
	
	/**
	 * @return the id
	 */
	public PermissionId getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(PermissionId id) {
		role = id.getRole();
		resource = id.getResource();
		operation = id.getOperation();
		this.id = id;
	}


	public void setRole(Integer role){
		this.role = role;
		id.setRole(role);
	}
	
	
	public Integer getResource(){
		return this.resource;
	}
	
	
	public void setResource(Integer resource){
		this.resource = resource;
		id.setResource(resource);
	}
	
	
	public Integer getOperation(){
		return this.operation;
	}
	
	
	public void setOperation(Integer operation){
		this.operation = operation;
		id.setOperation(operation);
	}
	
	
	public boolean equals(Object obj) {
		if( !(obj instanceof PermissionDTO) )
			return false;
		
		PermissionDTO p = (PermissionDTO)obj;
		
		boolean sameOperation = (operation == null) ? p.getOperation() == null : operation.equals(p.getOperation());
		
		boolean sameResource = (resource == null) ? p.getResource() == null : resource.equals(p.getResource());
		
		boolean sameRole = (role == null) ? p.getRole() == null : role.equals(p.getRole());
		
		return sameOperation && sameResource && sameRole;
	}

	
	public String toString() {
		return "Role: " + role + ", Operation: " + operation + ", Resource: " + resource;
	}
	
}