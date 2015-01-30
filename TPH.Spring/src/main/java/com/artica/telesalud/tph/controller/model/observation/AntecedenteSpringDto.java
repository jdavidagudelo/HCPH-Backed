package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientSpringDto;
/**
 * Clase utilizada para mapear antecedentes a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class AntecedenteSpringDto {

	private Date date;
	private Integer year;
	private String tipoAntecedente;
	private ConceptSpringDto conceptTipoAntecedente;
	private String observacion;
	private PatientSpringDto patient;
	private Integer obsId;
	public AntecedenteSpringDto() {
		super();
	}
	


	/**
	 * @return the obsId
	 */
	public Integer getObsId() {
		return obsId;
	}



	/**
	 * @param obsId the obsId to set
	 */
	public void setObsId(Integer obsId) {
		this.obsId = obsId;
	}



	/**
	 * @return the patient
	 */
	public PatientSpringDto getPatient() {
		return patient;
	}



	/**
	 * @param patient the patient to set
	 */
	public void setPatient(PatientSpringDto patient) {
		this.patient = patient;
	}



	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
	/**
	 * @return the tipoAntecedente
	 */
	public String getTipoAntecedente() {
		return tipoAntecedente;
	}
	/**
	 * @param tipoAntecedente the tipoAntecedente to set
	 */
	public void setTipoAntecedente(String tipoAntecedente) {
		this.tipoAntecedente = tipoAntecedente;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}



	/**
	 * @return the conceptTipoAntecedente
	 */
	public ConceptSpringDto getConceptTipoAntecedente() {
		return conceptTipoAntecedente;
	}



	/**
	 * @param conceptTipoAntecedente the conceptTipoAntecedente to set
	 */
	public void setConceptTipoAntecedente(ConceptSpringDto conceptTipoAntecedente) {
		this.conceptTipoAntecedente = conceptTipoAntecedente;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	
}
