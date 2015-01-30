package com.artica.telesalud.tph.controller.model.evento;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.location.CitySpringDto;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.util.JsonDateSerializer;
/**
 * Clase utilizada para mapear eventos a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class EventoSpringDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String ESTADO_ACTIVO = "Activo";
	public static String ESTADO_FINALIZADO = "Finalizado";
	private String otherCause;
	private Integer eventoId;
	private String coordinates;
	private String direccion;
	private CitySpringDto ciudad;
	private ConceptSpringDto zona;
	private ConceptSpringDto causaAtencion;
	private String numeroCaso;
	private Date fechaLlamada;
	private String estado;
	private EventAddressSpringDto eventAddress;
	private Integer creator;
	private Date dateCreated;
	private List<LesionadoSpringDto> lesionados;
	private Integer patientsCount = 0;
	
	public Integer getPatientsCount() {
		return patientsCount;
	}

	public void setPatientsCount(Integer patientsCount) {
		this.patientsCount = patientsCount;
	}

	/**
	 * @return the lesionados
	 */
	public List<LesionadoSpringDto> getLesionados() {
		return lesionados;
	}

	/**
	 * @param lesionados the lesionados to set
	 */
	public void setLesionados(List<LesionadoSpringDto> lesionados) {
		this.lesionados = lesionados;
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
	public EventoSpringDto(Evento evento)
	{
		if(evento == null){
			return;
		}
		coordinates = evento.getCoordinates();
		this.eventoId = evento.getEventoId();
		this.direccion = evento.getDireccion();
		if(evento.getCiudad() != null)
		{
			this.ciudad = new CitySpringDto(evento.getCiudad());
		}
		if(evento.getZona() != null)
		{
			this.zona = new ConceptSpringDto(evento.getZona());
		}
		if(evento.getCausaAtencion() != null)
		{
			this.causaAtencion = new ConceptSpringDto(evento.getCausaAtencion());
		}
		otherCause = evento.getOtherCause();
		this.numeroCaso = evento.getNumeroCaso();
		this.fechaLlamada = evento.getFechaLlamada();
		this.estado = evento.getEstado();
		if(evento.getEventAddress() != null)
		{
			eventAddress = new EventAddressSpringDto(evento.getEventAddress());
		}
		creator = evento.getCreator();
		dateCreated = evento.getDateCreated();
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

	public EventoSpringDto() {
		super();
	}

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
	public CitySpringDto getCiudad() {
		return ciudad;
	}
	public void setCiudad(CitySpringDto ciudad) {
		this.ciudad = ciudad;
	}
	public ConceptSpringDto getZona() {
		return zona;
	}
	public void setZona(ConceptSpringDto zona) {
		this.zona = zona;
	}
	public ConceptSpringDto getCausaAtencion() {
		return causaAtencion;
	}
	public void setCausaAtencion(ConceptSpringDto causaAtencion) {
		this.causaAtencion = causaAtencion;
	}
	public String getNumeroCaso() {
		return numeroCaso;
	}
	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}
	@JsonSerialize(using=JsonDateSerializer.class,
	        include=JsonSerialize.Inclusion.NON_NULL)
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

	/**
	 * @return the eventAddress
	 */
	public EventAddressSpringDto getEventAddress() {
		return eventAddress;
	}

	/**
	 * @param eventAddress the eventAddress to set
	 */
	public void setEventAddress(EventAddressSpringDto eventAddress) {
		this.eventAddress = eventAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((causaAtencion == null) ? 0 : causaAtencion.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result
				+ ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((eventoId == null) ? 0 : eventoId.hashCode());
		result = prime * result
				+ ((fechaLlamada == null) ? 0 : fechaLlamada.hashCode());
		result = prime * result
				+ ((numeroCaso == null) ? 0 : numeroCaso.hashCode());
		result = prime * result + ((zona == null) ? 0 : zona.hashCode());
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
		if (!(obj instanceof EventoSpringDto)) {
			return false;
		}
		EventoSpringDto other = (EventoSpringDto) obj;
		if (causaAtencion == null) {
			if (other.causaAtencion != null) {
				return false;
			}
		} else if (!causaAtencion.equals(other.causaAtencion)) {
			return false;
		}
		if (ciudad == null) {
			if (other.ciudad != null) {
				return false;
			}
		} else if (!ciudad.equals(other.ciudad)) {
			return false;
		}
		if (direccion == null) {
			if (other.direccion != null) {
				return false;
			}
		} else if (!direccion.equals(other.direccion)) {
			return false;
		}
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
			return false;
		}
		if (eventoId == null) {
			if (other.eventoId != null) {
				return false;
			}
		} else if (!eventoId.equals(other.eventoId)) {
			return false;
		}
		if (fechaLlamada == null) {
			if (other.fechaLlamada != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(fechaLlamada), new DateTime(other.fechaLlamada)).getSeconds() != 0) {
			return false;
		}
		if (numeroCaso == null) {
			if (other.numeroCaso != null) {
				return false;
			}
		} else if (!numeroCaso.equals(other.numeroCaso)) {
			return false;
		}
		if (zona == null) {
			if (other.zona != null) {
				return false;
			}
		} else if (!zona.equals(other.zona)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventoSpringDto [eventoId=" + eventoId + ", direccion="
				+ direccion + ", ciudad=" + ciudad + ", zona=" + zona
				+ ", causaAtencion=" + causaAtencion + ", numeroCaso="
				+ numeroCaso + ", fechaLlamada=" + fechaLlamada + ", estado="
				+ estado + "]";
	}



}
