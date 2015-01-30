package com.artica.telesalud.tph.controller.model.patient;

import java.util.Date;

import com.artica.telesalud.tph.model.patient.PatientIdentifier;
/**
 * Clase utilizada para mapear identificadores de pacientes a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PatientIdentifierSpringDto {
	private Integer PatientIdentifierId;
	private Integer patientId;
	private String identifier;
	private PatientIdentifierTypeSpringDto patientIdentifierType;
	private Boolean preferred;
	private Integer country;
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
	public PatientIdentifierSpringDto() {
		super();
	}
	public PatientIdentifierSpringDto(PatientIdentifier patientIdentifier){
		if(patientIdentifier == null)
		{
			return;
		}
		PatientIdentifierId = patientIdentifier.getPatientIdentifierId();
		if(patientIdentifier.getPatient() != null)
		{
			patientId = patientIdentifier.getPatient().getPatientId();
		}
		identifier= patientIdentifier.getIdentifier();
		if(patientIdentifier.getPatientIdentifierType() != null)
		{
			patientIdentifierType=new PatientIdentifierTypeSpringDto(patientIdentifier.getPatientIdentifierType());
		}
		preferred= patientIdentifier.getPreferred()==0?true:false;
		country= patientIdentifier.getCountry();
		creator = patientIdentifier.getCreator();
		dateCreated = patientIdentifier.getDateCreated();
	}
	/**
	 * @return the patientIdentifierId
	 */
	public Integer getPatientIdentifierId() {
		return PatientIdentifierId;
	}
	/**
	 * @param patientIdentifierId the patientIdentifierId to set
	 */
	public void setPatientIdentifierId(Integer patientIdentifierId) {
		PatientIdentifierId = patientIdentifierId;
	}
	/**
	 * @return the patient
	 */
	public Integer getPatientId() {
		return patientId;
	}
	/**
	 * @param patient the patient to set
	 */
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the identifier
	 */
	public String getIdentifier() {
		return identifier;
	}
	/**
	 * @param identifier the identifier to set
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	/**
	 * @return the patientIdentifierType
	 */
	public PatientIdentifierTypeSpringDto getPatientIdentifierType() {
		return patientIdentifierType;
	}
	/**
	 * @param patientIdentifierType the patientIdentifierType to set
	 */
	public void setPatientIdentifierType(
			PatientIdentifierTypeSpringDto patientIdentifierType) {
		this.patientIdentifierType = patientIdentifierType;
	}
	/**
	 * @return the preferred
	 */
	public Boolean getPreferred() {
		return preferred;
	}
	/**
	 * @param preferred the preferred to set
	 */
	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}
	/**
	 * @return the country
	 */
	public Integer getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(Integer country) {
		this.country = country;
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
				+ ((PatientIdentifierId == null) ? 0 : PatientIdentifierId
						.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result
				+ ((identifier == null) ? 0 : identifier.hashCode());
		result = prime * result
				+ ((patientId == null) ? 0 : patientId.hashCode());
		result = prime
				* result
				+ ((patientIdentifierType == null) ? 0 : patientIdentifierType
						.hashCode());
		result = prime * result
				+ ((preferred == null) ? 0 : preferred.hashCode());
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
		if (!(obj instanceof PatientIdentifierSpringDto)) {
			return false;
		}
		PatientIdentifierSpringDto other = (PatientIdentifierSpringDto) obj;
		if (PatientIdentifierId == null) {
			if (other.PatientIdentifierId != null) {
				return false;
			}
		} else if (!PatientIdentifierId.equals(other.PatientIdentifierId)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (identifier == null) {
			if (other.identifier != null) {
				return false;
			}
		} else if (!identifier.equals(other.identifier)) {
			return false;
		}
		if (patientId == null) {
			if (other.patientId != null) {
				return false;
			}
		} else if (!patientId.equals(other.patientId)) {
			return false;
		}
		if (patientIdentifierType == null) {
			if (other.patientIdentifierType != null) {
				return false;
			}
		} else if (!patientIdentifierType.equals(other.patientIdentifierType)) {
			return false;
		}
		if (preferred == null) {
			if (other.preferred != null) {
				return false;
			}
		} else if (!preferred.equals(other.preferred)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PatientIdentifierSpringDto [PatientIdentifierId="
				+ PatientIdentifierId + ", patientId=" + patientId
				+ ", identifier=" + identifier + ", patientIdentifierType="
				+ patientIdentifierType + ", preferred=" + preferred
				+ ", country=" + country + "]";
	}
	
}
