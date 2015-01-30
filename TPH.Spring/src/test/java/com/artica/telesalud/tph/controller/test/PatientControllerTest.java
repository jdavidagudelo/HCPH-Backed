package com.artica.telesalud.tph.controller.test;

import java.util.ArrayList;
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

import com.artica.telesalud.tph.controller.model.evento.LesionadoSpringDto;
import com.artica.telesalud.tph.controller.model.patient.PatientIdentifierTypeSpringDto;

class ListPatientIdentifierTypes extends
		ArrayList<PatientIdentifierTypeSpringDto> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ListPatientIdentifierTypes() {
		super();
		// TODO Auto-generated constructor stub
	}

}

public class PatientControllerTest {
	private String baseUrl = "http://interoperabilidad.udea.edu.co:8080/TPH.Spring/";
	static HttpHeaders getHeaders(String auth) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
		headers.add("Authorization", "Basic "
				+ new String(encodedAuthorisation));

		return headers;
	}

	@org.junit.Test
	public void testGetPatientIdentifierTypes() {
		
			HttpEntity<String> requestEntity = new HttpEntity<String>(

			getHeaders("Digitador" + ":" + "Digitador"));
			String url = baseUrl+"PatientController/getPatientIdentifierTypes";
			RestTemplate template = new RestTemplate();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			ResponseEntity<ListPatientIdentifierTypes> entity = template
					.exchange(url, HttpMethod.GET, requestEntity,
							ListPatientIdentifierTypes.class, map);
			ListPatientIdentifierTypes list = entity.getBody();

			Assert.assertNotNull(template);
			Assert.assertNotNull(entity);
			Assert.assertNotNull(list);
			Assert.assertTrue(!list.isEmpty());
			for (PatientIdentifierTypeSpringDto p : list) {
				Assert.assertNotNull(p);
				System.out.println(p.getName());
			}
		
	}
}
