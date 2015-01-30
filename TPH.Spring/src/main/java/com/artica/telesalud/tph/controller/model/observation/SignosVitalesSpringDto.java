package com.artica.telesalud.tph.controller.model.observation;

import java.util.Date;

import com.artica.telesalud.tph.concepts.ConceptsCode;
import com.artica.telesalud.tph.controller.model.encounter.EncounterSpringDto;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.observation.ObservationData;
/**
 * Clase utilizada para mapear signos vitales a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class SignosVitalesSpringDto {
	private Integer obsId;
	private Date fecha;
	private Double respiracion;
	private Double paDiastolica;
	private Double paSistolica;
	private Double glicemia;
	private Double pulso;
	private Double temperatura;
	private Double spo2;
	private EncounterSpringDto encounter;
	private Integer creator;
	private Date dateCreated;
	public SignosVitalesSpringDto(){
		
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

	public SignosVitalesSpringDto(ObservationData sv, Encounter encounter){
		if(encounter != null)
		{
			this.encounter = new EncounterSpringDto(encounter);
		}
		creator = sv.getCreator();
		dateCreated = sv.getDateCreated();
		ConceptsCode concepts = new ConceptsCode();
		setObsId(sv.getObsId());
		setFecha((Date)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cFechaSignoVital()));
		setGlicemia((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cGlicemia()));
		setPaDiastolica((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cPresionArterialDiastolica()));
		setPaSistolica((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cPresionArterialSistolica()));
		setPulso((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cFrecuenciaPulso()));
		setRespiracion((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cRespiracion()));
		setTemperatura((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cTemperatura()));
		setSpo2((Double)ObservationData.obtenerValorConcepto(sv.getSons(), concepts.cSPO2()));
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
	 * @return the respiracion
	 */
	public Double getRespiracion() {
		return respiracion;
	}
	/**
	 * @param respiracion the respiracion to set
	 */
	public void setRespiracion(Double respiracion) {
		this.respiracion = respiracion;
	}
	/**
	 * @return the paDiastolica
	 */
	public Double getPaDiastolica() {
		return paDiastolica;
	}
	/**
	 * @param paDiastolica the paDiastolica to set
	 */
	public void setPaDiastolica(Double paDiastolica) {
		this.paDiastolica = paDiastolica;
	}
	/**
	 * @return the paSistolica
	 */
	public Double getPaSistolica() {
		return paSistolica;
	}
	/**
	 * @param paSistolica the paSistolica to set
	 */
	public void setPaSistolica(Double paSistolica) {
		this.paSistolica = paSistolica;
	}
	/**
	 * @return the glicemia
	 */
	public Double getGlicemia() {
		return glicemia;
	}
	/**
	 * @param glicemia the glicemia to set
	 */
	public void setGlicemia(Double glicemia) {
		this.glicemia = glicemia;
	}
	/**
	 * @return the pulso
	 */
	public Double getPulso() {
		return pulso;
	}
	/**
	 * @param pulso the pulso to set
	 */
	public void setPulso(Double pulso) {
		this.pulso = pulso;
	}
	/**
	 * @return the temperatura
	 */
	public Double getTemperatura() {
		return temperatura;
	}
	/**
	 * @param temperatura the temperatura to set
	 */
	public void setTemperatura(Double temperatura) {
		this.temperatura = temperatura;
	}
	/**
	public SignosVitalesSpringDto(){
	 * @return the spo2
	 */
	public Double getSpo2() {
		return spo2;
	}
	/**
	 * @param spo2 the spo2 to set
	 */
	public void setSpo2(Double spo2) {
		this.spo2 = spo2;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encounter == null) ? 0 : encounter.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((glicemia == null) ? 0 : glicemia.hashCode());
		result = prime * result
				+ ((paDiastolica == null) ? 0 : paDiastolica.hashCode());
		result = prime * result
				+ ((paSistolica == null) ? 0 : paSistolica.hashCode());
		result = prime * result + ((pulso == null) ? 0 : pulso.hashCode());
		result = prime * result
				+ ((respiracion == null) ? 0 : respiracion.hashCode());
		result = prime * result + ((spo2 == null) ? 0 : spo2.hashCode());
		result = prime * result
				+ ((temperatura == null) ? 0 : temperatura.hashCode());
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
		if (!(obj instanceof SignosVitalesSpringDto)) {
			return false;
		}
		SignosVitalesSpringDto other = (SignosVitalesSpringDto) obj;
		if (encounter == null) {
			if (other.encounter != null) {
				return false;
			}
		} else if (!encounter.equals(other.encounter)) {
			return false;
		}
		if (fecha == null) {
			if (other.fecha != null) {
				return false;
			}
		} else if (!fecha.equals(other.fecha)) {
			return false;
		}
		if (glicemia == null) {
			if (other.glicemia != null) {
				return false;
			}
		} else if (!glicemia.equals(other.glicemia)) {
			return false;
		}
		if (paDiastolica == null) {
			if (other.paDiastolica != null) {
				return false;
			}
		} else if (!paDiastolica.equals(other.paDiastolica)) {
			return false;
		}
		if (paSistolica == null) {
			if (other.paSistolica != null) {
				return false;
			}
		} else if (!paSistolica.equals(other.paSistolica)) {
			return false;
		}
		if (pulso == null) {
			if (other.pulso != null) {
				return false;
			}
		} else if (!pulso.equals(other.pulso)) {
			return false;
		}
		if (respiracion == null) {
			if (other.respiracion != null) {
				return false;
			}
		} else if (!respiracion.equals(other.respiracion)) {
			return false;
		}
		if (spo2 == null) {
			if (other.spo2 != null) {
				return false;
			}
		} else if (!spo2.equals(other.spo2)) {
			return false;
		}
		if (temperatura == null) {
			if (other.temperatura != null) {
				return false;
			}
		} else if (!temperatura.equals(other.temperatura)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SignosVitalesSpringDto [fecha=" + fecha + ", respiracion="
				+ respiracion + ", paDiastolica=" + paDiastolica
				+ ", paSistolica=" + paSistolica + ", glicemia=" + glicemia
				+ ", pulso=" + pulso + ", temperatura=" + temperatura
				+ ", spo2=" + spo2 + ", encounter=" + encounter + "]";
	}
	
}
