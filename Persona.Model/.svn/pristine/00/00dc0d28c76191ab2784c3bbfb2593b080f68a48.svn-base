package com.artica.telesalud.persona.model.impl;
import java.io.Serializable;


public class PrivilegeDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer resource;
	private Integer operation;
	
	private PrivilegeId id = new PrivilegeId();
	public PrivilegeDTO()
	{
		id = new PrivilegeId();
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
		if( !(obj instanceof PrivilegeDTO) )
			return false;
		
		PrivilegeDTO p = (PrivilegeDTO)obj;
		
		boolean sameOperation = (operation == null) ? p.getOperation() == null : operation.equals(p.getOperation());
		
		boolean sameResource = (resource == null) ? p.getResource() == null : resource.equals(p.getResource());
		
		return sameOperation && sameResource;
	}

	
	public String toString() {
		return "Operation: " + operation + ", Resource: " + resource;
	}


	public PrivilegeId getId() {
		return id;
	}


	public void setId(PrivilegeId id) {
		resource = id.getResource();
		operation = id.getOperation();
		this.id = id;
	}
	
	
}