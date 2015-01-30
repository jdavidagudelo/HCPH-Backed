package com.artica.telesalud.tph.model.patient;

import java.io.Serializable;
import java.util.Date;

public class PatientIdentifierType implements Serializable{
	
	private Integer patientIdentifierTypeId;
	private String name;
	private String description;
	private String format;
	private String checkDigit;
	private Integer required;
	private String formatDescription;
	private String validator;
	private Integer creator;
	private Date dateCreated;
	private Short retired;
	private Integer retiredBy;
	private Date dateRetired;
	private String retireReason;
	private String Uuid;
	
	
	public Integer getPatientIdentifierTypeId() {
		return patientIdentifierTypeId;
	}
	public void setPatientIdentifierTypeId(Integer patientIdentifierTypeId) {
		this.patientIdentifierTypeId = patientIdentifierTypeId;
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
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	public Integer getRequired() {
		return required;
	}
	public void setRequired(Integer required) {
		this.required = required;
	}
	public String getFormatDescription() {
		return formatDescription;
	}
	public void setFormatDescription(String formatDescription) {
		this.formatDescription = formatDescription;
	}
	public String getValidator() {
		return validator;
	}
	public void setValidator(String validator) {
		this.validator = validator;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Short getRetired() {
		return retired;
	}
	public void setRetired(Short retired) {
		this.retired = retired;
	}
	public Integer getRetiredBy() {
		return retiredBy;
	}
	public void setRetiredBy(Integer retiredBy) {
		this.retiredBy = retiredBy;
	}
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
