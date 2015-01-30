package com.artica.telesalud.tph.model.evento;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.encounter.Encounter;

public class Lesionado {
	 
	private Integer lesionadoId;
	private Encounter encuentro;
	private Evento evento;
	private Tripulacion tripulacion;
	private Concept resultado;
	private Concept dispositivoTransporte;
	private Integer niegaAtencion;
	private Concept tipoNegacion;
	private String observacionNegacion;
	private Hospital entidadRecepcion;
	private String recibidoPor;
	private String registroRecibe;
	private Concept eps;
	private Concept aseguradora;
	private Concept planBeneficios;
	private Integer solicitaTeleasistencia;
	private Integer creator;
	private Date dateCreated;
	private Integer changedBy;
	private Date dateChanged;
	private Short voided;
	private Integer voidedBy;
	private Date dateVoided;
	private String voidReason;
	private String Uuid;
	private Integer eventLocalIdentifier;
	private String cargoEncargadoRecepcion;
	private Date fechaUltimaMenstruacion;
	private Set<ResponsableAtencion> responsablesAtencion = new HashSet<ResponsableAtencion>();
	
	
	/**
	 * @return the fechaUltimaMenstruacion
	 */
	public Date getFechaUltimaMenstruacion() {
		return fechaUltimaMenstruacion;
	}
	/**
	 * @param fechaUltimaMenstruacion the fechaUltimaMenstruacion to set
	 */
	public void setFechaUltimaMenstruacion(Date fechaUltimaMenstruacion) {
		this.fechaUltimaMenstruacion = fechaUltimaMenstruacion;
	}
	/**
	 * @return the cargoEncargadoRecepcion
	 */
	public String getCargoEncargadoRecepcion() {
		return cargoEncargadoRecepcion;
	}
	/**
	 * @param cargoEncargadoRecepcion the cargoEncargadoRecepcion to set
	 */
	public void setCargoEncargadoRecepcion(String cargoEncargadoRecepcion) {
		this.cargoEncargadoRecepcion = cargoEncargadoRecepcion;
	}
	/**
	 * @return the eventLocalIdentifier
	 */
	public Integer getEventLocalIdentifier() {
		return eventLocalIdentifier;
	}
	/**
	 * @param eventLocalIdentifier the eventLocalIdentifier to set
	 */
	public void setEventLocalIdentifier(Integer eventLocalIdentifier) {
		this.eventLocalIdentifier = eventLocalIdentifier;
	}
	public Integer getLesionadoId() {
		return lesionadoId;
	}
	public void setLesionadoId(Integer lesionadoId) {
		this.lesionadoId = lesionadoId;
	}
	public Encounter getEncuentro() {
		return encuentro;
	}
	public void setEncuentro(Encounter encuentro) {
		this.encuentro = encuentro;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	public Tripulacion getTripulacion() {
		return tripulacion;
	}
	public void setTripulacion(Tripulacion tripulacion) {
		this.tripulacion = tripulacion;
	}
	public Concept getResultado() {
		return resultado;
	}
	public void setResultado(Concept resultado) {
		this.resultado = resultado;
	}
	public Concept getDispositivoTransporte() {
		return dispositivoTransporte;
	}
	public void setDispositivoTransporte(Concept dispositivoTransporte) {
		this.dispositivoTransporte = dispositivoTransporte;
	}
	public Integer getNiegaAtencion() {
		return niegaAtencion;
	}
	public void setNiegaAtencion(Integer niegaAtencion) {
		this.niegaAtencion = niegaAtencion;
	}
	public Concept getTipoNegacion() {
		return tipoNegacion;
	}
	public void setTipoNegacion(Concept tipoNegacion) {
		this.tipoNegacion = tipoNegacion;
	}
	public String getObservacionNegacion() {
		return observacionNegacion;
	}
	public void setObservacionNegacion(String observacionNegacion) {
		this.observacionNegacion = observacionNegacion;
	}
	public Hospital getEntidadRecepcion() {
		return entidadRecepcion;
	}
	public void setEntidadRecepcion(Hospital entidadRecepcion) {
		this.entidadRecepcion = entidadRecepcion;
	}
	public String getRecibidoPor() {
		return recibidoPor;
	}
	public void setRecibidoPor(String recibidoPor) {
		this.recibidoPor = recibidoPor;
	}
	public String getRegistroRecibe() {
		return registroRecibe;
	}
	public void setRegistroRecibe(String registroRecibe) {
		this.registroRecibe = registroRecibe;
	}
	public Concept getEps() {
		return eps;
	}
	public void setEps(Concept eps) {
		this.eps = eps;
	}
	public Concept getAseguradora() {
		return aseguradora;
	}
	public void setAseguradora(Concept aseguradora) {
		this.aseguradora = aseguradora;
	}
	public Concept getPlanBeneficios() {
		return planBeneficios;
	}
	public void setPlanBeneficios(Concept planBeneficios) {
		this.planBeneficios = planBeneficios;
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
	public Set<ResponsableAtencion> getResponsablesAtencion() {
		return responsablesAtencion;
	}
	public void setResponsablesAtencion(Set<ResponsableAtencion> responsablesAtencion) {
		this.responsablesAtencion = responsablesAtencion;
	}
	public 	Integer getSolicitaTeleasistencia() {
		return solicitaTeleasistencia;
	}
	public void setSolicitaTeleasistencia(Integer solicitaTeleasistencia) {
		this.solicitaTeleasistencia = solicitaTeleasistencia;
	}
}