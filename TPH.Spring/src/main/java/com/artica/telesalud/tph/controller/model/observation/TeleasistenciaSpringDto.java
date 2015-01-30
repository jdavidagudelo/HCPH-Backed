package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.encounter.EncounterSpringDto;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
/**
 * Clase utilizada para mapear teleasistencias a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class TeleasistenciaSpringDto {

	private Integer teleasistenciaId;
	private Date fecha;
	private String motivoSolicitud;
	private String detalles;
	private ConceptSpringDto medio;
	private List<NotaEvolucionSpringDto> notasEvolucion;
	private UserSpringDto medicoSolicitante;
	private EncounterSpringDto encounter;
	private Integer creator;
	private Date dateCreated;
	

	/**
	 * @return the encounter
	 */
	public EncounterSpringDto getEncounter() {
		return encounter;
	}
	/**
	 * @param encounter the encounter to set
	 */
	public void setEncounter(EncounterSpringDto encounter) {
		this.encounter = encounter;
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
	public TeleasistenciaSpringDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the teleasistenciaId
	 */
	public Integer getTeleasistenciaId() {
		return teleasistenciaId;
	}
	/**
	 * @param teleasistenciaId the teleasistenciaId to set
	 */
	public void setTeleasistenciaId(Integer teleasistenciaId) {
		this.teleasistenciaId = teleasistenciaId;
	}
	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}
	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	/**
	 * @return the motivoSolicitud
	 */
	public String getMotivoSolicitud() {
		return motivoSolicitud;
	}
	/**
	 * @param motivoSolicitud the motivoSolicitud to set
	 */
	public void setMotivoSolicitud(String motivoSolicitud) {
		this.motivoSolicitud = motivoSolicitud;
	}
	/**
	 * @return the detalles
	 */
	public String getDetalles() {
		return detalles;
	}
	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	/**
	 * @return the medio
	 */
	public ConceptSpringDto getMedio() {
		return medio;
	}
	/**
	 * @param medio the medio to set
	 */
	public void setMedio(ConceptSpringDto medio) {
		this.medio = medio;
	}
	/**
	 * @return the notasEvolucion
	 */
	public List<NotaEvolucionSpringDto> getNotasEvolucion() {
		return notasEvolucion;
	}
	/**
	 * @param notasEvolucion the notasEvolucion to set
	 */
	public void setNotasEvolucion(List<NotaEvolucionSpringDto> notasEvolucion) {
		this.notasEvolucion = notasEvolucion;
	}
	/**
	 * @return the medicoSolicitante
	 */
	public UserSpringDto getMedicoSolicitante() {
		return medicoSolicitante;
	}
	/**
	 * @param medicoSolicitante the medicoSolicitante to set
	 */
	public void setMedicoSolicitante(UserSpringDto medicoSolicitante) {
		this.medicoSolicitante = medicoSolicitante;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((detalles == null) ? 0 : detalles.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime
				* result
				+ ((medicoSolicitante == null) ? 0 : medicoSolicitante
						.hashCode());
		result = prime * result + ((medio == null) ? 0 : medio.hashCode());
		result = prime * result
				+ ((motivoSolicitud == null) ? 0 : motivoSolicitud.hashCode());
		result = prime * result
				+ ((notasEvolucion == null) ? 0 : notasEvolucion.hashCode());
		result = prime
				* result
				+ ((teleasistenciaId == null) ? 0 : teleasistenciaId.hashCode());
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
		if (!(obj instanceof TeleasistenciaSpringDto)) {
			return false;
		}
		TeleasistenciaSpringDto other = (TeleasistenciaSpringDto) obj;
		if (detalles == null) {
			if (other.detalles != null) {
				return false;
			}
		} else if (!detalles.equals(other.detalles)) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(fecha), new DateTime(other.fecha)).getSeconds() != 0) {
			return false;
		}
		if (medicoSolicitante == null) {
			if (other.medicoSolicitante != null) {
				return false;
			}
		} else if (!medicoSolicitante.equals(other.medicoSolicitante)) {
			return false;
		}
		if (medio == null) {
			if (other.medio != null) {
				return false;
			}
		} else if (!medio.equals(other.medio)) {
			return false;
		}
		if (motivoSolicitud == null) {
			if (other.motivoSolicitud != null) {
				return false;
			}
		} else if (!motivoSolicitud.equals(other.motivoSolicitud)) {
			return false;
		}
		if (notasEvolucion == null) {
			if (other.notasEvolucion != null) {
				return false;
			}
		} else if (!notasEvolucion.equals(other.notasEvolucion)) {
			return false;
		}
		if (teleasistenciaId == null) {
			if (other.teleasistenciaId != null) {
				return false;
			}
		} else if (!teleasistenciaId.equals(other.teleasistenciaId)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TeleasistenciaSpringDto [teleasistenciaId=" + teleasistenciaId
				+ ", fecha=" + fecha + ", motivoSolicitud=" + motivoSolicitud
				+ ", detalles=" + detalles + ", medio=" + medio
				+ ", notasEvolucion=" + notasEvolucion + ", medicoSolicitante="
				+ medicoSolicitante + "]";
	}
	
	
}
