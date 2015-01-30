package com.artica.telesalud.tph.dao;

import java.util.Date;
import java.util.List;

import com.artica.telesalud.tph.model.evento.Evento;

public interface EventoDAO {
	
	public List<Evento> getAll();
	public List<Evento> getByStatus(String estado);
	public Evento getEvento(Integer eventoId);
	public Evento save(Evento evento);
	public Evento update(Evento evento);
	public Evento newEvento();
	public Evento delete(Evento evento);
	public boolean existEvento(String numeroCaso);
	public Evento getEventoByNumeroCaso(String numeroCaso);
	public List<Evento> getByStatus(String estado, Integer userId, Integer personId);
	public List<Evento> getByStatusTeleasistencias(String estado);
	public List<Evento> getEventosReporte(Date fechaDesde,
			Date fechaHasta, String numeroCaso, Integer departamento,
			Integer ciudad, Integer causa, String estado);
}
