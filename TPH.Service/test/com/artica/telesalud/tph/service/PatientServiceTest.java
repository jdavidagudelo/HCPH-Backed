package com.artica.telesalud.tph.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Before;
import org.xml.sax.SAXException;

import com.artica.telesalud.common.data.HibernateSessionFactoryManager;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;

public class PatientServiceTest {
	
	@Before
	public void init(){
		
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(HibernateTPHDAOFactory.TPH_HIBERNATE_CONFIG_FILE, "hibernate-tph-test-service.cfg.xml");
		
		HibernateSessionFactoryManager manager = HibernateSessionFactoryManager.getInstance();
		manager.createFactory("hibernate-tph-test-service.cfg.xml");
		
		new PatientService("com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory", params);
	}

	
    public static boolean validateXMLSchema(String xsdPath, String xmlPath) throws SAXException, IOException{
        
       
            SchemaFactory factory = 
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
     
        return true;
    }

}
