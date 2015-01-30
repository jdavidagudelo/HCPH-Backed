package com.artica.telesalud.tph.service;

import java.util.HashMap;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.dao.PersonDAO;
import com.artica.telesalud.tph.dao.UserDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.model.user.User;

public class PersonService {
	
	private PersonDAO personDAO;
	private UserDAO userDAO;

	public PersonService(String daoClassName, HashMap<String, String> params){
		
		personDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
				daoClassName, params).getPersonDAO();
		userDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
				daoClassName, params).getUserDAO();
		
	}
	
	public Person obtenerPersona(Integer personaId){
		
			return personDAO.findPerson(personaId);
			
	}
	
	public Person obtenerPorUsuario(Integer userId){
		
		Person person = null;
		User user = userDAO.getUser(userId);
				
		if(user!=null && user.getPersonId()!=null){
			
			return obtenerPersona(user.getPersonId());
			
		}

		return person;
		
	}

}
