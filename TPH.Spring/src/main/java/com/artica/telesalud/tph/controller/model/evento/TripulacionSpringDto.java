package com.artica.telesalud.tph.controller.model.evento;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import com.artica.telesalud.tph.model.evento.Tripulacion;
import com.artica.telesalud.tph.util.JsonDateSerializer;


/**
 * Clase utilizada para mapear tripulaciones a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com
 */
public class TripulacionSpringDto {
	private Integer tripulacionId;
	private EventoSpringDto evento;
	private String placa;
	private Date horaDespacho;
	private Date horaLlegada;
	private Date horaRegreso;
	private Integer creator;
	private Date dateCreated;
	
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
	public TripulacionSpringDto() {
		super();
	}
	public TripulacionSpringDto(Tripulacion tripulacion){
		if(tripulacion==null){
			return;
		}
		this.tripulacionId = tripulacion.getTripulacionId();
		if(tripulacion.getEvento() != null)
		{
		this.evento = new EventoSpringDto(tripulacion.getEvento());
		}
		this.placa = tripulacion.getPlaca();
		this.horaDespacho = tripulacion.getHoraDespacho();
		this.horaLlegada = tripulacion.getHoraLlegada();
		this.horaRegreso = tripulacion.getHoraRegreso();

		creator = tripulacion.getCreator();
		dateCreated = tripulacion.getDateCreated();
	}
	/**
	 * @return the tripulacionId
	 */
	public Integer getTripulacionId() {
		return tripulacionId;
	}
	/**
	 * @param tripulacionId the tripulacionId to set
	 */
	public void setTripulacionId(Integer tripulacionId) {
		this.tripulacionId = tripulacionId;
	}
	/**
	 * @return the evento
	 */
	public EventoSpringDto getEvento() {
		return evento;
	}
	/**
	 * @param evento the evento to set
	 */
	public void setEvento(EventoSpringDto evento) {
		this.evento = evento;
	}
	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}
	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	/**
	 * @return the horaDespacho
	 */
	@JsonSerialize(using=JsonDateSerializer.class,
	        include=JsonSerialize.Inclusion.NON_NULL)
	public Date getHoraDespacho() {
		return horaDespacho;
	}
	/**
	 * @param horaDespacho the horaDespacho to set
	 */
	public void setHoraDespacho(Date horaDespacho) {
		this.horaDespacho = horaDespacho;
	}
	/**
	 * @return the horaLlegada
	 */
	@JsonSerialize(using=JsonDateSerializer.class,
	        include=JsonSerialize.Inclusion.NON_NULL)
	public Date getHoraLlegada() {
		return horaLlegada;
	}
	/**
	 * @param horaLlegada the horaLlegada to set
	 */
	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}
	/**
	 * @return the horaRegreso
	 */
	@JsonSerialize(using=JsonDateSerializer.class,
	        include=JsonSerialize.Inclusion.NON_NULL)
	public Date getHoraRegreso() {
		return horaRegreso;
	}
	/**
	 * @param horaRegreso the horaRegreso to set
	 */
	public void setHoraRegreso(Date horaRegreso) {
		this.horaRegreso = horaRegreso;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((evento == null) ? 0 : evento.hashCode());
		result = prime * result
				+ ((horaDespacho == null) ? 0 : horaDespacho.hashCode());
		result = prime * result
				+ ((horaLlegada == null) ? 0 : horaLlegada.hashCode());
		result = prime * result
				+ ((horaRegreso == null) ? 0 : horaRegreso.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		result = prime * result
				+ ((tripulacionId == null) ? 0 : tripulacionId.hashCode());
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
		if (!(obj instanceof TripulacionSpringDto)) {
			return false;
		}
		TripulacionSpringDto other = (TripulacionSpringDto) obj;
		if (evento == null) {
			if (other.evento != null) {
				return false;
			}
		} else if (!evento.equals(other.evento)) {
			return false;
		}
		if (horaDespacho == null) {
			if (other.horaDespacho != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(horaDespacho), new DateTime(other.horaDespacho)).getSeconds() != 0) {
			return false;
		}
		if (horaLlegada == null) {
			if (other.horaLlegada != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(horaLlegada), new DateTime(other.horaLlegada)).getSeconds() != 0) {
			return false;
		}
		if (horaRegreso == null) {
			if (other.horaRegreso != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(horaRegreso), new DateTime(other.horaRegreso)).getSeconds() != 0) {
			return false;
		}
		if (placa == null) {
			if (other.placa != null) {
				return false;
			}
		} else if (!placa.equals(other.placa)) {
			return false;
		}
		if (tripulacionId == null) {
			if (other.tripulacionId != null) {
				return false;
			}
		} else if (!tripulacionId.equals(other.tripulacionId)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TripulacionSpringDto [tripulacionId=" + tripulacionId
				+ ", evento=" + evento + ", placa=" + placa + ", horaDespacho="
				+ horaDespacho + ", horaLlegada=" + horaLlegada
				+ ", horaRegreso=" + horaRegreso + "]";
	}
	
}
