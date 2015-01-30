package com.artica.telesalud.tph.service;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;

public class EncounterServiceTest {

	EncounterService encounterService;
	@Before
	public void init(){
		
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-tph-test.cfg.xml");
		
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager.getInstance();
		manager.createFactory("hibernate-tph-test.cfg.xml");
		
		encounterService = new EncounterService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
	}

	@Test
	public void testCreateEmergencyEncounter() {
		
	}

}
