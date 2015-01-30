package com.artica.telesalud.tph.model.concept;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class ConceptName {

	private Integer conceptNameId;
	private Concept concept;
	private String name;
	private String locale;
	private Integer creator;
	private Date dateCreated;
	private Integer voided;
	private Date dateVoided;
	private Integer voidedBy;
	private String voidedReason;
	private String uuid;
	
	
	public Integer getConceptNameId() {
		return conceptNameId;
	}
	public void setConceptNameId(Integer conceptNameId) {
		this.conceptNameId = conceptNameId;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getVoided() {
		return voided;
	}
	public void setVoided(Integer voided) {
		this.voided = voided;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateVoided() {
		return dateVoided;
	}
	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}
	public Integer getVoidedBy() {
		return voidedBy;
	}
	public void setVoidedBy(Integer voidedBy) {
		this.voidedBy = voidedBy;
	}
	public String getVoidedReason() {
		return voidedReason;
	}
	public void setVoidedReason(String voidedReason) {
		this.voidedReason = voidedReason;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
