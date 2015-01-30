package com.artica.telesalud.tph.controller.model.patient;

import java.util.Date;

import com.artica.telesalud.tph.model.patient.PatientIdentifierType;

/**
 * Clase utilizada para mapear tipos de identificacion a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PatientIdentifierTypeSpringDto {
	private Integer patientIdentifierTypeId;
	private String name;
	private String description;
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
	public PatientIdentifierTypeSpringDto(PatientIdentifierType patientIdentifierType)
	{
		if(patientIdentifierType == null)
		{
			return;
		}
		patientIdentifierTypeId =  patientIdentifierType.getPatientIdentifierTypeId();
		name =  patientIdentifierType.getName();
		description =  patientIdentifierType.getDescription();
		creator = patientIdentifierType.getCreator();
		dateCreated = patientIdentifierType.getDateCreated();
	}
	
	public PatientIdentifierTypeSpringDto() {
		super();
	}

	/**
	 * @return the patientIdentifierTypeId
	 */
	public Integer getPatientIdentifierTypeId() {
		return patientIdentifierTypeId;
	}
	/**
	 * @param patientIdentifierTypeId the patientIdentifierTypeId to set
	 */
	public void setPatientIdentifierTypeId(Integer patientIdentifierTypeId) {
		this.patientIdentifierTypeId = patientIdentifierTypeId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime
				* result
				+ ((patientIdentifierTypeId == null) ? 0
						: patientIdentifierTypeId.hashCode());
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
		if (!(obj instanceof PatientIdentifierTypeSpringDto)) {
			return false;
		}
		PatientIdentifierTypeSpringDto other = (PatientIdentifierTypeSpringDto) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (patientIdentifierTypeId == null) {
			if (other.patientIdentifierTypeId != null) {
				return false;
			}
		} else if (!patientIdentifierTypeId
				.equals(other.patientIdentifierTypeId)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PatientIdentifierTypeSpringDto [patientIdentifierTypeId="
				+ patientIdentifierTypeId + ", name=" + name + ", description="
				+ description + "]";
	}
	
}
