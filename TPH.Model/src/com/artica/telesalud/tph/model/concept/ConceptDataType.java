package com.artica.telesalud.tph.model.concept;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class ConceptDataType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer conceptDataTypeId;
	private String name;
	private String hl7Abreviation;
	private String description;
	private Integer creator;
	private Date dateCreated;
	private Integer retired;
	private Integer retiredBy;
	private Date dateRetired;
	private String retireReason;
	private String Uuid;
	
	
	public Integer getConceptDataTypeId() {
		return conceptDataTypeId;
	}
	public void setConceptDataTypeId(Integer conceptDataTypeId) {
		this.conceptDataTypeId = conceptDataTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHl7Abreviation() {
		return hl7Abreviation;
	}
	public void setHl7Abreviation(String hl7Abreviation) {
		this.hl7Abreviation = hl7Abreviation;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Integer getRetired() {
		return retired;
	}
	public void setRetired(Integer retired) {
		this.retired = retired;
	}
	public Integer getRetiredBy() {
		return retiredBy;
	}
	public void setRetiredBy(Integer retiredBy) {
		this.retiredBy = retiredBy;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateRetired() {
		return dateRetired;
	}
	public void setDateRetired(Date dateRetired) {
		this.dateRetired = dateRetired;
	}
	public String getRetireReason() {
		return retireReason;
	}
	public void setRetireReason(String retireReason) {
		this.retireReason = retireReason;
	}
	public String getUuid() {
		return Uuid;
	}
	public void setUuid(String uuid) {
		Uuid = uuid;
	}

}
