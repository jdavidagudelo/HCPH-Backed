package com.artica.telesalud.tph.model.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;

public class Form implements Serializable{
	
	private Integer formId;
	private String name;
	private String version;
	private String description;
	private Integer encounterType;
	private String template;
	private Integer creator;
	private Date dateCreated;
	private Integer changedBy;
	private Date dateChanged;
	private Integer retired;
	private Integer retiredBy;
	private Date dateRetired;
	private String retiredReason;
	private String uuid;
	@JsonBackReference
	private Set<FormField> fields=new HashSet<FormField>();
	
	public Integer getFormId() {
		return formId;
	}
	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getEncounterType() {
		return encounterType;
	}
	public void setEncounterType(Integer encounterType) {
		this.encounterType = encounterType;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
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
	public Integer getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(Integer changedBy) {
		this.changedBy = changedBy;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateChanged() {
		return dateChanged;
	}
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
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
	public String getRetiredReason() {
		return retiredReason;
	}
	public void setRetiredReason(String retiredReason) {
		this.retiredReason = retiredReason;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Set<FormField> getFields() {
		return fields;
	}
	public void setFields(Set<FormField> fields) {
		
		this.fields = fields;
	}
}
