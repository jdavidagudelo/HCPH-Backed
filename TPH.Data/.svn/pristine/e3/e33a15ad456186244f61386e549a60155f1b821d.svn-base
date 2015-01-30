package com.artica.telesalud.tph.dao.hibernate;

import org.junit.Before;
import org.junit.Test;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.dao.EventoDAO;
import com.artica.telesalud.tph.model.evento.Evento;

public class HibernateEventoDAOTest {

	EventoDAO eventoDAO;
	
	@Before
	public void init(){
		
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager.getInstance();
		manager.createFactory("hibernate-tph-test.cfg.xml");
		
		eventoDAO = new HibernateEventoDAO();
	}
	
	public void testGetAll() {
		
		for(Evento e : eventoDAO.getAll())
			System.out.println("Id del evento: " + e.getEventoId());
	}

	public void testSave() {
		Evento e = eventoDAO.newEvento();
		
		e.setDireccion("La casa de David");
		e.setCreator(2);
		e.setEstado(Evento.ESTADO_ACTIVO);
		System.out.println("hdkhjdsa");
		
		eventoDAO.save(e);
		
		testGetAll();
	}
	@Test
	public void getEncuentros()
	{
	}
}
