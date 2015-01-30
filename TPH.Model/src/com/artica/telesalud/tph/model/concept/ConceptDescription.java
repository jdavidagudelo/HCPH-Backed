package com.artica.telesalud.tph.model.concept;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class ConceptDescription {

	private Integer conceptDescriptionId;
	private Concept concept;
	private String description;
	private String locale;
	private Integer creator;
	private Date dateCreated;
	private Integer createdBy;
	private Date dateChanged;
	private String uuid;
	
	public Integer getConceptDescriptionId() {
		return conceptDescriptionId;
	}
	public void setConceptDescriptionId(Integer conceptDescriptionId) {
		this.conceptDescriptionId = conceptDescriptionId;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
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
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateChanged() {
		return dateChanged;
	}
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
