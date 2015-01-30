package com.artica.telesalud.tph.controller.test;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.codec.Base64;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.service.EventoService;
import com.artica.telesalud.tph.service.LocationService;

public class LocationControllerTest {
	LocationService locationService;
	@Test
	public void testLocation()
	{
		  HashMap<String, String> params = new HashMap<String, String>();
			params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-riesgo.cfg.xml");
			HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
					.getInstance();
			manager.createFactory("hibernate-tph.cfg.xml");
			
			locationService = new LocationService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
			System.out.println(locationService.getCityByName("Medellín", "Antioquia", "Colombia") != null);
			System.out.println(locationService.removeAccents("Medellín"));
	
	}
	
}
