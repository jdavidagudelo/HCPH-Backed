package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.location.City;


public interface CityDAO {
	
	public List<City> getAll();
	public List<City> getByState(Integer state);
	public Integer getState(Integer city);
	public City getCity(Integer cityId);
	public City getCityByName(String cityName, String stateName, String countryName);
}
