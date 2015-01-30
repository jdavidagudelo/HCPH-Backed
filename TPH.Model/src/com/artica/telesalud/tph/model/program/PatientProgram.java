package com.artica.telesalud.tph.model.program;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.util.JsonDateSerializer;

public class PatientProgram {

	private Integer patientProgramId;
	private Patient patient;
	private Program program;
	private Date dateEnrolled;
	private Date dateCompleted;
	private Integer creator;
	private Date dateCreated;
	private Integer changedBy;
	private Date dateChanged;
	private Boolean voided;
	private Integer voidedBy;
	private Date dateVoided;
	private String voidedReason;
	private String uuid;
	
	public Integer getPatientProgramId() {
		return patientProgramId;
	}
	public void setPatientProgramId(Integer patientProgramId) {
		this.patientProgramId = patientProgramId;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateEnrolled() {
		return dateEnrolled;
	}
	public void setDateEnrolled(Date dateEnrolled) {
		this.dateEnrolled = dateEnrolled;
	}
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateCompleted() {
		return dateCompleted;
	}
	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
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
	public Boolean getVoided() {
		return voided;
	}
	public void setVoided(Boolean voided) {
		this.voided = voided;
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
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getDateVoided() {
		return dateVoided;
	}
	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}
}
