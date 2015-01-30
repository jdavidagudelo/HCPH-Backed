package com.artica.telesalud.tph.controller.model.encounter;

import java.util.Date;

import com.artica.telesalud.tph.model.encounter.EncounterType;

/**
 * Clase utilizada por Spring para mapear tipos de encuentros a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class EncounterTypeSpringDto {
	/**
	 * Id del tipo de encuentro en la base de datos.
	 */
	private Integer encounterTypeId;
	/**
	 * Nombre del tipo de encuentro.
	 */
	private String name;
	/**
	 * Descripcion del tipo de encuentro.
	 */
	private String description;
	/**
	 * Id del usuario creador del tipo de encuentro.
	 */
	private Integer creator;
	/**
	 * Fecha de creacion del tipo de encuentro.
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
	public EncounterTypeSpringDto(EncounterType encounterType){
		if(encounterType == null)
		{
			return;
		}
		this.encounterTypeId = encounterType.getEncounterTypeId();
		this.name = encounterType.getName();
		this.description = encounterType.getDescription();
		creator = encounterType.getCreator();
		dateCreated = encounterType.getDateCreated();
	}
	
	public EncounterTypeSpringDto() {
		super();
	}

	/**
	 * @return the encounterTypeId
	 */
	public Integer getEncounterTypeId() {
		return encounterTypeId;
	}
	/**
	 * @param encounterTypeId the encounterTypeId to set
	 */
	public void setEncounterTypeId(Integer encounterTypeId) {
		this.encounterTypeId = encounterTypeId;
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
		result = prime * result
				+ ((encounterTypeId == null) ? 0 : encounterTypeId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof EncounterTypeSpringDto)) {
			return false;
		}
		EncounterTypeSpringDto other = (EncounterTypeSpringDto) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (encounterTypeId == null) {
			if (other.encounterTypeId != null) {
				return false;
			}
		} else if (!encounterTypeId.equals(other.encounterTypeId)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EncounterTypeSpringDto [encounterTypeId=" + encounterTypeId
				+ ", name=" + name + ", description=" + description + "]";
	}
	
}
