package com.artica.telesalud.tph.model.location;

import java.io.Serializable;

public class StateProvince implements Serializable{

	private Integer stateProvinceId;
	private Country country;
	private String code;
	private String name;
	
	public Integer getStateProvinceId() {
		return stateProvinceId;
	}
	public void setStateProvinceId(Integer stateProvinceId) {
		this.stateProvinceId = stateProvinceId;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
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
