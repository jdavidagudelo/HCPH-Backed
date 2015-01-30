package com.artica.telesalud.tph.controller.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.artica.telesalud.tph.controller.model.patient.PatientIdentifierTypeSpringDto;
import com.artica.telesalud.tph.controller.model.user.UserSpringDto;
import com.artica.telesalud.tph.mapper.DateObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class LoginControllerTest {
	static HttpHeaders getHeaders(String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));

		return headers;
	}
	private String baseUrl = "http://interoperabilidad.udea.edu.co:8080/TPH.Spring/";
	  @org.junit.Test
	public void testValidUser()
	{ 
		HttpEntity<String> requestEntity = new HttpEntity<String>(
			  
		        getHeaders("Digitador" + ":" + "Digitador"));
		String url = baseUrl+"LoginController/login/{username}/{password}";
		
		RestTemplate template = new RestTemplate();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "eintervencion");
		map.put("password", "eintervencion");
		ResponseEntity<UserSpringDto> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, UserSpringDto.class,
				map);
		UserSpringDto user = entity.getBody();

		Assert.assertNotNull(template);
		Assert.assertNotNull(entity);
		Assert.assertNotNull(user);
		System.out.println(user.getUserName());
		
	}
	  @org.junit.Test
	public void testInvalidUser() throws JsonParseException, JsonMappingException, IOException
	{ 
		HttpEntity<String> requestEntity = new HttpEntity<String>(
			  
		        getHeaders("Digitador" + ":" + "Digitador"));
		String url = baseUrl+"LoginController/login/{username}/{password}";
		DateObjectMapper mapper = new DateObjectMapper();
		RestTemplate template = new RestTemplate();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("username", "eintervencion");
		map.put("password", "eintervencion1");
		try
		{
			ResponseEntity<String> entity = template.exchange(url,
				HttpMethod.POST, requestEntity, String.class,
				map);
			String user = entity.getBody();
			System.out.println(entity.getStatusCode());
		}
		catch(HttpClientErrorException ex)
		{
			System.out.println(ex.getResponseBodyAsString());
		}
	
	}
}
