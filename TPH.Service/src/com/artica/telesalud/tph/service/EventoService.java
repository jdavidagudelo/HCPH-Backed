package com.artica.telesalud.tph.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.dao.EventAddressDAO;
import com.artica.telesalud.tph.dao.EventoDAO;
import com.artica.telesalud.tph.dao.UserDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.user.User;

public class EventoService {
	
	private EventoDAO eventoDAO;
	private UserDAO userDAO;
	private EventAddressDAO eventAddressDAO;
	
	/**
	 * @return the eventoDAO
	 */
	public EventoDAO getEventoDAO() {
		return eventoDAO;
	}


	/**
	 * @param eventoDAO the eventoDAO to set
	 */
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}


	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}


	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}


	public EventoService(String daoClassName, HashMap<String, String> params){
			
			eventoDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getEventoDAO();
			
			userDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getUserDAO();
			eventAddressDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getEventAddressDAO();
			
	}
	
	
	public List<Evento> obtenerEventos(){
		return eventoDAO.getAll();
	}
	
	public Evento obtenerEvento(Integer eventoId){
		return eventoDAO.getEvento(eventoId);
	}
	
	public List<Evento> obtenerEventosActivos(){
		return eventoDAO.getByStatus(Evento.ESTADO_ACTIVO);
	}
	
	public List<Evento> obtenerEventosActivos(Integer userId){
		
		User user = userDAO.getUser(userId);
		
		return eventoDAO.getByStatus(Evento.ESTADO_ACTIVO, userId, user.getPersonId());
	}
	
	public List<Evento> obtenerEventosSolicitudTeleasistencia(){
		
		return eventoDAO.getByStatusTeleasistencias(Evento.ESTADO_ACTIVO);
	}
	
	public List<Evento> obtenerEventosFinalizados(){
		return eventoDAO.getByStatus(Evento.ESTADO_FINALIZADO);
	}
	
	public Evento guardarEvento(Evento evento){
		if(evento.getEventAddress() != null)
		{
			eventAddressDAO.insert(evento.getEventAddress());
		}
		return eventoDAO.save(evento);
	}
	
	public Evento actualizarEvento(Evento evento){
		if(evento.getEventAddress() != null)
		{
			eventAddressDAO.insert(evento.getEventAddress());
		}
		return eventoDAO.update(evento);
	}
	
	public Evento crearNuevoEvento(){
		return eventoDAO.newEvento();
	}
	
	public boolean existeNumeroCaso(String numeroCaso){
		return eventoDAO.existEvento(numeroCaso);
	}
	
	public List<Evento> obtenerEventoReporte(Date fechaDesde,
			Date fechaHasta, String numeroCaso, Integer departamento,
			Integer ciudad, Integer causa, String estado) {
		return eventoDAO.getEventosReporte(fechaDesde, fechaHasta, numeroCaso, departamento, ciudad, causa, estado);
	}
	public Evento getEventoByNumeroCaso(String numeroCaso)
	{
		return eventoDAO.getEventoByNumeroCaso(numeroCaso);
	}
}
