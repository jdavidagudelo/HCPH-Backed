package com.artica.telesalud.persona.model.impl;


import java.io.Serializable;


public class OperationDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer operationId;
	private String name;
	private String description;
	private String uuid;
	
	
	public Integer getOperationId(){
		return this.operationId;
	}
	
	
	public void setOperationId(Integer operationId){
		this.operationId = operationId;
	}
	
	
	public String getName(){
		return this.name;
	}
	
	
	public void setName(String name){
		this.name = name;
	}
	
	
	public String getDescription(){
		return this.description;
	}
	
	
	public void setDescription(String description){
		this.description = description;
	}
	
	
	public String getUuid(){
		return this.uuid;
	}
	
	
	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	
	public boolean equals(Object obj) {
		
		if( !(obj instanceof OperationDTO) )
			return false;

		OperationDTO op = (OperationDTO)obj;
		if ( this.operationId == null )
			return op.getOperationId() == null;
		
		return ( getOperationId().equals(op.getOperationId()) );
	}

	
	public String toString() {
		return this.getName();
	}
	
	
}