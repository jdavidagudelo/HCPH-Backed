package com.artica.telesalud.tph.model.concept;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class ConceptAnswer {

	private Integer conceptAnswerId;
	private Concept concept;
	private Concept answerConcept;
	private Integer creator;
	private Date dateCreated;
	private String uuid;
	
	
	public Integer getConceptAnswerId() {
		return conceptAnswerId;
	}
	public void setConceptAnswerId(Integer conceptAnswerId) {
		this.conceptAnswerId = conceptAnswerId;
	}
	public Concept getConcept() {
		return concept;
	}
	public void setConcept(Concept concept) {
		this.concept = concept;
	}
	public Concept getAnswerConcept() {
		return answerConcept;
	}
	public void setAnswerConcept(Concept answerConcept) {
		this.answerConcept = answerConcept;
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
