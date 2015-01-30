package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.model.patient.PersonSpringDto;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.PersonService;
/**
 * Clase utilizada para mapear notas aclaratorias a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class NotaAclaratoriaSpringDto {
	private PersonSpringDto creador;
	private Date fechaCreacion;
	private String nota;
	private Integer lesionadoId;
	private Integer obsId;
	private Date dateCreated;
	private Integer creator;
	
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
	public NotaAclaratoriaSpringDto()
	{
		
	}
	/**
	 * @param creator the creator to set
	 */
	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public NotaAclaratoriaSpringDto(ObservationData sv, Lesionado lesionado, PersonService personService) {
		ConceptsCode concepts = new ConceptsCode();
		setDateCreated(sv.getDateCreated());
		setCreator(sv.getCreator());
		setObsId(sv.getObsId());
		setFechaCreacion((Date) ObservationData
				.obtenerValorConcepto(sv.getSons(),
						concepts.cFechaNotaAclaratoria()));
		setNota((String) ObservationData
				.obtenerValorConcepto(sv.getSons(),
						concepts.cTextoNotaAclaratoria()));
		String usuario = (String) ObservationData
				.obtenerValorConcepto(sv.getSons(),
						concepts.cUsuarioNotaAclaratoria());

		if (usuario != null && !usuario.equals("null")) {
			Person p = personService.obtenerPersona(Integer
					.parseInt(usuario));
			setCreador(new PersonSpringDto(p));
		}
		if(lesionado != null)
		{
			setLesionadoId(lesionado.getLesionadoId());
		}
	}


	/**
	 * @return the lesionadoId
	 */
	public Integer getLesionadoId() {
		return lesionadoId;
	}

	/**
	 * @param lesionadoId the lesionadoId to set
	 */
	public void setLesionadoId(Integer lesionadoId) {
		this.lesionadoId = lesionadoId;
	}

	/**
	 * @return the creador
	 */
	public PersonSpringDto getCreador() {
		return creador;
	}
	/**
	 * @param creador the creador to set
	 */
	public void setCreador(PersonSpringDto creador) {
		this.creador = creador;
	}
	/**
	 * @return the fechaCreacion
	 */
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}

	
}
