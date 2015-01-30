package com.artica.telesalud.tph.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.tph.controller.model.evento.ResponsableAtencionSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientIdentifierTypeSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
class ListResponsablesAtencion extends ArrayList<ResponsableAtencionSpringDto>
{

	private static final long serialVersionUID = 1L;

	public ListResponsablesAtencion() {
		super();
	}
	
}
public class ResponsableAtencionTest {
	static HttpHeaders getHeaders(String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));

		return headers;
	}
	private LesionadoService lesionadoService;
	private UserService userService;
	private EventoService eventoService;
	private PatientService patientService;
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
		lesionadoService = new LesionadoService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		patientService = new PatientService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		eventoService = new EventoService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		encounterService = new EncounterService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		userService = new com.artica.telesalud.persona.service.UserService(
				"com.artica.telesalud.persona.dao.hibernate.HibernatePersonaDAOFactory",
				params);
	}
	@org.junit.Test
	public void testGetResponsablesAtencion() {
		
			HttpEntity<String> requestEntity = new HttpEntity<String>(

			getHeaders("Digitador" + ":" + "Digitador"));
			String url = "http://localhost:8091/TPH.Spring/ResponsableAtencionController/getResponsablesAtencion/{lesionadoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("lesionadoId", 100);
			ResponseEntity<ListResponsablesAtencion> entity = template
					.exchange(url, HttpMethod.GET, requestEntity,
							ListResponsablesAtencion.class, map);
			ListResponsablesAtencion list = entity.getBody();

			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
		
	}
	
	//@Test
	public void test()
	{
		Evento event = eventoService.obtenerEvento(243);
		List<Lesionado> list = lesionadoService.obtenerLesionados(event);
		System.out.println(list.size());
	}

}
