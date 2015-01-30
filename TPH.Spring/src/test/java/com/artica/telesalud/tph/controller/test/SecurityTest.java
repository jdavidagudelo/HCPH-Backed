package com.artica.telesalud.tph.controller.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;
import com.artica.telesalud.tph.controller.model.evento.LesionadoSpringDto;
import com.artica.telesalud.tph.controller.model.observation.AntecedenteSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.service.LesionadoService;
import com.artica.telesalud.tph.service.PatientService;
class ListConcepts extends ArrayList<ConceptSpringDto>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ListConcepts(){
		super();
	}
	
}
public class SecurityTest {
	
	  static HttpHeaders getHeaders(String auth) {
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		    byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		    headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

		    return headers;
		  }
	  //@Test
	  public void thatOrdersCanBeAddedAndQueried() {

	    HttpEntity<String> requestEntity = new HttpEntity<String>(
	        getHeaders("Digitador" + ":" + "Digitador"));
	    String url = "http://localhost:8091/TPH.Spring/ConceptController/getEstadosCiviles";
	    RestTemplate template = new RestTemplate();
	    ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
	    ListConcepts concepts = entity.getBody();
	    for(ConceptSpringDto concept : concepts)
	    	System.out.println(concept.getShortName());
	  }
		private Lesionado fillLesionado(Lesionado lesionado)
		{
			HashMap<String, String> params = new HashMap<String, String>();
			params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-riesgo.cfg.xml");
			PatientService patientService = new PatientService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
			Encounter encounter = lesionado.getEncuentro();
			Patient patient = encounter.getPatient();
			patient = patientService.buscarPatient(encounter.getPatient().getPatientId());
			encounter.setPatient(patient);
			lesionado.setEncuentro(encounter);
			
			return lesionado;
		}
	 // @Test
	  public void testPost()
	  {	
		  
		  HashMap<String, String> params = new HashMap<String, String>();
			params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-tph.cfg.xml");
			LesionadoService lesionadoService = new LesionadoService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
			HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
					.getInstance();
			manager.createFactory("hibernate-tph.cfg.xml");
			LesionadoSpringDto lesionado = new LesionadoSpringDto(fillLesionado(lesionadoService.obtenerLesionado(100)));
		
		  
		  
		  //autenticacion basica, además dentro del cuerpo del mensaje se incluye la información del paciente lesionado
		  HttpEntity<LesionadoSpringDto> requestEntity = new HttpEntity<LesionadoSpringDto>(
				  lesionado,
		        getHeaders("Digitador" + ":" + "Digitador"));
		  //url del servicio POST, incluye variables
		  String url = "http://localhost:8091/TPH.Spring/LesionadoController/savePatient/{userId}";
		  //Parametros en HashMap deben tener el mismo tipo de los que desea recibir el servicio REST
		  HashMap<String, Integer> map = new HashMap<String,Integer>();
		  map.put("userId", 37);
		  //RestTemplate
		  RestTemplate template = new RestTemplate(new SimpleClientHttpRequestFactory());
		  //Se reGETaliza el consumo de un servicio POST. 
		  ResponseEntity<LesionadoSpringDto> entity = template.exchange(url, HttpMethod.POST, requestEntity, LesionadoSpringDto.class, map);
		  System.out.println(entity.getBody().getEncuentro().getPatient().getPerson().getPreferredName().getGivenName());	
	  }
	 @Test
	  public void testAntecedente()
	  {
		  HashMap<String, String> params = new HashMap<String, String>();
			params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-riesgo.cfg.xml");
			HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
					.getInstance();
			manager.createFactory("hibernate-tph.cfg.xml");
			
			PatientService patientService = new PatientService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
			
		  //autenticacion basica, además dentro del cuerpo del mensaje se incluye la información del paciente lesionado
		  AntecedenteSpringDto antecedente = new AntecedenteSpringDto();
		  //antecedente.setIdTipoAntecedente(29);
		  antecedente.setObservacion("Alergia a servicios SOAP");
		  antecedente.setPatient(new PatientSpringDto(patientService.buscarPatient(265)));
		  antecedente.setTipoAntecedente("Alergico");
		  antecedente.setYear(2014);
		  HttpEntity<AntecedenteSpringDto> requestEntity = new HttpEntity<AntecedenteSpringDto>(
				  antecedente,
		        getHeaders("Digitador" + ":" + "Digitador"));
		  //url del servicio POST, incluye variables
		  String url = "http://localhost:8091/TPH.Spring/ObsController/saveAntecedente/{lesionadoId}/{userId}";
		  //Parametros en HashMap deben tener el mismo tipo de los que desea recibir el servicio REST
		  HashMap<String, Integer> map = new HashMap<String,Integer>();
		  map.put("lesionadoId", 37);
		  map.put("userId", 37);
		  //RestTemplate
		  RestTemplate template = new RestTemplate(new SimpleClientHttpRequestFactory());
		  //Se reGETaliza el consumo de un servicio POST. 
		  ResponseEntity<AntecedenteSpringDto> entity = template.exchange(url, HttpMethod.POST, requestEntity, AntecedenteSpringDto.class, map);
		  System.out.println(entity.getBody().getPatient().getPerson().getPreferredName().getGivenName());	
	  }
}
