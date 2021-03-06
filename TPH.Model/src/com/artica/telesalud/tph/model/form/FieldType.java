package com.artica.telesalud.tph.model.form;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class FieldType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer fieldTypeId;
	private String name;
	private String description;
	private Integer isSet;
	private Integer creator;
	private Date dateCreated;
	private String uuid;
	private Integer isContainer;
	
	public Integer getFieldTypeId() {
		return fieldTypeId;
	}
	public void setFieldTypeId(Integer fieldTypeId) {
		this.fieldTypeId = fieldTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsSet() {
		return isSet;
	}
	public void setIsSet(Integer isSet) {
		this.isSet = isSet;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Integer getIsContainer() {
		return isContainer;
	}
	public void setIsContainer(Integer isContainer) {
		this.isContainer = isContainer;
	}
}
