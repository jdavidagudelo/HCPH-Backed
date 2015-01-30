package com.artica.telesalud.tph.model.concept;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;


public class ConceptMap {

	private Integer conceptMapId;
	private Concept concept;
	private ConceptSource conceptSource;
	private String sourceCode;
	private String comment;
	private Integer creator;
	private Date dateCreated;
	private String uuid;
	public Integer getConceptMapId() {
		return conceptMapId;
	}
	public void setConceptMapId(Integer conceptMapId) {
		this.conceptMapId = conceptMapId;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public ConceptSource getConceptSource() {
		return conceptSource;
	}
	public void setConceptSource(ConceptSource conceptSource) {
		this.conceptSource = conceptSource;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	
}
