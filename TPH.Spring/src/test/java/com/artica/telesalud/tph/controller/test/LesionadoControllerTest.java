package com.artica.telesalud.tph.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.artica.telesalud.persona.model.impl.UserDTO;
import com.artica.telesalud.persona.service.UserService;
import com.artica.telesalud.tph.controller.model.evento.EventAddressSpringDto;
import com.artica.telesalud.tph.controller.model.evento.EventoSpringDto;
import com.artica.telesalud.tph.controller.model.evento.LesionadoSpringDto;
import com.artica.telesalud.tph.controller.model.evento.PrimerRespondienteSpringDto;
import com.artica.telesalud.tph.controller.model.observation.SignosVitalesSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientIdentifierTypeSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientSpringDto;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.patient.PatientIdentifier;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.PatientService;

class ListLesionados extends ArrayList<LesionadoSpringDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListLesionados() {
		super();
	}

}

public class LesionadoControllerTest {
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
	private String baseUrl = "http://localhost:8091/TPH.Spring/";
	@Before
	public void init() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE,
				"hibernate-riesgo.cfg.xml");
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		manager.createFactory("hibernate-tph.cfg.xml");

		lesionadoService = new LesionadoService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		patientService = new PatientService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
		eventoService = new EventoService(
				"com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory",
				params);
				userService = new com.artica.telesalud.persona.service.UserService(
				"com.artica.telesalud.persona.dao.hibernate.HibernatePersonaDAOFactory",
				params);
	}
	
	private Lesionado fillLesionado(Lesionado lesionado) {
		Encounter encounter = lesionado.getEncuentro();
		Patient patient = encounter.getPatient();
		patient = patientService.buscarPatient(encounter.getPatient()
				.getPatientId());
		PatientIdentifier identifier = patientService
				.getPatientIdentifier(patient);
		patient.setPreferredIdentifier(identifier);
		encounter.setPatient(patient);
		lesionado.setEncuentro(encounter);

		return lesionado;
	}

	private List<LesionadoSpringDto> getLesionadosEvento(Integer eventId,
			Integer userId) {
		List<Lesionado> lesionados = new ArrayList<Lesionado>();

		if (eventId != null) {
			Evento event = eventoService.obtenerEvento(eventId);

			UserDTO user = userService.findUserById(userId);
			Integer role = user.getRoleId();
			if (SessionUserApp.isEquipoIntervencion(role)) {
				lesionados = lesionadoService.obtenerLesionados(event);
			} else if (SessionUserApp.isDigitador(role)
					|| SessionUserApp.isMedicoDigitador(role)
					|| SessionUserApp.isAdmin(role)) {
				lesionados = lesionadoService.obtenerLesionados(event);
			} else if (SessionUserApp.isMedicoTeleasistencia(role)) {
				lesionados = lesionadoService
						.obtenerLesionadosSolicitudTeleasistencia(event);
			}
		}
		List<LesionadoSpringDto> result = new ArrayList<LesionadoSpringDto>();
		for (Lesionado lesionado : lesionados) {
			System.out.println(lesionado);
			fillLesionado(lesionado);
			LesionadoSpringDto current = new LesionadoSpringDto(lesionado);
			result.add(current);
		}
		return result;

	}

	private List<EventoSpringDto> getEventos(Integer userId) {
		UserDTO user = userService.findUserById(userId);
		Integer userRoleId = user.getRoleId();
		List<Evento> eventos = null;
		if (SessionUserApp.isEquipoIntervencion(userRoleId)) {

			eventos = eventoService.obtenerEventosActivos(userId);
		} else if (SessionUserApp.isDigitador(userRoleId)
				|| SessionUserApp.isMedicoDigitador(userRoleId)) {
			eventos = eventoService.obtenerEventosActivos();
		} else if (SessionUserApp.isMedicoTeleasistencia(userRoleId)) {
			eventos = eventoService.obtenerEventosSolicitudTeleasistencia();
		}
		if (eventos != null) {
			List<EventoSpringDto> result = new ArrayList<EventoSpringDto>();
			for (Evento evento : eventos) {
				result.add(new EventoSpringDto(evento));
			}
			return result;
		}
		return new ArrayList<EventoSpringDto>();
	}

	@org.junit.Test
	public void testGetLesionadosEvento() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		List<UserDTO> users = userService.getUsers(100, 0);
		for (UserDTO user : users) {
			Integer userId = user.getUserId();
			List<EventoSpringDto> events = getEventos(userId);
			for (EventoSpringDto event : events) {
				Integer eventId = event.getEventoId();
				/*List<LesionadoSpringDto> lesionados = getLesionadosEvento(
						eventId, userId);*/
				String url = baseUrl+"LesionadoController/getLesionadosEvento/{eventoId}/{userId}";
				RestTemplate template = new RestTemplate();
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				map.put("eventoId", eventId);
				map.put("userId", userId);
				ResponseEntity<ListLesionados> entity = template.exchange(url,
						HttpMethod.GET, requestEntity, ListLesionados.class,
						map);
				ListLesionados list = entity.getBody();

				Assert.assertNotNull(template);
				Assert.assertNotNull(entity);
				Assert.assertNotNull(list);
				for(LesionadoSpringDto lesionado : list)
				{
					System.out.println(lesionado.getLesionadoId());
				}
			}
		}
	}
	//@Test
	public void testGetLesionadosEvento1() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		List<UserDTO> users = userService.getUsers(100, 0);
	
			Integer userId = 35;
			List<EventoSpringDto> events = getEventos(userId);
			
				Integer eventId = 273;
				List<LesionadoSpringDto> lesionados = getLesionadosEvento(
						eventId, userId);
				String url = baseUrl+"LesionadoController/getLesionadosEvento/{eventoId}/{userId}";
				RestTemplate template = new RestTemplate();
				HashMap<String, Integer> map = new HashMap<String, Integer>();
				map.put("eventoId", eventId);
				map.put("userId", userId);
				ResponseEntity<ListLesionados> entity = template.exchange(url,
						HttpMethod.GET, requestEntity, ListLesionados.class,
						map);
				ListLesionados list = entity.getBody();

				Assert.assertNotNull(template);
				Assert.assertNotNull(entity);
				Assert.assertNotNull(list);
				Assert.assertTrue(list.equals(lesionados));
			
		
	}
	//@org.junit.Test
	public void testGetLesionado() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		List<UserDTO> users = userService.getUsers(100, 0);
		for (UserDTO user : users) {
			Integer userId = user.getUserId();
			List<EventoSpringDto> events = getEventos(userId);
			for (EventoSpringDto event : events) {
				Integer eventId = event.getEventoId();
				List<LesionadoSpringDto> lesionados = getLesionadosEvento(
						eventId, userId);
				for (LesionadoSpringDto lesionado : lesionados) {
					String url = baseUrl+"LesionadoController/getLesionado/{lesionadoId}";
					RestTemplate template = new RestTemplate();
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put("lesionadoId", lesionado.getLesionadoId());
					ResponseEntity<LesionadoSpringDto> entity = template
							.exchange(url, HttpMethod.GET, requestEntity,
									LesionadoSpringDto.class, map);
					LesionadoSpringDto result = entity.getBody();
					Assert.assertNotNull(template);
					Assert.assertNotNull(entity);
					Assert.assertNotNull(result);
					Assert.assertTrue(result.equals(lesionado));
				}
			}
		}
	}

	 //@org.junit.Test
	public void testCreateLesionado() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		Integer eventId = 100;
		String url = baseUrl+"LesionadoController/createLesionado/{eventoId}/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("eventoId", eventId);
		map.put("userId", 37);
		ResponseEntity<LesionadoSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, LesionadoSpringDto.class, map);
		LesionadoSpringDto lesionado = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(lesionado);

		Integer lesionadoId = lesionado.getLesionadoId();
		LesionadoSpringDto test = new LesionadoSpringDto(
				fillLesionado(lesionadoService.obtenerLesionado(lesionadoId)));
		Assert.assertTrue(lesionado.equals(test));
	}
	public void testSaveRespondiente()
	{
		Integer lesionadoId = 163;
		LesionadoSpringDto modified = new LesionadoSpringDto(
				fillLesionado(lesionadoService.obtenerLesionado(lesionadoId)));
		PrimerRespondienteSpringDto respondiente = new PrimerRespondienteSpringDto();
		respondiente.setLesionado(modified);
	}
	//@org.junit.Test
	public void testSavePatient() {

		Integer lesionadoId = 163;
		LesionadoSpringDto modified = new LesionadoSpringDto(
				fillLesionado(lesionadoService.obtenerLesionado(lesionadoId)));
		PatientSpringDto patient = modified.getEncuentro().getPatient();
		List<SignosVitalesSpringDto> signosVitalesSpringDtos = new ArrayList<SignosVitalesSpringDto>();
		SignosVitalesSpringDto s = new SignosVitalesSpringDto();
		s.setFecha(new Date());
		s.setGlicemia(12.0);
		s.setEncounter(modified.getEncuentro());
		System.out.println(patient.getPerson().getPreferredName());
		patient.setAttendatFamily("Agudelo");
		patient.setAttendatKin("Sr");
		patient.setAttendatName("Juan");
		patient.getPreferredIdentifier().setCountry(1);
		patient.getPreferredIdentifier().setIdentifier("1037524435");
		PatientIdentifierTypeSpringDto type = new PatientIdentifierTypeSpringDto();
		type.setPatientIdentifierTypeId(1);
		patient.getPreferredIdentifier().setPatientIdentifierType(type);
		patient.getPreferredIdentifier().setPreferred(true);
		EventAddressSpringDto eventAddress = new EventAddressSpringDto();
		
		patient.getPerson().getPreferredAddress().setCompleteAddress(new EventAddressSpringDto());
		patient.getPerson().getPreferredName().setGivenName("Juan");
		patient.getPerson().getPreferredName().setFamilyName("Agudelo");
		HttpEntity<LesionadoSpringDto> requestEntity = new HttpEntity<LesionadoSpringDto>(
				modified, getHeaders("Digitador" + ":" + "Digitador"));

		String url = baseUrl+"LesionadoController/saveLesionado/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 37);
		ResponseEntity<LesionadoSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, LesionadoSpringDto.class, map);
		LesionadoSpringDto lesionado = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(lesionado);

		patient = lesionado.getEncuentro().getPatient();
		System.out.println(patient.getPerson().getPreferredAddress());
		Assert.assertTrue(patient.getAttendatFamily().equals("Agudelo"));
		Assert.assertTrue(patient.getAttendatKin().equals("Sr"));
		Assert.assertTrue(patient.getAttendatName().equals("Juan"));
		Assert.assertTrue(patient.getPreferredIdentifier().getIdentifier()
				.equals("1037524435"));
		Assert.assertTrue(patient.getPreferredIdentifier()
				.getPatientIdentifierType().getPatientIdentifierTypeId()
				.equals(1));
		Assert.assertTrue(patient.getPreferredIdentifier().getPreferred()
				.equals(true));
		Assert.assertTrue(patient.getPerson().getPreferredName().getGivenName()
				.equals("Juan"));
		Assert.assertTrue(patient.getPerson().getPreferredName()
				.getFamilyName().equals("Agudelo"));
		// Assert.assertTrue(lesionado.equals(test));
	}

	//@org.junit.Test
	public void testSavePatientComplete() {

		Integer lesionadoId = 163;
		LesionadoSpringDto modified = new LesionadoSpringDto(
				fillLesionado(lesionadoService.obtenerLesionado(lesionadoId)));
		PatientSpringDto patient = modified.getEncuentro().getPatient();
		patient.setAttendatFamily("Agudelo");
		patient.setAttendatKin("Sr");
		patient.setAttendatName("Juan");
		patient.getPreferredIdentifier().setCountry(1);
		patient.getPreferredIdentifier().setIdentifier("1037524435");
		PatientIdentifierTypeSpringDto type = new PatientIdentifierTypeSpringDto();
		type.setPatientIdentifierTypeId(1);
		patient.getPreferredIdentifier().setPatientIdentifierType(type);
		patient.getPreferredIdentifier().setPreferred(true);
		patient.getPerson().getPreferredName().setGivenName("Juan");
		patient.getPerson().getPreferredName().setMiddleName("David");
		patient.getPerson().getPreferredName().setFamilyName("Agudelo");
		patient.getPerson().getPreferredName().setFamilyName2("Agudelo");

		HttpEntity<LesionadoSpringDto> requestEntity = new HttpEntity<LesionadoSpringDto>(
				modified, getHeaders("Digitador" + ":" + "Digitador"));

		String url = baseUrl+"LesionadoController/saveLesionado/{userId}";
		RestTemplate template = new RestTemplate();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("userId", 37);
		ResponseEntity<LesionadoSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, LesionadoSpringDto.class, map);
		LesionadoSpringDto lesionado = entity.getBody();
		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(lesionado);

		System.out.println(lesionado.getEncuentro());
		System.out.println(modified.getEncuentro());

		LesionadoSpringDto test = new LesionadoSpringDto(
				fillLesionado(lesionadoService.obtenerLesionado(lesionadoId)));
		Assert.assertTrue(lesionado.equals(test));

	}

	//@org.junit.Test
	public void testSavePatientCompleteList() {
		for (int i = 155; i <= 167; i++) {
			Integer lesionadoId = i;
			LesionadoSpringDto modified = new LesionadoSpringDto(
					fillLesionado(lesionadoService
							.obtenerLesionado(lesionadoId)));
			PatientSpringDto patient = modified.getEncuentro().getPatient();
			patient.setAttendatFamily("Agudelo");
			patient.setAttendatKin("Sr");
			patient.setAttendatName("Juan");
			patient.getPreferredIdentifier().setCountry(1);
			patient.getPreferredIdentifier().setIdentifier("1037524435");
			PatientIdentifierTypeSpringDto type = new PatientIdentifierTypeSpringDto();
			type.setPatientIdentifierTypeId(1);
			patient.getPreferredIdentifier().setPatientIdentifierType(type);
			patient.getPreferredIdentifier().setPreferred(true);
			patient.getPerson().getPreferredName().setGivenName("Juan");
			patient.getPerson().getPreferredName().setMiddleName("David");
			patient.getPerson().getPreferredName().setFamilyName("Agudelo");
			patient.getPerson().getPreferredName().setFamilyName2("Agudelo");

			HttpEntity<LesionadoSpringDto> requestEntity = new HttpEntity<LesionadoSpringDto>(
					modified, getHeaders("Digitador" + ":" + "Digitador"));

			String url = baseUrl+"LesionadoController/savePatient/{userId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("userId", 37);
			ResponseEntity<LesionadoSpringDto> entity = template.exchange(url,
					HttpMethod.POST, requestEntity, LesionadoSpringDto.class,
					map);
			LesionadoSpringDto lesionado = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(lesionado);

			System.out.println(lesionado.getEncuentro());
			System.out.println(modified.getEncuentro());

			LesionadoSpringDto test = new LesionadoSpringDto(
					fillLesionado(lesionadoService
							.obtenerLesionado(lesionadoId)));
			Assert.assertTrue(lesionado.equals(test));
		}
	}
	public void TestCreateLesionado()
	{
		
	}
}
