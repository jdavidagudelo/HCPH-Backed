package com.artica.telesalud.tph.controller.model.patient;

import java.util.Date;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.model.person.Person;
/**
 * Clase utilizada para mapear personas a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PersonSpringDto {
	private Integer personId;
	private String gender;
	private Date birthdate;
	private Boolean birthdateEstimated;
	private Integer specialty;
	private String identification;
	private Boolean dead;
	private Date deathDate;
	private ConceptSpringDto causeOfDeath;
	private Integer age;
	private String registro;
	private String firma;
	private ConceptSpringDto erp;
	private ConceptSpringDto nivel;
	private ConceptSpringDto controlAph;
	private PersonNameSpringDto preferredName;
	private PersonAddressSpringDto preferredAddress;
	private Integer creator;
	private Date dateCreated;

	/**
	 * @return the creator
	 */
	public Integer getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
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
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public PersonSpringDto() {
		super();
	}

	public PersonSpringDto(Person person) {
		if (person == null) {
			return;
		}
		this.personId = person.getPersonId();
		this.gender = person.getGender();
		this.birthdate = person.getBirthdate();
		this.birthdateEstimated = person.getBirthdateEstimated();
		this.specialty = person.getSpecialty();
		this.identification = person.getIdentification();
		this.dead = person.getDead();
		this.deathDate = person.getDeathDate();
		if (person.getCauseOfDeath() != null) {
			this.causeOfDeath = new ConceptSpringDto(person.getCauseOfDeath());
		}
		this.age = person.getAge();
		this.registro = person.getRegistro();
		this.firma = person.getFirma();
		if (person.getErp() != null) {
			this.erp = new ConceptSpringDto(person.getErp());
		}
		if (person.getNivel() != null) {
			this.nivel = new ConceptSpringDto(person.getNivel());
		}
		if (person.getControlAph() != null) {
			this.controlAph = new ConceptSpringDto(person.getControlAph());
		}
		if (person.getPreferredAddress() != null) {
			preferredAddress = new PersonAddressSpringDto(
					person.getPreferredAddress());
		}
		if (person.getPreferredName() != null) {
			preferredName = new PersonNameSpringDto(person.getPreferredName());
		}
		creator = person.getCreator();
		dateCreated = person.getDateCreated();
	}

	/**
	 * @return the personId
	 */
	public Integer getPersonId() {
		return personId;
	}

	/**
	 * @param personId
	 *            the personId to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * @return the birthdateEstimated
	 */
	public Boolean getBirthdateEstimated() {
		return birthdateEstimated;
	}

	/**
	 * @param birthdateEstimated
	 *            the birthdateEstimated to set
	 */
	public void setBirthdateEstimated(Boolean birthdateEstimated) {
		this.birthdateEstimated = birthdateEstimated;
	}

	/**
	 * @return the specialty
	 */
	public Integer getSpecialty() {
		return specialty;
	}

	/**
	 * @param specialty
	 *            the specialty to set
	 */
	public void setSpecialty(Integer specialty) {
		this.specialty = specialty;
	}

	/**
	 * @return the identification
	 */
	public String getIdentification() {
		return identification;
	}

	/**
	 * @param identification
	 *            the identification to set
	 */
	public void setIdentification(String identification) {
		this.identification = identification;
	}

	/**
	 * @return the dead
	 */
	public Boolean getDead() {
		return dead;
	}

	/**
	 * @param dead
	 *            the dead to set
	 */
	public void setDead(Boolean dead) {
		this.dead = dead;
	}

	/**
	 * @return the deathDate
	 */
	public Date getDeathDate() {
		return deathDate;
	}

	/**
	 * @param deathDate
	 *            the deathDate to set
	 */
	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	/**
	 * @return the causeOfDeath
	 */
	public ConceptSpringDto getCauseOfDeath() {
		return causeOfDeath;
	}

	/**
	 * @param causeOfDeath
	 *            the causeOfDeath to set
	 */
	public void setCauseOfDeath(ConceptSpringDto causeOfDeath) {
		this.causeOfDeath = causeOfDeath;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the registro
	 */
	public String getRegistro() {
		return registro;
	}

	/**
	 * @param registro
	 *            the registro to set
	 */
	public void setRegistro(String registro) {
		this.registro = registro;
	}

	/**
	 * @return the firma
	 */
	public String getFirma() {
		return firma;
	}

	/**
	 * @param firma
	 *            the firma to set
	 */
	public void setFirma(String firma) {
		this.firma = firma;
	}

	/**
	 * @return the erp
	 */
	public ConceptSpringDto getErp() {
		return erp;
	}

	/**
	 * @param erp
	 *            the erp to set
	 */
	public void setErp(ConceptSpringDto erp) {
		this.erp = erp;
	}

	/**
	 * @return the nivel
	 */
	public ConceptSpringDto getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 *            the nivel to set
	 */
	public void setNivel(ConceptSpringDto nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the controlAph
	 */
	public ConceptSpringDto getControlAph() {
		return controlAph;
	}

	/**
	 * @param controlAph
	 *            the controlAph to set
	 */
	public void setControlAph(ConceptSpringDto controlAph) {
		this.controlAph = controlAph;
	}

	/**
	 * @return the preferredName
	 */
	public PersonNameSpringDto getPreferredName() {
		return preferredName;
	}

	/**
	 * @param preferredName
	 *            the preferredName to set
	 */
	public void setPreferredName(PersonNameSpringDto preferredName) {
		this.preferredName = preferredName;
	}

	/**
	 * @return the preferredAddress
	 */
	public PersonAddressSpringDto getPreferredAddress() {
		return preferredAddress;
	}

	/**
	 * @param preferredAddress
	 *            the preferredAddress to set
	 */
	public void setPreferredAddress(PersonAddressSpringDto preferredAddress) {
		this.preferredAddress = preferredAddress;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result
				+ ((birthdate == null) ? 0 : birthdate.hashCode());
		result = prime
				* result
				+ ((birthdateEstimated == null) ? 0 : birthdateEstimated
						.hashCode());
		result = prime * result
				+ ((causeOfDeath == null) ? 0 : causeOfDeath.hashCode());
		result = prime * result
				+ ((controlAph == null) ? 0 : controlAph.hashCode());
		result = prime * result + ((dead == null) ? 0 : dead.hashCode());
		result = prime * result
				+ ((deathDate == null) ? 0 : deathDate.hashCode());
		result = prime * result + ((erp == null) ? 0 : erp.hashCode());
		result = prime * result + ((firma == null) ? 0 : firma.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result
				+ ((identification == null) ? 0 : identification.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime
				* result
				+ ((preferredAddress == null) ? 0 : preferredAddress.hashCode());
		result = prime * result
				+ ((preferredName == null) ? 0 : preferredName.hashCode());
		result = prime * result
				+ ((registro == null) ? 0 : registro.hashCode());
		result = prime * result
				+ ((specialty == null) ? 0 : specialty.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		if (!(obj instanceof PersonSpringDto)) {
			return false;
		}
		PersonSpringDto other = (PersonSpringDto) obj;
		if (age == null) {
			if (other.age != null) {
				return false;
			}
		} else if (!age.equals(other.age)) {
			return false;
		}
		if (birthdate == null) {
			if (other.birthdate != null) {
				return false;
			}
		} else if (!birthdate.equals(other.birthdate)) {
			return false;
		}
		if (birthdateEstimated == null) {
			if (other.birthdateEstimated != null) {
				return false;
			}
		} else if (!birthdateEstimated.equals(other.birthdateEstimated)) {
			return false;
		}
		if (causeOfDeath == null) {
			if (other.causeOfDeath != null) {
				return false;
			}
		} else if (!causeOfDeath.equals(other.causeOfDeath)) {
			return false;
		}
		if (controlAph == null) {
			if (other.controlAph != null) {
				return false;
			}
		} else if (!controlAph.equals(other.controlAph)) {
			return false;
		}
		if (dead == null) {
			if (other.dead != null) {
				return false;
			}
		} else if (!dead.equals(other.dead)) {
			return false;
		}
		if (deathDate == null) {
			if (other.deathDate != null) {
				return false;
			}
		} else if (!deathDate.equals(other.deathDate)) {
			return false;
		}
		if (erp == null) {
			if (other.erp != null) {
				return false;
			}
		} else if (!erp.equals(other.erp)) {
			return false;
		}
		if (firma == null) {
			if (other.firma != null) {
				return false;
			}
		} else if (!firma.equals(other.firma)) {
			return false;
		}
		if (gender == null) {
			if (other.gender != null) {
				return false;
			}
		} else if (!gender.equals(other.gender)) {
			return false;
		}
		if (identification == null) {
			if (other.identification != null) {
				return false;
			}
		} else if (!identification.equals(other.identification)) {
			return false;
		}
		if (nivel == null) {
			if (other.nivel != null) {
				return false;
			}
		} else if (!nivel.equals(other.nivel)) {
			return false;
		}
		if (personId == null) {
			if (other.personId != null) {
				return false;
			}
		} else if (!personId.equals(other.personId)) {
			return false;
		}
		if (preferredAddress == null) {
			if (other.preferredAddress != null) {
				return false;
			}
		} else if (!preferredAddress.equals(other.preferredAddress)) {
			return false;
		}
		if (preferredName == null) {
			if (other.preferredName != null) {
				return false;
			}
		} else if (!preferredName.equals(other.preferredName)) {
			return false;
		}
		if (registro == null) {
			if (other.registro != null) {
				return false;
			}
		} else if (!registro.equals(other.registro)) {
			return false;
		}
		if (specialty == null) {
			if (other.specialty != null) {
				return false;
			}
		} else if (!specialty.equals(other.specialty)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonSpringDto [personId=" + personId + ", gender=" + gender
				+ ", birthdate=" + birthdate + ", birthdateEstimated="
				+ birthdateEstimated + ", specialty=" + specialty
				+ ", identification=" + identification + ", dead=" + dead
				+ ", deathDate=" + deathDate + ", causeOfDeath=" + causeOfDeath
				+ ", age=" + age + ", registro=" + registro + ", firma="
				+ firma + ", erp=" + erp + ", nivel=" + nivel + ", controlAph="
				+ controlAph + ", preferredName=" + preferredName
				+ ", preferredAddress=" + preferredAddress + "]";
	}

}
