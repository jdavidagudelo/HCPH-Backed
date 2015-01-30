package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.encounter.EncounterSpringDto;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
/**
 * Clase utilizada para mapear evaluaciones a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class EvaluacionCompletaSpringDto {
	private Integer obsId;
	private Integer conceptId;
	private Date dateCreated;
	private Integer creator;
	private EncounterSpringDto encounter;
	public EvaluacionCompletaSpringDto()
	{
		
	}
	public EvaluacionCompletaSpringDto(Obs obs, Encounter encounter) {
	
		super();
		if(encounter != null)
		{
			this.encounter = new EncounterSpringDto(encounter);
		}
		obsId = obs.getObsId();
		conceptId = obs.getConcept();
		dateCreated = obs.getDateCreated();
		creator = obs.getCreator();
	}
	public EvaluacionCompletaSpringDto(ObservationData obs, Encounter encounter) {
		super();
		if(encounter != null)
		{
			this.encounter = new EncounterSpringDto(encounter);
		}
		obsId = obs.getObsId();
		conceptId = obs.getConceptId();
		dateCreated = obs.getDateCreated();
		creator = obs.getCreator();
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
	 * @return the conceptId
	 */
	public Integer getConceptId() {
		return conceptId;
	}
	/**
	 * @param conceptId the conceptId to set
	 */
	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}
	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	/**
	 * @return the creator
	 */
	public Integer getCreator() {
		return creator;
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	/**
	 * @return the encounter
	 */
	public EncounterSpringDto getEncounter() {
		return encounter;
	}
	/**
	 * @param encounter the encounter to set
	 */
	public void setEncounter(EncounterSpringDto encounter) {
		this.encounter = encounter;
	}
	
}
