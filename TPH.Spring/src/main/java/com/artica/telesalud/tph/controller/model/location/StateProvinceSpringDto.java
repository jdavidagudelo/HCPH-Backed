package com.artica.telesalud.tph.controller.model.location;

import java.io.Serializable;

import com.artica.telesalud.tph.model.location.StateProvince;

/**
 * Clase utilizada para mapear estados a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class StateProvinceSpringDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer stateProvinceId;
	private CountrySpringDto country;
	private String code;
	private String name;
	
	public StateProvinceSpringDto() {
		super();
	}
	public StateProvinceSpringDto(StateProvince stateProvince)
	{
		if(stateProvince==null)
		{
			return;
		}
		this.stateProvinceId = stateProvince.getStateProvinceId();
		if(stateProvince.getCountry() != null)
		{
		this.country = new CountrySpringDto(stateProvince.getCountry());
		}
		this.code = stateProvince.getCode();
		this.name = stateProvince.getName();
	}
	/**
	 * @return the stateProvinceId
	 */
	public Integer getStateProvinceId() {
		return stateProvinceId;
	}
	/**
	 * @param stateProvinceId the stateProvinceId to set
	 */
	public void setStateProvinceId(Integer stateProvinceId) {
		this.stateProvinceId = stateProvinceId;
	}
	/**
	 * @return the country
	 */
	public CountrySpringDto getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(CountrySpringDto country) {
		this.country = country;
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
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((stateProvinceId == null) ? 0 : stateProvinceId.hashCode());
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
		if (!(obj instanceof StateProvinceSpringDto)) {
			return false;
		}
		StateProvinceSpringDto other = (StateProvinceSpringDto) obj;
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (country == null) {
			if (other.country != null) {
				return false;
			}
		} else if (!country.equals(other.country)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (stateProvinceId == null) {
			if (other.stateProvinceId != null) {
				return false;
			}
		} else if (!stateProvinceId.equals(other.stateProvinceId)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateProvinceSpringDto [stateProvinceId=" + stateProvinceId
				+ ", country=" + country + ", code=" + code + ", name=" + name
				+ "]";
	}
	
}
