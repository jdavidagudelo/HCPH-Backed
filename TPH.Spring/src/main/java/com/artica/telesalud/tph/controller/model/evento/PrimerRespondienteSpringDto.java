package com.artica.telesalud.tph.controller.model.evento;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.model.evento.PrimerRespondiente;
/**
 * Clase utilizada para mapear respondientes a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PrimerRespondienteSpringDto {
	private Integer primerRespondienteId;
	private LesionadoSpringDto lesionado;
	private ConceptSpringDto primerRespondiente;
	private Integer creator;
	private Date dateCreated;
	
	public PrimerRespondienteSpringDto(PrimerRespondiente primerRespondiente)
	{
		primerRespondienteId = primerRespondiente.getPrimerRespondienteId();
		if(primerRespondiente.getLesionado() != null)
		{
			lesionado = new LesionadoSpringDto(primerRespondiente.getLesionado());
		}
		if(primerRespondiente.getPrimerRespondiente() != null)
		{
			this.primerRespondiente = new ConceptSpringDto(primerRespondiente.getPrimerRespondiente());
		}
		creator = primerRespondiente.getCreator();
		dateCreated = primerRespondiente.getDateCreated();
	}
	public PrimerRespondienteSpringDto() {
	}
	/**
	 * @return the primerRespondienteId
	 */
	public Integer getPrimerRespondienteId() {
		return primerRespondienteId;
	}
	/**
	 * @param primerRespondienteId the primerRespondienteId to set
	 */
	public void setPrimerRespondienteId(Integer primerRespondienteId) {
		this.primerRespondienteId = primerRespondienteId;
	}
	/**
	 * @return the lesionado
	 */
	public LesionadoSpringDto getLesionado() {
		return lesionado;
	}
	/**
	 * @param lesionado the lesionado to set
	 */
	public void setLesionado(LesionadoSpringDto lesionado) {
		this.lesionado = lesionado;
	}
	/**
	 * @return the primerRespondiente
	 */
	public ConceptSpringDto getPrimerRespondiente() {
		return primerRespondiente;
	}
	/**
	 * @param primerRespondiente the primerRespondiente to set
	 */
	public void setPrimerRespondiente(ConceptSpringDto primerRespondiente) {
		this.primerRespondiente = primerRespondiente;
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
	
}
