package com.artica.telesalud.tph.controller.model.location;

import java.io.Serializable;

import com.artica.telesalud.tph.model.location.City;
/**
 * Clase utilizada para mapear ciudades a objetos JSON.
 * @author Juan David Agudelo. jdaaa2009@gmail.com 
 *
 */
public class CitySpringDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private StateProvinceSpringDto stateProvince;
	private String code;
	private String name;
	public CitySpringDto(){
		
	}
	public CitySpringDto(City city) {
		if(city == null)
		{
			return;
		}
		this.cityId = city.getCityId();
		if(city.getStateProvince() != null)
		{
			this.stateProvince = new StateProvinceSpringDto(city.getStateProvince());
		}
		this.code = city.getCode();
		this.name = city.getName();
	}
	/**
	 * @return the cityId
	 */
	public Integer getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the stateProvince
	 */
	public StateProvinceSpringDto getStateProvince() {
		return stateProvince;
	}
	/**
	 * @param stateProvince the stateProvince to set
	 */
	public void setStateProvince(StateProvinceSpringDto stateProvince) {
		this.stateProvince = stateProvince;
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
		result = prime * result + ((cityId == null) ? 0 : cityId.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((stateProvince == null) ? 0 : stateProvince.hashCode());
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
		if (!(obj instanceof CitySpringDto)) {
			return false;
		}
		CitySpringDto other = (CitySpringDto) obj;
		if (cityId == null) {
			if (other.cityId != null) {
				return false;
			}
		} else if (!cityId.equals(other.cityId)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (stateProvince == null) {
			if (other.stateProvince != null) {
				return false;
			}
		} else if (!stateProvince.equals(other.stateProvince)) {
			return false;
		}
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CitySpringDto [cityId=" + cityId + ", stateProvince="
				+ stateProvince + ", code=" + code + ", name=" + name + "]";
	}
	
}
