package com.artica.telesalud.tph.controller.model.concept;

import java.io.Serializable;

import com.artica.telesalud.tph.model.concept.Concept;
/**
 * Clase usada para mapear directamente un concepto a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class ConceptSpringDto implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * Id del concepto
	 */
	private Integer conceptId;
	/**
	 * Nombre del concepto
	 */
	private String shortName;
	/**
	 * Descripcion del concepto.
	 */
	private String description;
	

	public ConceptSpringDto(Concept concept)
	{
		if(concept ==null){
			return;
		}
		this.conceptId = concept.getConceptId();
		this.shortName = concept.getShortName();
		this.description = concept.getDescription();
	}
	
	public ConceptSpringDto() {
		super();
	}

	/**
	 * @return the conceptId
	 */
	public Integer getConceptId() {
		return conceptId;
	}
	/**
	 * @param conceptId the conceptId to set
	 */
	public void setConceptId(Integer conceptId) {
		this.conceptId = conceptId;
	}
	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}
	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
				+ ((conceptId == null) ? 0 : conceptId.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
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
		if (!(obj instanceof ConceptSpringDto)) {
			return false;
		}
		ConceptSpringDto other = (ConceptSpringDto) obj;
		if (conceptId == null) {
			if (other.conceptId != null) {
				return false;
			}
		} else if (!conceptId.equals(other.conceptId)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (shortName == null) {
			if (other.shortName != null) {
				return false;
			}
		} else if (!shortName.equals(other.shortName)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConceptSpringDto [conceptId=" + conceptId + ", shortName="
				+ shortName + ", description=" + description + "]";
	}

	
}
