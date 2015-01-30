package com.artica.telesalud.tph.model.person;

import java.util.Date;

public class RelationshipType {
	
	private Integer relationshipTypeId;
	private String aIstoB;
	private String bIstoA;
	private Integer prefered;
	private Integer weight;
	private String description;
	private Integer creator;
	private Date dateCreated;
	private Integer retired;
	private Integer retiredBy;
	private Date dateRetired;
	private String retireReason;
	private String Uuid;
	
	
	public Integer getRelationshipTypeId() {
		return relationshipTypeId;
	}
	public void setRelationshipTypeId(Integer relationshipTypeId) {
		this.relationshipTypeId = relationshipTypeId;
	}
	public String getaIstoB() {
		return aIstoB;
	}
	public void setaIstoB(String aIstoB) {
		this.aIstoB = aIstoB;
	}
	public String getbIstoA() {
		return bIstoA;
	}
	public void setbIstoA(String bIstoA) {
		this.bIstoA = bIstoA;
	}
	public Integer getPrefered() {
		return prefered;
	}
	public void setPrefered(Integer prefered) {
		this.prefered = prefered;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
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
