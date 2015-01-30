package com.artica.telesalud.tph.model.encounter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.artica.telesalud.tph.model.form.Form;
import com.artica.telesalud.tph.model.location.City;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.person.Person;

public class Encounter implements Serializable {

	private static final long serialVersionUID = -7600533176799742485L;
	
	public static String STATE_ACTIVE="ACTIVO";
	public static String STATE_TERMINATED="TERMINADO";
	public static String STATE_NO_ATTENDED="NO ATENDIDO";
	public static String STATE_ATTENDED_NEW="ATENDIDO CON NOVEDAD";
	public static String STATE_ATTENDED_REVISED="ATENDIDO REVISADO";
	
	private Integer encounterId;
	private EncounterType encounterType;
	private Patient patient;
	private Person provider;
	private City location;
	private Form form;
	private Date encounterDatetime;
	private String state;
	private Integer creator;
	private Date dateCreated;
	private Integer changedBy;
	private Date dateChanged;
	private Short voided;
	private Date dateVoided;
	private Integer voidedBy;
	private String voidedReason;
	private String uuid;
	private List<Obs> observations= new ArrayList<Obs>();
	
	private Encounter antecedentePersonal;
	
	
	public Integer getEncounterId() {
		return encounterId;
	}
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}
	public EncounterType getEncounterType() {
		return encounterType;
	}
	public void setEncounterType(EncounterType encounterType) {
		this.encounterType = encounterType;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Person getProvider() {
		return provider;
	}
	public void setProvider(Person provider) {
		this.provider = provider;
	}
	public City getLocation() {
		return location;
	}
	public void setLocation(City location) {
		this.location = location;
	}
	public Form getForm() {
		return form;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public Date getEncounterDatetime() {
		return encounterDatetime;
	}
	public void setEncounterDatetime(Date encounterDatetime) {
		this.encounterDatetime = encounterDatetime;
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
	public Integer getChangedBy() {
		return changedBy;
	}
	public void setChangedBy(Integer changedBy) {
		this.changedBy = changedBy;
	}
	public Date getDateChanged() {
		return dateChanged;
	}
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<Obs> getObservations() {
		return observations;
	}
	public void setObservations(List<Obs> observations) {
		this.observations=observations;
	}
	public Encounter getAntecedentePersonal() {
		return antecedentePersonal;
	}
	public void setAntecedentePersonal(Encounter antecedentePersonal) {
		this.antecedentePersonal = antecedentePersonal;
	}
	
}
