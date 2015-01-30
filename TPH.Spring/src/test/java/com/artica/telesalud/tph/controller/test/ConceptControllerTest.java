package com.artica.telesalud.tph.controller.test;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.artica.telesalud.tph.controller.model.concept.ConceptSpringDto;

public class ConceptControllerTest {
	  static HttpHeaders getHeaders(String auth) {
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		    byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		    headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

		    return headers;
		  }
	  private String baseUrl = "http://localhost:8091/TPH.Spring/";
	  @org.junit.Test
	  public void testObtenerCausasAtencion()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/obtenerCausasAtencion";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetEstadosCiviles()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getEstadosCiviles";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetAseguradoras()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getAseguradoras";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetEpss()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getEpss";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetPlanesBeneficios()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getPlanesBeneficios";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetTiposAntecedentes()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getTiposAntecedentes";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetControlesAph()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getControlesAph";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetMediosSolicitudTeleasistencia()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getMediosSolicitudTeleasistencia";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetZonas()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String url = baseUrl+"ConceptController/getZonas";
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
	  @org.junit.Test
	  public void testGetDiagnosticos()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String query = "tumor";
		  String url = baseUrl+"ConceptController/getDiagnosticos/{query}";
		  HashMap<String, String> map = new HashMap<String, String>();
		  map.put("query", query);
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class, map);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getDescription());  
			  Assert.assertTrue(concept.getDescription().toLowerCase().contains(query.toLowerCase()));
		  }

	  }
	  @org.junit.Test
	  public void testGetProcedimientos()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String query = "a";
		  String url = baseUrl+"ConceptController/getProcedimientos/{query}";
		  HashMap<String, String> map = new HashMap<String, String>();
		  map.put("query", query);
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class, map);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getDescription());  
		  }

	  }
	  @org.junit.Test
	  public void testGetProcedimientosHallazgos()
	  {
		  HttpEntity<String> requestEntity = new HttpEntity<String>(
			        getHeaders("Digitador" + ":" + "Digitador"));
		  String query = "a";
		  String url = baseUrl+"ConceptController/getProcedimientosHallazgos/{query}";
		  HashMap<String, String> map = new HashMap<String, String>();
		  map.put("query", query);
		  RestTemplate template = new RestTemplate();
		  ResponseEntity<ListConcepts> entity = template.exchange(url, HttpMethod.GET, requestEntity, ListConcepts.class, map);
		  ListConcepts concepts = entity.getBody();
		  Assert.assertNotNull(template);
		  Assert.assertNotNull(entity);
		  Assert.assertNotNull(concepts);
		  for(ConceptSpringDto concept : concepts)
		  {
			  Assert.assertNotNull(concept);
			  System.out.println(concept.getShortName());  
		  }

	  }
}
