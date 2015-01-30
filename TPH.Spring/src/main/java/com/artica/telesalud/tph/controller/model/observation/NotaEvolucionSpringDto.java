package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
/**
 * Clase utilizada para mapear notas de evolucion a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class NotaEvolucionSpringDto {
	private Date fecha;
	
	private ConceptSpringDto dxPrincipal;
	private List<ConceptSpringDto> dxSecundarios;
	private String recomendaciones;
	private UserSpringDto medicoTratante;
	private Integer obsId;
    private String medicoTratanteNombre;
	
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
	 * @return the dxPrincipal
	 */
	public ConceptSpringDto getDxPrincipal() {
		return dxPrincipal;
	}
	/**
	 * @param dxPrincipal the dxPrincipal to set
	 */
	public void setDxPrincipal(ConceptSpringDto dxPrincipal) {
		this.dxPrincipal = dxPrincipal;
	}
	/**
	 * @return the dxSecundarios
	 */
	public List<ConceptSpringDto> getDxSecundarios() {
		return dxSecundarios;
	}
	/**
	 * @param dxSecundarios the dxSecundarios to set
	 */
	public void setDxSecundarios(List<ConceptSpringDto> dxSecundarios) {
		this.dxSecundarios = dxSecundarios;
	}
	/**
	 * @return the recomendaciones
	 */
	public String getRecomendaciones() {
		return recomendaciones;
	}
	/**
	 * @param recomendaciones the recomendaciones to set
	 */
	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = recomendaciones;
	}
	/**
	 * @return the medicoTratante
	 */
	public UserSpringDto getMedicoTratante() {
		return medicoTratante;
	}
	/**
	 * @param medicoTratante the medicoTratante to set
	 */
	public void setMedicoTratante(UserSpringDto medicoTratante) {
		this.medicoTratante = medicoTratante;
	}
	
	
	/**
	 * @return the medicoTratanteNombre
	 */
	public String getMedicoTratanteNombre() {
		return medicoTratanteNombre;
	}
	/**
	 * @param medicoTratanteNombre the medicoTratanteNombre to set
	 */
	public void setMedicoTratanteNombre(String medicoTratanteNombre) {
		this.medicoTratanteNombre = medicoTratanteNombre;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dxPrincipal == null) ? 0 : dxPrincipal.hashCode());
		result = prime * result
				+ ((dxSecundarios == null) ? 0 : dxSecundarios.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((medicoTratante == null) ? 0 : medicoTratante.hashCode());
		result = prime * result
				+ ((recomendaciones == null) ? 0 : recomendaciones.hashCode());
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
		if (!(obj instanceof NotaEvolucionSpringDto)) {
			return false;
		}
		NotaEvolucionSpringDto other = (NotaEvolucionSpringDto) obj;
		if (dxPrincipal == null) {
			if (other.dxPrincipal != null) {
				return false;
			}
		} else if (!dxPrincipal.equals(other.dxPrincipal)) {
			return false;
		}
		if (dxSecundarios == null) {
			if (other.dxSecundarios != null) {
				return false;
			}
		} else if (!dxSecundarios.equals(other.dxSecundarios)) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(fecha), new DateTime(other.fecha)).getSeconds() != 0) {
			return false;
		}
		if (medicoTratante == null) {
			if (other.medicoTratante != null) {
				return false;
			}
		} else if (!medicoTratante.equals(other.medicoTratante)) {
			return false;
		}
		if (recomendaciones == null) {
			if (other.recomendaciones != null) {
				return false;
			}
		} else if (!recomendaciones.equals(other.recomendaciones)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotaEvolucionSpringDto [fecha=" + fecha + ", dxPrincipal="
				+ dxPrincipal + ", dxSecundarios=" + dxSecundarios
				+ ", recomendaciones=" + recomendaciones + ", medicoTratante="
				+ medicoTratante + "]";
	}
	public NotaEvolucionSpringDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
