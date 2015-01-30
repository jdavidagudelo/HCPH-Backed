package com.artica.telesalud.tph.dao;

import java.util.List;

import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Tripulacion;

public interface TripulacionDAO {
	
	public List<Tripulacion> getAll();
	public Tripulacion getTripulacion(Integer tripulacionId);
	public List<Tripulacion> getTripulacion(Evento evento);
	public Tripulacion save(Tripulacion tripulacion);
	public Tripulacion update(Tripulacion tripulacion);
	public Tripulacion newTripulacion();
	public Tripulacion delete(Tripulacion tripulacion);

}
