package com.artica.telesalud.tph.service;

import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.dao.TripulacionDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Tripulacion;

public class TripulacionService {
	
	TripulacionDAO tripulacionDAO;
	
	public TripulacionService(String daoClassName, HashMap<String, String> params){
			
		tripulacionDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getTripulacionDAO();
			
	}
	
	public List<Tripulacion> obtenerTripulaciones(){
		return tripulacionDAO.getAll();
	}
	
	public List<Tripulacion> obtenerTripulaciones(Evento evento){
		return tripulacionDAO.getTripulacion(evento);
	}
	
	public Tripulacion obtenerTripulacion(Integer tripulacionId){
		return tripulacionDAO.getTripulacion(tripulacionId);
	}
	
	public Tripulacion guardarTripulacion(Tripulacion tripulacion){
		return tripulacionDAO.save(tripulacion);
	}
	
	public Tripulacion actualizarTripulacion(Tripulacion tripulacion){
		return tripulacionDAO.update(tripulacion);
	}
	
	public Tripulacion crearNuevaTripulacion(){
		return tripulacionDAO.newTripulacion();
	}

}
