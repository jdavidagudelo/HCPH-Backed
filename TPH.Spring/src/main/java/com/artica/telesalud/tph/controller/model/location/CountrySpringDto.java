package com.artica.telesalud.tph.controller.model.location;

import com.artica.telesalud.tph.model.location.Country;

/**
 * Clase utilizada para mapear paises a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class CountrySpringDto {
	private Integer countryId;
	private String code;
	private String name;
	public CountrySpringDto(Country country)
	{
		if(country == null){
			return;
		}
		this.countryId = country.getCountryId();
		this.code = country.getCode();
		this.name = country.getName();
	}
	
	public CountrySpringDto() {
		super();
	}

	/**
	 * @return the countryId
	 */
	public Integer getCountryId() {
		return countryId;
	}
	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result
				+ ((countryId == null) ? 0 : countryId.hashCode());
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
		if (!(obj instanceof CountrySpringDto)) {
			return false;
		}
		CountrySpringDto other = (CountrySpringDto) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (countryId == null) {
			if (other.countryId != null) {
				return false;
			}
		} else if (!countryId.equals(other.countryId)) {
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
		return "CountrySpringDto [countryId=" + countryId + ", code=" + code
				+ ", name=" + name + "]";
	}
	
}
