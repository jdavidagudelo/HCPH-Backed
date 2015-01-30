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
import com.artica.telesalud.tph.controller.model.location.CitySpringDto;
import com.artica.telesalud.tph.controller.user.SessionUserApp;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Evento;
import com.artica.telesalud.tph.service.EventoService;
class ListEventos extends ArrayList<EventoSpringDto>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListEventos() {
		super();
	}
	
}
public class EventoControllerTest {
	private String baseUrl = "http://localhost:8091/TPH.Spring/";
	  static HttpHeaders getHeaders(String auth) {
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		    byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		    headers.add("Authorization", "Basic " + new String(encodedAuthorisation));
		    headers.add("Cache-Control", "no-cache");
		    return headers;
		  }
	  private EventoService eventoService;
	  private UserService userService;
	  @Before
	  public void init()
	  {
		  HashMap<String, String> params = new HashMap<String, String>();
			params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-riesgo.cfg.xml");
			HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
					.getInstance();
			manager.createFactory("hibernate-tph.cfg.xml");
			
			eventoService = new EventoService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
			userService = new com.artica.telesalud.persona.service.UserService("com.artica.telesalud.persona.dao.hibernate.HibernatePersonaDAOFactory", params);
	  }
	 // @org.junit.Test
	  public void testObtenerEvento()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  Integer eventoId = 100;
		  String url = baseUrl +"EventoController/obtenerEvento/{eventoId}";
		  RestTemplate template = new RestTemplate();
		  HashMap<String, Integer> map = new HashMap<String, Integer>();
		  map.put("eventoId", eventoId);
		  Evento event = eventoService.obtenerEvento(eventoId);
		  ResponseEntity<EventoSpringDto> entity = template.exchange(url, HttpMethod.GET, requestEntity, EventoSpringDto.class, map);
		  EventoSpringDto evento = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(evento);
		  Assert.assertTrue(evento.equals(new EventoSpringDto(event)));
	  }

	//@org.junit.Test
	public void testObtenerEventoList() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		List<Evento> events = eventoService.obtenerEventos();
		for (Evento event : events) {
			Integer eventoId = event.getEventoId();
			String url = baseUrl +"EventoController/obtenerEvento/{eventoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("eventoId", eventoId);
			ResponseEntity<EventoSpringDto> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, EventoSpringDto.class, map);
			EventoSpringDto evento = entity.getBody();
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(evento);
			Assert.assertTrue(evento.equals(new EventoSpringDto(event)));
		}
	}
	private List<EventoSpringDto> getEventos(Integer userId)
	{
		UserDTO user = userService.findUserById(userId);
		Integer userRoleId = user.getRoleId();
		List<Evento> eventos = null;
		if(SessionUserApp.isEquipoIntervencion(userRoleId)){
			
			eventos = eventoService.obtenerEventosActivos(userId);
		}else if(SessionUserApp.isDigitador(userRoleId) || SessionUserApp.isMedicoDigitador(userRoleId))
		{
			eventos = eventoService.obtenerEventosActivos();
		}
		else if(SessionUserApp.isMedicoTeleasistencia(userRoleId))
		{
			eventos= eventoService.obtenerEventosSolicitudTeleasistencia();
		}
		if(eventos != null)
		{
			List<EventoSpringDto> result = new ArrayList<EventoSpringDto>();
			for(Evento evento : eventos)
			{
				result.add(new EventoSpringDto(evento));
			}
			return result;
		}
		return new ArrayList<EventoSpringDto>();
	}
	
	//@org.junit.Test
	public void testGetEventos() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		List<UserDTO> users = userService.getUsers(100, 0);
		//for(User user : users)
		{
			Integer userId =35;
			List<EventoSpringDto> events = getEventos(userId);
		
			String url =baseUrl +"EventoController/getEventos/{userId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("userId", userId);
			ResponseEntity<ListEventos> entity = template.exchange(url,
					HttpMethod.GET, requestEntity, ListEventos.class, map);
			ListEventos eventos = entity.getBody();
			System.out.println(eventos.size());
			System.out.println(events.size());
			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(eventos);
			for(int i = 0; i < events.size(); i++)
			{
				//Assert.assertTrue(events.get(i).equals(eventos.get(i)));
			}
		}
		
	}
	@Test
	public void testSaveEvento()
	{
		EventoSpringDto event = new EventoSpringDto();
		HttpEntity<String> requestEntity = new HttpEntity<String>(
		        getHeaders("Digitador" + ":" + "Digitador"));
	  String url = baseUrl+"ConceptController/obtenerCausasAtencion";
	  RestTemplate template = new RestTemplate();
	  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
	  ListConcepts concepts = entity.getBody();
	  event.setCausaAtencion(concepts.get(0));
	  HttpEntity<String> requestEntityZ = new HttpEntity<String>(
		        getHeaders("Digitador" + ":" + "Digitador"));
	  url = baseUrl+"ConceptController/getZonas";
	  template = new RestTemplate();
	  entity = template.exchange(url, HttpMethod.GET, requestEntityZ, ListConcepts.class);
	  concepts = entity.getBody();
	  event.setZona(concepts.get(0));
	  EventAddressSpringDto eventAddress = new EventAddressSpringDto();
	  eventAddress.setBlockName("Bloque");
	  eventAddress.setBlockNumber("14");
	  eventAddress.setMainWay("Calle");
	  eventAddress.setMainWayNumber("12");
	  event.setEventAddress(eventAddress);
	  CitySpringDto city = new CitySpringDto();
	  city.setCityId(1);
	  city.setName("Medellin");
	  event.setCiudad(city);
	  event.setDireccion("Calle falsa 123");
	  event.setCoordinates("(23.2287773, 12.222883)");
	  event.setEstado("Activo");
	  event.setNumeroCaso("25252561");
	  event.setFechaLlamada(new Date());
	  HttpEntity<EventoSpringDto> requestEntityE = new HttpEntity<EventoSpringDto>(event,
		        getHeaders("Digitador" + ":" + "Digitador"));
	  url = baseUrl+"EventoController/saveEvento/{userId}";
	  HashMap<String, Object> map = new HashMap<String, Object>();
	map.put("userId", 35);
	  ResponseEntity<EventoSpringDto> entityE= template.exchange(url, HttpMethod.POST, requestEntityE, EventoSpringDto.class, map);
	  
	  System.out.println(entityE.getBody());
	}
}
