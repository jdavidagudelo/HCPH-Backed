package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.location.Location;


public interface LocationDAO {
	
	public List<Location> getAll();
	public Integer getTotalLocations();
	public Location create(Location l);
	public Location update(Location l);
	public Location getLocation(Integer id);
}
