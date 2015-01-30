package com.artica.telesalud.tph.model.concept;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.util.JsonDateSerializer;

public class Drug implements Serializable{

	private Integer drugId;
	private Concept concept;
	private String name;
	private Integer combination;
	private Concept dosageForm;
	private Double doseStrength;
	private Double maximunDailyDose;
	private Double minimunDailyDose;
	private Double recomendedDailyDose;
	private Integer units;
	private Integer creator;
	private Date dateCreated;
	private Integer retired;
	private Integer retiredBy;
	private Date dateRetired;
	private String retireReason;
	private String uuid;
	
	public Integer getDrugId() {
		return drugId;
	}
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
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
	public Integer getCombination() {
		return combination;
	}
	public void setCombination(Integer combination) {
		this.combination = combination;
	}
	public Concept getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(Concept dosageForm) {
		this.dosageForm = dosageForm;
	}
	public Double getDoseStrength() {
		return doseStrength;
	}
	public void setDoseStrength(Double doseStrength) {
		this.doseStrength = doseStrength;
	}
	public Double getMaximunDailyDose() {
		return maximunDailyDose;
	}
	public void setMaximunDailyDose(Double maximunDailyDose) {
		this.maximunDailyDose = maximunDailyDose;
	}
	public Double getMinimunDailyDose() {
		return minimunDailyDose;
	}
	public void setMinimunDailyDose(Double minimunDailyDose) {
		this.minimunDailyDose = minimunDailyDose;
	}
	public Double getRecomendedDailyDose() {
		return recomendedDailyDose;
	}
	public void setRecomendedDailyDose(Double recomendedDailyDose) {
		this.recomendedDailyDose = recomendedDailyDose;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
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
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
