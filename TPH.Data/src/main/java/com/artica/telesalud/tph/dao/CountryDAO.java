package com.artica.telesalud.tph.dao;

import com.artica.telesalud.tph.model.location.Country;

public interface CountryDAO {
	public Country getCountryByName(String country);
}
