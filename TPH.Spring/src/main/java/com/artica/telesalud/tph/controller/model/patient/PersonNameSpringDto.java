package com.artica.telesalud.tph.controller.model.patient;

import java.util.Date;

import com.artica.telesalud.tph.model.person.PersonName;

/**
 * Clase utilizada para mapear nombres de personas a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class PersonNameSpringDto {
	private Integer personNameId;
	private Boolean preferred;
	private Integer personId;
	private String prefix;
	private String givenName;
	private String middleName;
	private String familyNamePrefix;
	private String familyName;
	private String familyName2;
	private String familyNameSuffix;
	private String degree;
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
	public PersonNameSpringDto() {
		super();
	}
	public PersonNameSpringDto(PersonName personName)
	{
		if(personName==null){
			return;
		}
		personNameId = personName.getPersonNameId();
		preferred = personName.getPreferred()==0?false:true;
		if(personName.getPerson() != null)
		{
		personId = personName.getPerson().getPersonId();
		}
		prefix = personName.getPrefix();
		givenName = personName.getGivenName();
		middleName = personName.getMiddleName();
		familyNamePrefix = personName.getFamilyNamePrefix();
		familyName = personName.getFamilyName();
		familyName2 = personName.getFamilyName2();
		familyNameSuffix = personName.getFamilyNameSuffix();
		degree = personName.getDegree();
		creator = personName.getCreator();
		dateCreated = personName.getDateCreated();
	}
	/**
	 * @return the personNameId
	 */
	public Integer getPersonNameId() {
		return personNameId;
	}
	/**
	 * @param personNameId the personNameId to set
	 */
	public void setPersonNameId(Integer personNameId) {
		this.personNameId = personNameId;
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
	 * @return the person
	 */
	public Integer getPersonId() {
		return personId;
	}
	/**
	 * @param person the person to set
	 */
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the middleName
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * @param middleName the middleName to set
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * @return the familyNamePrefix
	 */
	public String getFamilyNamePrefix() {
		return familyNamePrefix;
	}
	/**
	 * @param familyNamePrefix the familyNamePrefix to set
	 */
	public void setFamilyNamePrefix(String familyNamePrefix) {
		this.familyNamePrefix = familyNamePrefix;
	}
	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}
	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	/**
	 * @return the familyName2
	 */
	public String getFamilyName2() {
		return familyName2;
	}
	/**
	 * @param familyName2 the familyName2 to set
	 */
	public void setFamilyName2(String familyName2) {
		this.familyName2 = familyName2;
	}
	/**
	 * @return the familyNameSuffix
	 */
	public String getFamilyNameSuffix() {
		return familyNameSuffix;
	}
	/**
	 * @param familyNameSuffix the familyNameSuffix to set
	 */
	public void setFamilyNameSuffix(String familyNameSuffix) {
		this.familyNameSuffix = familyNameSuffix;
	}
	/**
	 * @return the degree
	 */
	public String getDegree() {
		return degree;
	}
	/**
	 * @param degree the degree to set
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((degree == null) ? 0 : degree.hashCode());
		result = prime * result
				+ ((familyName == null) ? 0 : familyName.hashCode());
		result = prime * result
				+ ((familyName2 == null) ? 0 : familyName2.hashCode());
		result = prime
				* result
				+ ((familyNamePrefix == null) ? 0 : familyNamePrefix.hashCode());
		result = prime
				* result
				+ ((familyNameSuffix == null) ? 0 : familyNameSuffix.hashCode());
		result = prime * result
				+ ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result
				+ ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		result = prime * result
				+ ((personNameId == null) ? 0 : personNameId.hashCode());
		result = prime * result
				+ ((preferred == null) ? 0 : preferred.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
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
		if (!(obj instanceof PersonNameSpringDto)) {
			return false;
		}
		PersonNameSpringDto other = (PersonNameSpringDto) obj;
		if (degree == null) {
			if (other.degree != null) {
				return false;
			}
		} else if (!degree.equals(other.degree)) {
			return false;
		}
		if (familyName == null) {
			if (other.familyName != null) {
				return false;
			}
		} else if (!familyName.equals(other.familyName)) {
			return false;
		}
		if (familyName2 == null) {
			if (other.familyName2 != null) {
				return false;
			}
		} else if (!familyName2.equals(other.familyName2)) {
			return false;
		}
		if (familyNamePrefix == null) {
			if (other.familyNamePrefix != null) {
				return false;
			}
		} else if (!familyNamePrefix.equals(other.familyNamePrefix)) {
			return false;
		}
		if (familyNameSuffix == null) {
			if (other.familyNameSuffix != null) {
				return false;
			}
		} else if (!familyNameSuffix.equals(other.familyNameSuffix)) {
			return false;
		}
		if (givenName == null) {
			if (other.givenName != null) {
				return false;
			}
		} else if (!givenName.equals(other.givenName)) {
			return false;
		}
		if (middleName == null) {
			if (other.middleName != null) {
				return false;
			}
		} else if (!middleName.equals(other.middleName)) {
			return false;
		}
		if (personId == null) {
			if (other.personId != null) {
				return false;
			}
		} else if (!personId.equals(other.personId)) {
			return false;
		}
		if (personNameId == null) {
			if (other.personNameId != null) {
				return false;
			}
		} else if (!personNameId.equals(other.personNameId)) {
			return false;
		}
		if (preferred == null) {
			if (other.preferred != null) {
				return false;
			}
		} else if (!preferred.equals(other.preferred)) {
			return false;
		}
		if (prefix == null) {
			if (other.prefix != null) {
				return false;
			}
		} else if (!prefix.equals(other.prefix)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PersonNameSpringDto [personNameId=" + personNameId
				+ ", preferred=" + preferred + ", personId=" + personId
				+ ", prefix=" + prefix + ", givenName=" + givenName
				+ ", middleName=" + middleName + ", familyNamePrefix="
				+ familyNamePrefix + ", familyName=" + familyName
				+ ", familyName2=" + familyName2 + ", familyNameSuffix="
				+ familyNameSuffix + ", degree=" + degree + "]";
	}
	
}
