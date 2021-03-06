package com.artica.telesalud.tph.model.evento;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.location.City;

public class Evento {
	
	public static String ESTADO_ACTIVO = "Activo";
	public static String ESTADO_FINALIZADO = "Finalizado";
	private EventAddress eventAddress;
	private Integer eventoId;
	private String coordinates;
	private String direccion;
	private City ciudad;
	private Concept zona;
	private Integer sede;
	private Concept causaAtencion;
	private String numeroCaso;
	private Date fechaLlamada;
	private String estado;
	private Integer creator;
	private Date dateCreated;
	private Integer changedBy;
	private Date dateChanged;
	private Short voided;
	private Integer voidedBy;
	private Date dateVoided;
	private String voidReason;
	private String Uuid;
	private String otherCause;
	
	
	/**
	 * @return the otherCause
	 */
	public String getOtherCause() {
		return otherCause;
	}
	/**
	 * @param otherCause the otherCause to set
	 */
	public void setOtherCause(String otherCause) {
		this.otherCause = otherCause;
	}
	/**
	 * @return the coordinates
	 */
	public String getCoordinates() {
		return coordinates;
	}
	/**
	 * @param coordinates the coordinates to set
	 */
	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
	private Set<Lesionado> lesionados = new HashSet<Lesionado>();
	
	public Integer getEventoId() {
		return eventoId;
	}
	public void setEventoId(Integer eventoId) {
		this.eventoId = eventoId;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * @return the eventAddress
	 */
	public EventAddress getEventAddress() {
		return eventAddress;
	}
	/**
	 * @param eventAddress the eventAddress to set
	 */
	public void setEventAddress(EventAddress eventAddress) {
		this.eventAddress = eventAddress;
	}
	public City getCiudad() {
		return ciudad;
	}
	public void setCiudad(City ciudad) {
		this.ciudad = ciudad;
	}
	public Concept getZona() {
		return zona;
	}
	public void setZona(Concept zona) {
		this.zona = zona;
	}	
	public Integer getSede() {
		return sede;
	}
	public void setSede(Integer sede) {
		this.sede = sede;
	}
	public Concept getCausaAtencion() {
		return causaAtencion;
	}
	public void setCausaAtencion(Concept causaAtencion) {
		this.causaAtencion = causaAtencion;
	}
	public String getNumeroCaso() {
		return numeroCaso;
	}
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
	public Date getFechaLlamada() {
		return fechaLlamada;
	}
	public void setFechaLlamada(Date fechaLlamada) {
		this.fechaLlamada = fechaLlamada;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	public Integer getVoidedBy() {
		return voidedBy;
	}
	public void setVoidedBy(Integer voidedBy) {
		this.voidedBy = voidedBy;
	}
	public Date getDateVoided() {
		return dateVoided;
	}
	public void setDateVoided(Date dateVoided) {
		this.dateVoided = dateVoided;
	}
	public String getVoidReason() {
		return voidReason;
	}
	public void setVoidReason(String voidReason) {
		this.voidReason = voidReason;
	}
	public String getUuid() {
		return Uuid;
	}
	public void setUuid(String uuid) {
		Uuid = uuid;
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
	public Set<Lesionado> getLesionados() {
		return lesionados;
	}
	public void setLesionados(Set<Lesionado> lesionados) {
		
		this.lesionados = lesionados;
	}
}