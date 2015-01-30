package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.Obs;
import com.artica.telesalud.tph.model.observation.ObservationData;
import com.artica.telesalud.tph.service.ConceptService;
/**
 * Clase utilizada para mapear insumos a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class InsumoSpringDto {
	private ConceptSpringDto insumo;
	private ConceptSpringDto viaAdministracion;
	private Double cantidad;
	private ConceptSpringDto unidades;
	private Boolean inmediato;
	private Double horas;
	private Double minutos;
	private Double segundos;
	private Integer obsId;
	private Integer creator;
	private Date dateCreated;
    private EvaluacionCompletaSpringDto evaluacion;
    public InsumoSpringDto()
    {
    	
    }
	public InsumoSpringDto(ConceptService conceptService, ObservationData od, Encounter encounter, Obs obsParent)
	{
		if(obsParent != null && encounter != null)
		{
			//evaluacion = new EvaluacionCompletaSpringDto(obsParent, encounter);
		}
		ConceptsCode concepts = new ConceptsCode();
		setObsId(od.getObsId());
		dateCreated = od.getDateCreated();
		creator = od.getCreator();
		Integer conceptId = (Integer)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cInsumo());
		insumo = new ConceptSpringDto(conceptService.obtenerConcepto(conceptId));
		conceptId = (Integer)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cViaAdministracionInsumo());
		viaAdministracion = new ConceptSpringDto(conceptService.obtenerConcepto(conceptId));
		conceptId = (Integer)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cUnidadVolumenInsumo());
		unidades = new ConceptSpringDto(conceptService.obtenerConcepto(conceptId));
		inmediato = (Boolean)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cSuministroInmediatoInsumo());
		horas = (Double)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cHorasSuministroInsumo());
		minutos = (Double)ObservationData.obtenerValorConcepto(
						od.getSons(), concepts.cMinutosSuministroInsumo());
		cantidad = (Double)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cCantidadInsumo());
		segundos = (Double)ObservationData.obtenerValorConcepto(
				od.getSons(), concepts.cSegundosSuministroInsumo());
	}
	/**
	 * @return the insumo
	 */
	public ConceptSpringDto getInsumo() {
		return insumo;
	}
	/**
	 * @param insumo the insumo to set
	 */
	public void setInsumo(ConceptSpringDto insumo) {
		this.insumo = insumo;
	}
	/**
	 * @return the viaAdministracion
	 */
	public ConceptSpringDto getViaAdministracion() {
		return viaAdministracion;
	}
	/**
	 * @param viaAdministracion the viaAdministracion to set
	 */
	public void setViaAdministracion(ConceptSpringDto viaAdministracion) {
		this.viaAdministracion = viaAdministracion;
	}
	/**
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * @return the unidades
	 */
	public ConceptSpringDto getUnidades() {
		return unidades;
	}
	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(ConceptSpringDto unidades) {
		this.unidades = unidades;
	}
	/**
	 * @return the inmediato
	 */
	public Boolean getInmediato() {
		return inmediato;
	}
	/**
	 * @param inmediato the inmediato to set
	 */
	public void setInmediato(Boolean inmediato) {
		this.inmediato = inmediato;
	}

	/**
	 * @return the horas
	 */
	public Double getHoras() {
		return horas;
	}
	/**
	 * @param horas the horas to set
	 */
	public void setHoras(Double horas) {
		this.horas = horas;
	}
	/**
	 * @return the minutos
	 */
	public Double getMinutos() {
		return minutos;
	}
	/**
	 * @param minutos the minutos to set
	 */
	public void setMinutos(Double minutos) {
		this.minutos = minutos;
	}
	/**
	 * @return the segundos
	 */
	public Double getSegundos() {
		return segundos;
	}
	/**
	 * @param segundos the segundos to set
	 */
	public void setSegundos(Double segundos) {
		this.segundos = segundos;
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
	/**
	 * @return the evaluacion
	 */
	public EvaluacionCompletaSpringDto getEvaluacion() {
		return evaluacion;
	}
	/**
	 * @param evaluacion the evaluacion to set
	 */
	public void setEvaluacion(EvaluacionCompletaSpringDto evaluacion) {
		this.evaluacion = evaluacion;
	}
	
}
