package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.location.StateProvince;

public interface StateProvinceDAO {
	
	public List<StateProvince> getAll();
	public StateProvince getStateByName(String countryName, String stateName);
}
