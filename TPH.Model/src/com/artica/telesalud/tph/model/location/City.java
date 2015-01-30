package com.artica.telesalud.tph.model.location;

import java.io.Serializable;

public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer cityId;
	private StateProvince stateProvince;
	private String code;
	private String name;
	private Short voided;
	
	/**
	 * @return the voided
	 */
	public Short getVoided() {
		return voided;
	}
	/**
	 * @param voided the voided to set
	 */
	public void setVoided(Short voided) {
		this.voided = voided;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public StateProvince getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(StateProvince stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
