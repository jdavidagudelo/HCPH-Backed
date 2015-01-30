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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.controller.model.amp.AntecedentesAmpSpringDto;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.encounter.Encounter;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.service.ConceptService;
import com.artica.telesalud.tph.service.EncounterService;
import com.artica.telesalud.tph.service.ObsService;
import com.artica.telesalud.tph.service.PatientService;
class ListAntecedentesAmp extends ArrayList<AntecedentesAmpSpringDto>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListAntecedentesAmp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
public class MessageControllerTest {
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
	
	}
	//@Test
	public void testInteroperarPaciente() {

		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));

		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = "http://localhost:8091/TPH.Spring/MessageController/interoperarPaciente/{lesionadoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("lesionadoId", encounterId);
			try
			{
			ResponseEntity<String> entity = template.exchange(
					url, HttpMethod.POST, requestEntity,
					String.class, map);
			String list = entity.getBody();
			Assert.assertNotNull(template);
		
			System.out.println(list);
			}
			catch(HttpClientErrorException ex)
			{
				System.out.println(ex.getResponseBodyAsString());
			}
			catch(Exception ex)
			{
				System.out.println(encounter.getEncounterId());
			}
		}
	}
	//@Test
	public void testSendEMRDocument() {
		List<String> ehrs = Arrays.asList("co.udea.telesalud.hcteleasis");
		HttpEntity<List<String>> requestEntity = new HttpEntity<List<String>>(ehrs,
				getHeaders("Digitador" + ":" + "Digitador"));
		
		for (Encounter encounter : encounterService.getAll()) {
			Integer encounterId = encounter.getEncounterId();
			String url = "http://localhost:8091/TPH.Spring/MessageController/sendEMRDocument/{encounterId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("encounterId", encounterId);
			try
			{
			ResponseEntity<String> entity = template.exchange(
					url, HttpMethod.POST, requestEntity,
					String.class, map);
			String list = entity.getBody();
			Assert.assertNotNull(template);
		
			System.out.println(list+"=>"+encounterId);
			}
			catch(HttpClientErrorException ex)
			{
				System.out.println(ex.getResponseBodyAsString());
			}
			catch(Exception ex)
			{
				System.out.println("abc"+encounter.getEncounterId());
			}
		}
	}
	@Test
	public void testRequestAntecedentesAmp() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		
		for (Patient patient : patientService.getAll()) {
			Integer patientId = patient.getPatientId();
			String url = "http://interoperabilidad.udea.edu.co:8080/TPH.Spring/MessageController/requestAntecedentesPaciente/{patientId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("patientId", patientId);
			try
			{
			ResponseEntity<String> entity = template.exchange(
					url, HttpMethod.POST, requestEntity,
					String.class, map);
			String list = entity.getBody();
			Assert.assertNotNull(template);
		
			System.out.println(list+"=>"+patientId);
			}
			catch(HttpClientErrorException ex)
			{
				//System.out.println(ex.getResponseBodyAsString());
			}
			catch(Exception ex)
			{
				//System.out.println(patient.getPatientId());
				//ex.printStackTrace();
			}
		}
	}
	//@Test
	public void testGetAntecedentesAmp() {
		HttpEntity<String> requestEntity = new HttpEntity<String>(
				getHeaders("Digitador" + ":" + "Digitador"));
		
		for (Encounter encounter : encounterService.getAll()) {
			Integer patientId = encounter.getEncounterId();
			String url = "http://localhost:8091/TPH.Spring/MessageController/getAntecedentesAmp/{lesionadoId}";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("lesionadoId", patientId);
			try
			{
			ResponseEntity<ListAntecedentesAmp> entity = template.exchange(
					url, HttpMethod.GET, requestEntity,
					ListAntecedentesAmp.class, map);
			ListAntecedentesAmp list = entity.getBody();
			Assert.assertNotNull(template);
		
			System.out.println(list+"=>"+patientId);
			}
			catch(HttpClientErrorException ex)
			{
				//System.out.println(ex.getResponseBodyAsString());
			}
			catch(Exception ex)
			{
				//System.out.println(patient.getPatientId());
				//ex.printStackTrace();
			}
		}
	}
}
