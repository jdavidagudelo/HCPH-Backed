package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;

import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.service.ConceptService;
/**
 * Clase utilizada para mapear cierres de atencion a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class CierreAtencionSpringDto {
	private ConceptSpringDto tipoCierre;
	private String nota;
	private UserSpringDto usuarioCierra;
	private Integer lesionadoId;
	private Integer obsId;
	private Date dateCreated;
	private Integer creator;
	
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
	public CierreAtencionSpringDto()
	{
		
	}
	public CierreAtencionSpringDto(ObservationData od, UserDTO user, Person person, String roleName, ConceptService conceptService, Integer lesionadoId)
	{
		ConceptsCode concepts = new ConceptsCode();
		setLesionadoId(lesionadoId);
		usuarioCierra = new UserSpringDto(user, person, roleName);
		setNota((String)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cNotaAclaratoriaCierre()));
		Integer idTipoCierre = (Integer)ObservationData.obtenerValorConcepto(od.getSons(), concepts.cTipoCierreAtencion());
		setObsId(od.getObsId());
		if(idTipoCierre != null){
			setTipoCierre(new ConceptSpringDto(conceptService.obtenerConcepto(idTipoCierre)));
		}
		dateCreated = od.getDateCreated();
		creator = od.getCreator();
	}

	/**
	 * @return the tipoCierre
	 */
	public ConceptSpringDto getTipoCierre() {
		return tipoCierre;
	}
	/**
	 * @param tipoCierre the tipoCierre to set
	 */
	public void setTipoCierre(ConceptSpringDto tipoCierre) {
		this.tipoCierre = tipoCierre;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public UserSpringDto getUsuarioCierra() {
		return usuarioCierra;
	}
	public void setUsuarioCierra(UserSpringDto usuarioCierra) {
		this.usuarioCierra = usuarioCierra;
	}

	
}
