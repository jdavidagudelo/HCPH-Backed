package com.artica.telesalud.tph.controller.model.encounter;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;
import org.joda.time.Seconds;

import com.artica.telesalud.tph.controller.model.location.CitySpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PersonSpringDto;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.util.JsonDateSerializer;
/**
 * Clase utilizada por Spring para mapear encuentros de un paciente a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 */
public class EncounterSpringDto {
	public static String STATE_ACTIVE = "ACTIVO";
	public static String STATE_TERMINATED = "TERMINADO";
	public static String STATE_NO_ATTENDED = "NO ATENDIDO";
	public static String STATE_ATTENDED_NEW = "ATENDIDO CON NOVEDAD";
	public static String STATE_ATTENDED_REVISED = "ATENDIDO REVISADO";
	/**
	 * Id del encuentro
	 */
	private Integer encounterId;
	/**
	 * Tipo de encuentro.
	 */
	private EncounterTypeSpringDto encounterType;
	/**
	 * Paciente asociado al encuentro.
	 */
	private PatientSpringDto patient;
	/**
	 * Proveedor asociado al encuentro
	 */
	private PersonSpringDto provider;
	/**
	 * Ubicacion del encuentro.
	 */
	private CitySpringDto location;
	/**
	 * Fecha del encuentro.
	 */
	private Date encounterDatetime;
	/**
	 * Estado del encuentro.
	 */
	private String state;	
	/**
	 * Id del creador del encuentro.
	 */
	private Integer creator;
	/**
	 * Fecha de creacion del encuentro.
	 */
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

	public EncounterSpringDto() {
		super();
	}

	public EncounterSpringDto(Encounter encounter) {
		if (encounter == null) {
			return;
		}
		encounterId = encounter.getEncounterId();
		if (encounter.getEncounterType() != null) {
			encounterType = new EncounterTypeSpringDto(
					encounter.getEncounterType());
		}
		if (encounter.getPatient() != null) {
			patient = new PatientSpringDto(encounter.getPatient());
		}
		if (encounter.getProvider() != null) {
			provider = new PersonSpringDto(encounter.getProvider());
		}
		if (encounter.getLocation() != null) {
			location = new CitySpringDto(encounter.getLocation());
		}
		encounterDatetime = encounter.getEncounterDatetime();
		state = encounter.getState();
		creator = encounter.getCreator();
		dateCreated = encounter.getDateCreated();
	}

	/**
	 * @return the encounterId
	 */
	public Integer getEncounterId() {
		return encounterId;
	}

	/**
	 * @param encounterId
	 *            the encounterId to set
	 */
	public void setEncounterId(Integer encounterId) {
		this.encounterId = encounterId;
	}

	/**
	 * @return the encounterType
	 */
	public EncounterTypeSpringDto getEncounterType() {
		return encounterType;
	}

	/**
	 * @param encounterType
	 *            the encounterType to set
	 */
	public void setEncounterType(EncounterTypeSpringDto encounterType) {
		this.encounterType = encounterType;
	}

	/**
	 * @return the patient
	 */
	public PatientSpringDto getPatient() {
		return patient;
	}

	/**
	 * @param patient
	 *            the patient to set
	 */
	public void setPatient(PatientSpringDto patient) {
		this.patient = patient;
	}

	/**
	 * @return the provider
	 */
	public PersonSpringDto getProvider() {
		return provider;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(PersonSpringDto provider) {
		this.provider = provider;
	}

	/**
	 * @return the location
	 */
	public CitySpringDto getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(CitySpringDto location) {
		this.location = location;
	}

	/**
	 * @return the encounterDatetime
	 */
	@JsonSerialize(using=JsonDateSerializer.class,
	        include=JsonSerialize.Inclusion.NON_NULL)
	public Date getEncounterDatetime() {
		return encounterDatetime;
	}

	/**
	 * @param encounterDatetime
	 *            the encounterDatetime to set
	 */
	public void setEncounterDatetime(Date encounterDatetime) {
		this.encounterDatetime = encounterDatetime;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((encounterDatetime == null) ? 0 : encounterDatetime
						.hashCode());
		result = prime * result
				+ ((encounterId == null) ? 0 : encounterId.hashCode());
		result = prime * result
				+ ((encounterType == null) ? 0 : encounterType.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
		result = prime * result
				+ ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (!(obj instanceof EncounterSpringDto)) {
			return false;
		}
		EncounterSpringDto other = (EncounterSpringDto) obj;
		if (encounterDatetime == null) {
			if (other.encounterDatetime != null) {
				return false;
			}
		} else if (Seconds.secondsBetween(new DateTime(encounterDatetime), new DateTime(other.encounterDatetime)).getSeconds() != 0) {
			return false;
		}
		if (encounterId == null) {
			if (other.encounterId != null) {
				return false;
			}
		} else if (!encounterId.equals(other.encounterId)) {
			return false;
		}
		if (encounterType == null) {
			if (other.encounterType != null) {
				return false;
			}
		} else if (!encounterType.equals(other.encounterType)) {
			return false;
		}
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		if (patient == null) {
			if (other.patient != null) {
				return false;
			}
		} else if (!patient.equals(other.patient)) {
			return false;
		}
		if (provider == null) {
			if (other.provider != null) {
				return false;
			}
		} else if (!provider.equals(other.provider)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EncounterSpringDto [encounterId=" + encounterId
				+ ", encounterType=" + encounterType + ", patient=" + patient
				+ ", provider=" + provider + ", location=" + location
				+ ", encounterDatetime=" + encounterDatetime + ", state="
				+ state + "]";
	}

}
