package com.artica.telesalud.tph.lightweightmodel;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PersonaLesionada implements Serializable {
	
	public static final Integer RESULTADO_TRANSPORTE_HOSPITAL=1,
						  RESULTADO_TRANSPORTE_SECUNDARIO=2,
						  RESULTADO_ALTA_SITIO=3,
						  RESULTADO_ENTREGA_OTRO=4,
						  RESULTADO_RCCP_EXITOSA=5,
						  RESULTADO_FALLECE_TRASLADO=6,
						  RESULTADO_FALLECE_HOSPITAL=7,
						  RESULTADO_FALLECE_SITIO=8,
						  RESULTADO_NEGACION=9,
						  NEGACION_REMISION=10,
						  NEGACION_ATENCION=11,
						  DISPOSITIVO_CAMILLA_LONA=12,
						  DISPOSITIVO_CAMILLA_MILLER=13,
						  DISPOSITIVO_CAMILLA_SCOOP=14,
						  DISPOSITIVO_CAMILLA_RIGIDA=15;
	
	
	private String identificacion;
	private String nombre;
	private Integer lesionadoId;
	private Integer encuentro;
	private Integer evento;
	private Integer tripulacion;
	private Integer resultado;
	private Integer dispositivoTransporte;
	private Integer niegaAtencion;
	private Integer tipoNegacion;
	private String observacionNegacion;
	private Integer entidadRecepcion;
	private String recibidoPor;
	private String registroRecibe;
	private Integer eps;
	private Integer aseguradora;
	private Integer planBeneficios;
	private String placaTripulacion;
	private String nombreResultado;
	private Date fechaCreacion;
	private String tipoIdentificacion;
	
	/**
	 * @return the tipoIdentificacion
	 */
	public String getTipoIdentificacion() {
		return tipoIdentificacion;
	}
	/**
	 * @param tipoIdentificacion the tipoIdentificacion to set
	 */
	public void setTipoIdentificacion(String tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
	}
	public String getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getLesionadoId() {
		return lesionadoId;
	}
	public void setLesionadoId(Integer lesionadoId) {
		this.lesionadoId = lesionadoId;
	}
	public Integer getEncuentro() {
		return encuentro;
	}
	public void setEncuentro(Integer encuentro) {
		this.encuentro = encuentro;
	}
	public Integer getEvento() {
		return evento;
	}
	public void setEvento(Integer evento) {
		this.evento = evento;
	}
	public Integer getTripulacion() {
		return tripulacion;
	}
	public void setTripulacion(Integer tripulacion) {
		this.tripulacion = tripulacion;
	}
	public Integer getResultado() {
		return resultado;
	}
	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}
	public Integer getDispositivoTransporte() {
		return dispositivoTransporte;
	}
	public void setDispositivoTransporte(Integer dispositivoTransporte) {
		this.dispositivoTransporte = dispositivoTransporte;
	}
	public Integer getNiegaAtencion() {
		return niegaAtencion;
	}
	public void setNiegaAtencion(Integer niegaAtencion) {
		this.niegaAtencion = niegaAtencion;
	}
	public Integer getTipoNegacion() {
		return tipoNegacion;
	}
	public void setTipoNegacion(Integer tipoNegacion) {
		this.tipoNegacion = tipoNegacion;
	}
	public String getObservacionNegacion() {
		return observacionNegacion;
	}
	public void setObservacionNegacion(String observacionNegacion) {
		this.observacionNegacion = observacionNegacion;
	}
	public Integer getEntidadRecepcion() {
		return entidadRecepcion;
	}
	public void setEntidadRecepcion(Integer entidadRecepcion) {
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
	public Integer getEps() {
		return eps;
	}
	public void setEps(Integer eps) {
		this.eps = eps;
	}
	public Integer getAseguradora() {
		return aseguradora;
	}
	public void setAseguradora(Integer aseguradora) {
		this.aseguradora = aseguradora;
	}
	public Integer getPlanBeneficios() {
		return planBeneficios;
	}
	public void setPlanBeneficios(Integer planBeneficios) {
		this.planBeneficios = planBeneficios;
	}
	public String getPlacaTripulacion() {
		return placaTripulacion;
	}
	public void setPlacaTripulacion(String placaTripulacion) {
		this.placaTripulacion = placaTripulacion;
	}
	public String getNombreResultado() {
		return nombreResultado;
	}
	public void setNombreResultado(String nombreResultado) {
		this.nombreResultado = nombreResultado;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
