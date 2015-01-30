package com.artica.telesalud.tph.model.observation;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.concept.Drug;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.location.City;
import com.artica.telesalud.tph.model.order.Order;
import com.artica.telesalud.tph.model.person.Person;

public class Obs implements Serializable{
	
	private Integer obsId;
	private Person person;
	private Integer concept;
	private Encounter encounter;
	private Order order;
	private Date obsDatetime;
	private City location;
	private Obs obsGroup;
	private Integer valueBoolean;
	private Integer valueCoded;
	private Drug valueDrug;
	private Date valueDatetime;
	private Double valueNumeric;
	private String valueText;
	private String comments;
	private Integer creator;
	private Date dateCreated;
	private Short voided;
	private Date dateVoided;
	private Integer voidedBy;
	private String voidedReason;
	private String uuid;
	private String check;
	private Concept completeConcept;
	
	public Integer getObsId() {
		return obsId;
	}
	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Integer getConcept() {
		return concept;
	}
	public void setConcept(Integer concept) {
		this.concept = concept;
	}
	public Encounter getEncounter() {
		return encounter;
	}
	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Date getObsDatetime() {
		return obsDatetime;
	}
	public void setObsDatetime(Date obsDatetime) {
		this.obsDatetime = obsDatetime;
	}
	public City getLocation() {
		return location;
	}
	public void setLocation(City location) {
		this.location = location;
	}
	public Obs getObsGroup() {
		return obsGroup;
	}
	public void setObsGroup(Obs obsGroup) {
		this.obsGroup = obsGroup;
	}
	public Integer getValueBoolean() {
		return valueBoolean;
	}
	public void setValueBoolean(Integer valueBoolean) {
		this.valueBoolean = valueBoolean;
	}
	public Integer getValueCoded() {
		return valueCoded;
	}
	public void setValueCoded(Integer valueCoded) {
		this.valueCoded = valueCoded;
	}
	public Drug getValueDrug() {
		return valueDrug;
	}
	public void setValueDrug(Drug valueDrug) {
		this.valueDrug = valueDrug;
	}
	public Date getValueDatetime() {
		return valueDatetime;
	}
	public void setValueDatetime(Date valueDatetime) {
		this.valueDatetime = valueDatetime;
	}
	public Double getValueNumeric() {
		return valueNumeric;
	}
	public void setValueNumeric(Double valueNumeric) {
		this.valueNumeric = valueNumeric;
	}
	public String getValueText() {
		return valueText;
	}
	public void setValueText(String valueText) {
		this.valueText = valueText;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public Short getVoided() {
		return voided;
	}
	public void setVoided(Short voided) {
		this.voided = voided;
	}
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
	public void setCheck(String check) {
		this.check = check;
	}
	public String getCheck() {
		return check;
	}
	public String calculateCheck() {
		String cadena = this.person.getPersonId().toString()+this.concept.toString();
		
		if(this.encounter != null) cadena += this.encounter.getEncounterId().toString();
		if(this.obsGroup != null) cadena += this.obsGroup.getObsId().toString();
		if(this.valueBoolean != null) cadena += this.valueBoolean.toString();
		if(this.valueCoded != null) cadena += this.valueCoded.toString();
		if(this.valueNumeric != null) cadena += this.valueNumeric.toString();
		if(this.valueText != null) cadena += this.valueText;
		if(this.comments != null) cadena += this.comments;
		if(this.creator != null) cadena += this.creator.toString();
		cadena += this.voided.toString();
		if(this.voidedBy != null) cadena += this.voidedBy.toString();
		if(this.voidedReason != null) cadena += this.voidedReason;
		if(this.uuid != null) cadena += this.uuid;	
		
		return cadena.trim();
	}
	public Concept getCompleteConcept() {
		return completeConcept;
	}
	public void setCompleteConcept(Concept completeConcept) {
		this.completeConcept = completeConcept;
	}
	
	
}
