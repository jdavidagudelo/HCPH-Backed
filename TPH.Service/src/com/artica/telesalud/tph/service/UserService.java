package com.artica.telesalud.tph.service;

import java.util.HashMap;

import com.artica.telesalud.common.service.DAOFactoryInstantiator;
import com.artica.telesalud.tph.dao.UserDAO;
import com.artica.telesalud.tph.dao.hibernate.HibernateTPHDAOFactory;
import com.artica.telesalud.tph.model.user.User;

public class UserService {
	private UserDAO userDAO;
	public UserService(String daoClassName, HashMap<String, String> params)
	{
		userDAO = DAOFactoryInstantiator.instantiateDaoFactory(HibernateTPHDAOFactory.class, 
				daoClassName, params).getUserDAO();
	}
	public User getUser(Integer userId)
	{
		return userDAO.getUser(userId);
	}
	public User getUserByPersonId(Integer personId)
	{
		return userDAO.getUserByPerson(personId);
	}
}
