package com.artica.telesalud.persona.model.impl;
import java.io.Serializable;


public class ResourceDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer resourceId;
	private String name;
	private String description;
	private String uuid;
	private String url;
	private String category;
	private String tag;
	
	
	public Integer getResourceId(){
		return this.resourceId;
	}
	
	
	public void setResourceId(Integer resourceId){
		this.resourceId = resourceId;
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
	
	
	public String getUrl(){
		return this.url;
	}
	
	
	public void setUrl(String url){
		this.url = url;
	}
	
	
	public String getCategory(){
		return this.category;
	}
	
	
	public void setCategory(String category){
		this.category = category;
	}
	
	
	public String getTag(){
		return this.tag;
	}
	
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	
	public boolean equals(Object obj) {
		ResourceDTO op = (ResourceDTO)obj;
		if( !(obj instanceof ResourceDTO) )
			return false;
		
		if ( this.resourceId == null )
			return op.getResourceId() == null;
		
		return ( getResourceId().equals(op.getResourceId()) );
	}

	
	public String toString() {
		return this.getName();
	}
}