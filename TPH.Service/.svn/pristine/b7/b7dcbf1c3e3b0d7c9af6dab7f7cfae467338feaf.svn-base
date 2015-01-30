package com.artica.telesalud.tph.service;

import java.util.HashMap;
import java.util.List;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.dao.PersonAddressDAO;
import com.artica.telesalud.tph.dao.PersonDAO;
import com.artica.telesalud.tph.dao.PersonNameDAO;
import com.artica.telesalud.tph.dao.ResponsableAtencionDAO;
import com.artica.telesalud.tph.dao.UserDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.ResponsableAtencion;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.model.user.User;

public class ResponsableAtencionService {
	
	ResponsableAtencionDAO responsableAtencionDAO;
	PersonDAO personDAO;
	PersonNameDAO personNameDAO;
	PersonAddressDAO personAddressDAO;
	UserDAO userDAO;
	PatientService patientService;
	
	public ResponsableAtencionService(String daoClassName, HashMap<String, String> params){
			
			responsableAtencionDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getResponsableAtencionDAO();
			
			personDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getPersonDAO();
			
			personNameDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getPersonNameDAO();
			
			personAddressDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
					daoClassName, params).getPersonAddressDAO();
			
			userDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class,
					daoClassName, params).getUserDAO();
			
			patientService = new PatientService(daoClassName, params);
	}
	
	
	public List<ResponsableAtencion> obtenerResponsablesAtencion(Lesionado lesionado){
		
		List<ResponsableAtencion> responsables = responsableAtencionDAO.getAll(lesionado);
		for(ResponsableAtencion ra : responsables){
			
			Person person = personDAO.findPerson(ra.getPersona());
			
			person.setPreferredName(personNameDAO.getPreferredPersonName(person));
			person.setPreferredAddress(personAddressDAO.getPreferredPersonAddress(person));
			
			ra.setPerson(person);
			
		}
		
		return responsables;
		
	}
	
	public ResponsableAtencion obtenerPorRegistro(String registro){

		Person person = personDAO.findPersonByRegister(registro);
		
		if(person!=null){
			
			ResponsableAtencion ra = new ResponsableAtencion();
			
			ra.setPerson(person);
			ra.setPersona(person.getPersonId());
			
			User user = userDAO.getUserByPerson(person.getPersonId());
			if(user!=null) 
				ra.setExterno(0);
			else
				ra.setExterno(1);
			
			return ra;
			
		}
		return null;
		
	}
	
	public ResponsableAtencion guardar(Lesionado lesionado, Integer personaId, String nombres, String apellidos, String registro, boolean esExterno, Integer controlAph, Integer creator, Integer responsableAtencionId){
		
		List<ResponsableAtencion> responsables = responsableAtencionDAO.getAll(lesionado);
		for(ResponsableAtencion ra : responsables){
			Person person = personDAO.findPerson(ra.getPersona());
			if(person .getRegistro().equals(registro))
			{
				person.setPreferredName(personNameDAO.getPreferredPersonName(person));
				person.setPreferredAddress(personAddressDAO.getPreferredPersonAddress(person));
				ra.setPerson(person);
				return ra;
			}
			
		}
		ResponsableAtencion responsableAtencion = null;
		if(responsableAtencionId != null)
		{
			responsableAtencion = responsableAtencionDAO.get(responsableAtencionId);
		}
		
		if(responsableAtencion == null)
		{
			responsableAtencion = new ResponsableAtencion();
			responsableAtencion.setCreator(creator);
			responsableAtencion.setExterno(esExterno?1:0);
			responsableAtencion.setLesionado(lesionado);

			if(personaId==null){
				
				Person person = patientService.savePerson(null,null, creator, null, null, registro, null, null, null, null, controlAph);
				patientService.savePersonName(person, 1, nombres, null, apellidos, null, creator);
				patientService.savePersonAddress(person, 1, null, null, null, null, null, null, null);
			
				responsableAtencion.setPersona(person.getPersonId());
			}else
			{
				responsableAtencion.setPersona(personaId);
			}
			responsableAtencion = responsableAtencionDAO.insert(responsableAtencion);
		}
		return responsableAtencion;
	}
	
	
	
	
}
