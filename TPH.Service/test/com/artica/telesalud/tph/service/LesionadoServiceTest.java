package com.artica.telesalud.tph.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.concept.ConceptSet;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Hospital;
import com.artica.telesalud.tph.model.evento.Lesionado;

public class LesionadoServiceTest {

	private ConceptService conceptService; 
	private LesionadoService lesionadoService;
	private EventoService eventoService; 
	private HospitalService hospitalService; 
	
	@Before
	public void init(){
		
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-tph-test-service.cfg.xml");
		
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager.getInstance();
		manager.createFactory("hibernate-tph-test-service.cfg.xml");
		
		conceptService = new ConceptService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
		lesionadoService = new LesionadoService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
		eventoService = new EventoService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
		hospitalService = new HospitalService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
	}
	
	
	public void testCrearNuevoLesionado() {
		Lesionado l = lesionadoService.crearNuevoLesionado(1, 2);
		
		System.out.println("El id del lesionado es:" + l.getLesionadoId());
	}
	
	public void testGetLesionados() {
		List<Lesionado> lesionados = lesionadoService.obtenerLesionadosPorIdentificacion("15147258",1);
		List<Lesionado> lesionados2 = lesionadoService.obtenerLesionadosPorResponsable("1231231");
		
		System.out.println("Por Identificacion");

		for(Lesionado les:lesionados)
			System.out.println(les.getLesionadoId());
		
		System.out.println("Por Responsables de Atencion");
		
		for(Lesionado les:lesionados2)
			System.out.println(les.getLesionadoId());
	}
	
	public void testGetEventosReporte() {
		List<Evento> eventos = eventoService.obtenerEventoReporte(null, null, null, null, null, null, Evento.ESTADO_ACTIVO);
		for(Evento evento:eventos)
			System.out.println(evento.getEventoId());
		
	}
	
	public void testConceptos() {
		
		ConceptSet conceptSet = new ConceptSet();
		conceptSet.setConcept(conceptService.obtenerConcepto(15));
		
		Concept nuevo = new Concept();
		nuevo.setConceptId(2);
		conceptSet.setConceptSet(nuevo);
		conceptSet.setDateCreated(new Date());
		conceptSet.setSortWeight(new Double(0));
		conceptSet.setCreator(2);
		conceptService.saveConceptSet(conceptSet);
	}
	
	public void testHospitales() {
		
		Hospital hospital = hospitalService.obtener(1);
		hospital.setVoided((short)0);
		hospitalService.update(hospital);
		
	}
	
	@Test
	public void testConceptosActive() {
		
		Concept concepto = conceptService.obtenerConcepto(1);
		concepto.setRetired((short)0);
		conceptService.update(concepto);
	}

}
