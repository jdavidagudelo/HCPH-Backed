package com.artica.telesalud.tph.controller.model.evento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.artica.telesalud.tph.controller.model.amp.AntecedentesAmpSpringDto;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.encounter.EncounterSpringDto;
import com.artica.telesalud.tph.controller.model.observation.AntecedenteSpringDto;
import com.artica.telesalud.tph.controller.model.observation.CierreAtencionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.EvaluacionSpringDto;
import com.artica.telesalud.tph.controller.model.observation.NotaAclaratoriaSpringDto;
import com.artica.telesalud.tph.controller.model.observation.SignosVitalesSpringDto;
import com.artica.telesalud.tph.controller.model.observation.TeleasistenciaSpringDto;
import com.artica.telesalud.tph.model.evento.Lesionado;
/**
 * Clase utilizada para mapear lesionados a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class LesionadoSpringDto {
	private Integer lesionadoId;
	private EncounterSpringDto encuentro;
	
	private EventoSpringDto evento;
	private TripulacionSpringDto tripulacion;
	private ConceptSpringDto resultado;
	private ConceptSpringDto dispositivoTransporte;
	private Boolean niegaAtencion;
	private ConceptSpringDto tipoNegacion;
	private String observacionNegacion;
	private HospitalSpringDto entidadRecepcion;
	private String recibidoPor;
	private String registroRecibe;
	private ConceptSpringDto eps;
	private ConceptSpringDto aseguradora;
	private ConceptSpringDto planBeneficios;
	private CierreAtencionSpringDto cierreAtencion;
	private Boolean solicitaTeleasistencia;
	private List<EvaluacionSpringDto> evaluaciones = new ArrayList<EvaluacionSpringDto>();
	private List<AntecedentesAmpSpringDto> antecedentesAmp = new ArrayList<AntecedentesAmpSpringDto>();
	private List<SignosVitalesSpringDto> signosVitales = new ArrayList<SignosVitalesSpringDto>();
	private List<TeleasistenciaSpringDto> teleasistencias = new ArrayList<TeleasistenciaSpringDto>();
	private List<ResponsableAtencionSpringDto> responsables = new ArrayList<ResponsableAtencionSpringDto>();
	private List<PrimerRespondienteSpringDto> respondientes = new ArrayList<PrimerRespondienteSpringDto>();
	private List<AntecedenteSpringDto> antecedentes = new ArrayList<AntecedenteSpringDto>();
	private List<NotaAclaratoriaSpringDto> notasAclaratorias = new ArrayList<NotaAclaratoriaSpringDto>();
	private Integer eventLocalIdentifier;	
	private Integer creator;
	private Date dateCreated;
	private String hallazgosClinicos;
	private String impresionDiagnostica;
	private String cargoEncargadoRecepcion;
	private Date fechaUltimaMenstruacion;
	
	/**
	 * @return the cierreAtencion
	 */
	public CierreAtencionSpringDto getCierreAtencion() {
		return cierreAtencion;
	}

	/**
	 * @param cierreAtencion the cierreAtencion to set
	 */
	public void setCierreAtencion(CierreAtencionSpringDto cierreAtencion) {
		this.cierreAtencion = cierreAtencion;
	}

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
	 * @return the antecedentes
	 */
	public List<AntecedenteSpringDto> getAntecedentes() {
		return antecedentes;
	}

	/**
	 * @param antecedentes the antecedentes to set
	 */
	public void setAntecedentes(List<AntecedenteSpringDto> antecedentes) {
		this.antecedentes = antecedentes;
	}

	/**
	 * @return the antecedentesAmp
	 */
	public List<AntecedentesAmpSpringDto> getAntecedentesAmp() {
		return antecedentesAmp;
	}

	/**
	 * @param antecedentesAmp the antecedentesAmp to set
	 */
	public void setAntecedentesAmp(List<AntecedentesAmpSpringDto> antecedentesAmp) {
		this.antecedentesAmp = antecedentesAmp;
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
	 * @return the notasAclaratorias
	 */
	public List<NotaAclaratoriaSpringDto> getNotasAclaratorias() {
		return notasAclaratorias;
	}

	/**
	 * @param notasAclaratorias the notasAclaratorias to set
	 */
	public void setNotasAclaratorias(
			List<NotaAclaratoriaSpringDto> notasAclaratorias) {
		this.notasAclaratorias = notasAclaratorias;
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
	public LesionadoSpringDto() {
		super();
	}
	
	/**
	 * @return the signosVitales
	 */
	public List<SignosVitalesSpringDto> getSignosVitales() {
		return signosVitales;
	}

	/**
	 * @param signosVitales the signosVitales to set
	 */
	public void setSignosVitales(List<SignosVitalesSpringDto> signosVitales) {
		this.signosVitales = signosVitales;
	}

	/**
	 * @return the teleasistencias
	 */
	public List<TeleasistenciaSpringDto> getTeleasistencias() {
		return teleasistencias;
	}

	/**
	 * @param teleasistencias the teleasistencias to set
	 */
	public void setTeleasistencias(List<TeleasistenciaSpringDto> teleasistencias) {
		this.teleasistencias = teleasistencias;
	}

	/**
	 * @return the responsables
	 */
	public List<ResponsableAtencionSpringDto> getResponsables() {
		return responsables;
	}

	/**
	 * @param responsables the responsables to set
	 */
	public void setResponsables(List<ResponsableAtencionSpringDto> responsables) {
		this.responsables = responsables;
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

	/**
	 * @return the evaluaciones
	 */
	public List<EvaluacionSpringDto> getEvaluaciones() {
		return evaluaciones;
	}

	/**
	 * @param evaluaciones the evaluaciones to set
	 */
	public void setEvaluaciones(List<EvaluacionSpringDto> evaluaciones) {
		this.evaluaciones = evaluaciones;
	}

	public LesionadoSpringDto(Lesionado lesionado) {
		if (lesionado == null) {
			return;
		}
		eventLocalIdentifier = lesionado.getEventLocalIdentifier();
		lesionadoId = lesionado.getLesionadoId();
		if (lesionado.getEncuentro() != null) {
			encuentro = new EncounterSpringDto(lesionado.getEncuentro());
		}
		if (lesionado.getEvento() != null) {
			evento = new EventoSpringDto(lesionado.getEvento());
		}
		if (lesionado.getTripulacion() != null) {
			tripulacion = new TripulacionSpringDto(lesionado.getTripulacion());
		}
		if (lesionado.getResultado() != null) {
			resultado = new ConceptSpringDto(lesionado.getResultado());
		}
		if (lesionado.getDispositivoTransporte() != null) {
			dispositivoTransporte = new ConceptSpringDto(
					lesionado.getDispositivoTransporte());
		}
		niegaAtencion = Integer.valueOf(0).equals(lesionado.getNiegaAtencion())? false:true;
		if (lesionado.getTipoNegacion() != null) {
			tipoNegacion = new ConceptSpringDto(lesionado.getTipoNegacion());
		}
		observacionNegacion = lesionado.getObservacionNegacion();
		if (lesionado.getEntidadRecepcion() != null) {
			entidadRecepcion = new HospitalSpringDto(
					lesionado.getEntidadRecepcion());
		}
		recibidoPor = lesionado.getRecibidoPor();
		registroRecibe = lesionado.getRegistroRecibe();
		if (lesionado.getEps() != null) {
			eps = new ConceptSpringDto(lesionado.getEps());
		}
		if (lesionado.getAseguradora() != null) {
			aseguradora = new ConceptSpringDto(lesionado.getAseguradora());
		}
		if (lesionado.getPlanBeneficios() != null) {
			planBeneficios = new ConceptSpringDto(lesionado.getPlanBeneficios());
		}
		solicitaTeleasistencia = Integer.valueOf(0).equals(lesionado.getSolicitaTeleasistencia())?false:true;
		creator = lesionado.getCreator();
		dateCreated = lesionado.getDateCreated();
		cargoEncargadoRecepcion = lesionado.getCargoEncargadoRecepcion();
		fechaUltimaMenstruacion = lesionado.getFechaUltimaMenstruacion();
	}
	
	/**
	 * @return the respondientes
	 */
	public List<PrimerRespondienteSpringDto> getRespondientes() {
		return respondientes;
	}

	/**
	 * @param respondientes the respondientes to set
	 */
	public void setRespondientes(List<PrimerRespondienteSpringDto> respondientes) {
		this.respondientes = respondientes;
	}

	/**
	 * @return the lesionadoId
	 */
	public Integer getLesionadoId() {
		return lesionadoId;
	}

	/**
	 * @param lesionadoId
	 *            the lesionadoId to set
	 */
	public void setLesionadoId(Integer lesionadoId) {
		this.lesionadoId = lesionadoId;
	}

	/**
	 * @return the encuentro
	 */
	public EncounterSpringDto getEncuentro() {
		return encuentro;
	}

	/**
	 * @param encuentro
	 *            the encuentro to set
	 */
	public void setEncuentro(EncounterSpringDto encuentro) {
		this.encuentro = encuentro;
	}

	/**
	 * @return the evento
	 */
	public EventoSpringDto getEvento() {
		return evento;
	}

	/**
	 * @param evento
	 *            the evento to set
	 */
	public void setEvento(EventoSpringDto evento) {
		this.evento = evento;
	}

	/**
	 * @return the tripulacion
	 */
	public TripulacionSpringDto getTripulacion() {
		return tripulacion;
	}

	/**
	 * @param tripulacion
	 *            the tripulacion to set
	 */
	public void setTripulacion(TripulacionSpringDto tripulacion) {
		this.tripulacion = tripulacion;
	}

	/**
	 * @return the resultado
	 */
	public ConceptSpringDto getResultado() {
		return resultado;
	}

	/**
	 * @param resultado
	 *            the resultado to set
	 */
	public void setResultado(ConceptSpringDto resultado) {
		this.resultado = resultado;
	}

	/**
	 * @return the dispositivoTransporte
	 */
	public ConceptSpringDto getDispositivoTransporte() {
		return dispositivoTransporte;
	}

	/**
	 * @param dispositivoTransporte
	 *            the dispositivoTransporte to set
	 */
	public void setDispositivoTransporte(ConceptSpringDto dispositivoTransporte) {
		this.dispositivoTransporte = dispositivoTransporte;
	}

	/**
	 * @return the niegaAtencion
	 */
	public Boolean getNiegaAtencion() {
		return niegaAtencion;
	}

	/**
	 * @param niegaAtencion
	 *            the niegaAtencion to set
	 */
	public void setNiegaAtencion(Boolean niegaAtencion) {
		this.niegaAtencion = niegaAtencion;
	}

	/**
	 * @return the tipoNegacion
	 */
	public ConceptSpringDto getTipoNegacion() {
		return tipoNegacion;
	}

	/**
	 * @param tipoNegacion
	 *            the tipoNegacion to set
	 */
	public void setTipoNegacion(ConceptSpringDto tipoNegacion) {
		this.tipoNegacion = tipoNegacion;
	}

	/**
	 * @return the observacionNegacion
	 */
	public String getObservacionNegacion() {
		return observacionNegacion;
	}

	/**
	 * @param observacionNegacion
	 *            the observacionNegacion to set
	 */
	public void setObservacionNegacion(String observacionNegacion) {
		this.observacionNegacion = observacionNegacion;
	}

	/**
	 * @return the entidadRecepcion
	 */
	public HospitalSpringDto getEntidadRecepcion() {
		return entidadRecepcion;
	}

	/**
	 * @param entidadRecepcion
	 *            the entidadRecepcion to set
	 */
	public void setEntidadRecepcion(HospitalSpringDto entidadRecepcion) {
		this.entidadRecepcion = entidadRecepcion;
	}

	/**
	 * @return the recibidoPor
	 */
	public String getRecibidoPor() {
		return recibidoPor;
	}

	/**
	 * @param recibidoPor
	 *            the recibidoPor to set
	 */
	public void setRecibidoPor(String recibidoPor) {
		this.recibidoPor = recibidoPor;
	}

	/**
	 * @return the registroRecibe
	 */
	public String getRegistroRecibe() {
		return registroRecibe;
	}

	/**
	 * @param registroRecibe
	 *            the registroRecibe to set
	 */
	public void setRegistroRecibe(String registroRecibe) {
		this.registroRecibe = registroRecibe;
	}

	/**
	 * @return the eps
	 */
	public ConceptSpringDto getEps() {
		return eps;
	}

	/**
	 * @param eps
	 *            the eps to set
	 */
	public void setEps(ConceptSpringDto eps) {
		this.eps = eps;
	}

	/**
	 * @return the aseguradora
	 */
	public ConceptSpringDto getAseguradora() {
		return aseguradora;
	}

	/**
	 * @param aseguradora
	 *            the aseguradora to set
	 */
	public void setAseguradora(ConceptSpringDto aseguradora) {
		this.aseguradora = aseguradora;
	}

	/**
	 * @return the planBeneficios
	 */
	public ConceptSpringDto getPlanBeneficios() {
		return planBeneficios;
	}

	/**
	 * @param planBeneficios
	 *            the planBeneficios to set
	 */
	public void setPlanBeneficios(ConceptSpringDto planBeneficios) {
		this.planBeneficios = planBeneficios;
	}

	/**
	 * @return the solicitaTeleasistencia
	 */
	public Boolean getSolicitaTeleasistencia() {
		return solicitaTeleasistencia;
	}

	/**
	 * @param solicitaTeleasistencia
	 *            the solicitaTeleasistencia to set
	 */
	public void setSolicitaTeleasistencia(Boolean solicitaTeleasistencia) {
		this.solicitaTeleasistencia = solicitaTeleasistencia;
	}
	
	/**
	 * @return the hallazgosClinicos
	 */
	public String getHallazgosClinicos() {
		return hallazgosClinicos;
	}

	/**
	 * @param hallazgosClinicos the hallazgosClinicos to set
	 */
	public void setHallazgosClinicos(String hallazgosClinicos) {
		this.hallazgosClinicos = hallazgosClinicos;
	}

	/**
	 * @return the impresionDiagnostica
	 */
	public String getImpresionDiagnostica() {
		return impresionDiagnostica;
	}

	/**
	 * @param impresionDiagnostica the impresionDiagnostica to set
	 */
	public void setImpresionDiagnostica(String impresionDiagnostica) {
		this.impresionDiagnostica = impresionDiagnostica;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aseguradora == null) ? 0 : aseguradora.hashCode());
		result = prime
				* result
				+ ((dispositivoTransporte == null) ? 0 : dispositivoTransporte
						.hashCode());
		result = prime * result
				+ ((encuentro == null) ? 0 : encuentro.hashCode());
		result = prime
				* result
				+ ((entidadRecepcion == null) ? 0 : entidadRecepcion.hashCode());
		result = prime * result + ((eps == null) ? 0 : eps.hashCode());
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result
				+ ((lesionadoId == null) ? 0 : lesionadoId.hashCode());
		result = prime * result
				+ ((niegaAtencion == null) ? 0 : niegaAtencion.hashCode());
		result = prime
				* result
				+ ((observacionNegacion == null) ? 0 : observacionNegacion
						.hashCode());
		result = prime * result
				+ ((planBeneficios == null) ? 0 : planBeneficios.hashCode());
		result = prime * result
				+ ((recibidoPor == null) ? 0 : recibidoPor.hashCode());
		result = prime * result
				+ ((registroRecibe == null) ? 0 : registroRecibe.hashCode());
		result = prime * result
				+ ((resultado == null) ? 0 : resultado.hashCode());
		result = prime
				* result
				+ ((solicitaTeleasistencia == null) ? 0
						: solicitaTeleasistencia.hashCode());
		result = prime * result
				+ ((tipoNegacion == null) ? 0 : tipoNegacion.hashCode());
		result = prime * result
				+ ((tripulacion == null) ? 0 : tripulacion.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof LesionadoSpringDto)) {
			return false;
		}
		LesionadoSpringDto other = (LesionadoSpringDto) obj;
		if (aseguradora == null) {
			if (other.aseguradora != null) {
				return false;
			}
		} else if (!aseguradora.equals(other.aseguradora)) {
			return false;
		}
		if (dispositivoTransporte == null) {
			if (other.dispositivoTransporte != null) {
				return false;
			}
		} else if (!dispositivoTransporte.equals(other.dispositivoTransporte)) {
			return false;
		}
		if (encuentro == null) {
			if (other.encuentro != null) {
				return false;
			}
		} else if (!encuentro.equals(other.encuentro)) {
			return false;
		}
		if (entidadRecepcion == null) {
			if (other.entidadRecepcion != null) {
				return false;
			}
		} else if (!entidadRecepcion.equals(other.entidadRecepcion)) {
			return false;
		}
		if (eps == null) {
			if (other.eps != null) {
				return false;
			}
		} else if (!eps.equals(other.eps)) {
			return false;
		}
		if (evento == null) {
			if (other.evento != null) {
				return false;
			}
		} else if (!evento.equals(other.evento)) {
			return false;
		}
		if (lesionadoId == null) {
			if (other.lesionadoId != null) {
				return false;
			}
		} else if (!lesionadoId.equals(other.lesionadoId)) {
			return false;
		}
		if (niegaAtencion == null) {
			if (other.niegaAtencion != null) {
				return false;
			}
		} else if (!niegaAtencion.equals(other.niegaAtencion)) {
			return false;
		}
		if (observacionNegacion == null) {
			if (other.observacionNegacion != null) {
				return false;
			}
		} else if (!observacionNegacion.equals(other.observacionNegacion)) {
			return false;
		}
		if (planBeneficios == null) {
			if (other.planBeneficios != null) {
				return false;
			}
		} else if (!planBeneficios.equals(other.planBeneficios)) {
			return false;
		}
		if (recibidoPor == null) {
			if (other.recibidoPor != null) {
				return false;
			}
		} else if (!recibidoPor.equals(other.recibidoPor)) {
			return false;
		}
		if (registroRecibe == null) {
			if (other.registroRecibe != null) {
				return false;
			}
		} else if (!registroRecibe.equals(other.registroRecibe)) {
			return false;
		}
		if (resultado == null) {
			if (other.resultado != null) {
				return false;
			}
		} else if (!resultado.equals(other.resultado)) {
			return false;
		}
		if (solicitaTeleasistencia == null) {
			if (other.solicitaTeleasistencia != null) {
				return false;
			}
		} else if (!solicitaTeleasistencia.equals(other.solicitaTeleasistencia)) {
			return false;
		}
		if (tipoNegacion == null) {
			if (other.tipoNegacion != null) {
				return false;
			}
		} else if (!tipoNegacion.equals(other.tipoNegacion)) {
			return false;
		}
		if (tripulacion == null) {
			if (other.tripulacion != null) {
				return false;
			}
		} else if (!tripulacion.equals(other.tripulacion)) {
			return false;
		}
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LesionadoSpringDto [lesionadoId=" + lesionadoId
				+ ", encuentro=" + encuentro + ", evento=" + evento
				+ ", tripulacion=" + tripulacion + ", resultado=" + resultado
				+ ", dispositivoTransporte=" + dispositivoTransporte
				+ ", niegaAtencion=" + niegaAtencion + ", tipoNegacion="
				+ tipoNegacion + ", observacionNegacion=" + observacionNegacion
				+ ", entidadRecepcion=" + entidadRecepcion + ", recibidoPor="
				+ recibidoPor + ", registroRecibe=" + registroRecibe + ", eps="
				+ eps + ", aseguradora=" + aseguradora + ", planBeneficios="
				+ planBeneficios + ", solicitaTeleasistencia="
				+ solicitaTeleasistencia + "]";
	}

}
