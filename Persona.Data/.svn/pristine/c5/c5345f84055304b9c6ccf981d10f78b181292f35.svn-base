package com.artica.telesalud.persona.dao.hibernate;

import java.util.HashMap;
import com.artica.telesalud.persona.dao.OperationDAO;
import com.artica.telesalud.persona.dao.PermissionDAO;
import com.artica.telesalud.persona.dao.PersonaDAOFactory;
import com.artica.telesalud.persona.dao.PrivilegeDAO;
import com.artica.telesalud.persona.dao.ResourceDAO;
import com.artica.telesalud.persona.dao.RoleDAO;
import com.artica.telesalud.persona.dao.UserDAO;

public class HibernatePersonaDAOFactory implements PersonaDAOFactory {
	


	
	public void configure(HashMap<String, String> params) {
		
	}

	
	public OperationDAO getOperationDao() {
		return new HibernateOperationDAO();
	}

	
	public UserDAO getUserDao() {
		return new HibernateUserDAO();
	}

	
	public RoleDAO getRoleDao() {
		return new HibernateRoleDAO();
	}

	
	public ResourceDAO getResourceDao() {
		return new HibernateResourceDAO();
	}

	
	public PrivilegeDAO getPrivilegeDao() {
		return new HibernatePrivilegeDAO();
	}

	
	public PermissionDAO getPermissionDao() {
		return new HibernatePermissionDAO();
	}
}
