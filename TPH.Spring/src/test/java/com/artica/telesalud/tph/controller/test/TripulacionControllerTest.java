package com.artica.telesalud.tph.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.model.evento.TripulacionSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
class ListTripulaciones extends ArrayList<TripulacionSpringDto>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListTripulaciones() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
public class TripulacionControllerTest {
	static HttpHeaders getHeaders(String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));

		return headers;
	}
	private PatientService patientService;
	private EventoService eventoService;
	private EncounterService encounterService;
	private ObsService obsService;
	private ConceptService conceptService;

	@Before
	public void init() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				"hibernate-riesgo.cfg.xml");
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory("hibernate-tph.cfg.xml");
		conceptService = new ConceptService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		obsService = new ObsService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);

		patientService = new PatientService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);

		encounterService = new EncounterService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);

		eventoService = new EventoService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		
	
	}
	@Test
	public void testGetTripulacionesEvento() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Evento evento : eventoService.obtenerEventos()) {
			Integer eventoId = evento.getEventoId();
			String url = "http://localhost:8091/TPH.Spring/TripulacionController/getTripulacionesEvento/{eventoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("eventoId", eventoId);
			ResponseEntity<ListTripulaciones> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ListTripulaciones.class,
					map);
			ListTripulaciones list = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			//Assert.assertNotNull(list);
			System.out.println(evento.getEventoId()+"=>"+list);
		}
	}
}
