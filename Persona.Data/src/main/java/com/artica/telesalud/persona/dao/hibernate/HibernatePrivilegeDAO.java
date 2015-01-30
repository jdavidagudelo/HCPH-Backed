package com.artica.telesalud.persona.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.persona.dao.PrivilegeDAO;
import com.artica.telesalud.persona.model.impl.PrivilegeDTO;
import com.artica.telesalud.persona.model.impl.ResourceDTO;

public class HibernatePrivilegeDAO  extends HibernateDAO implements PrivilegeDAO,
Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PrivilegeDTO insert(PrivilegeDTO privilege) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(privilege);
			tx.commit();

		} catch (Exception e) {
			if (tx != null && session.isOpen()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return privilege;
	}

	
	public PrivilegeDTO newBlankPrivilege() {
		return new PrivilegeDTO();
	}

	
	public PrivilegeDTO delete(PrivilegeDTO privilege) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.delete(privilege);
			tx.commit();

		} catch (Exception e) {
			if (tx != null && session.isOpen()) {
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return privilege;
	}

	@SuppressWarnings("unchecked")
	
	public int deletePrivilegesFor(ResourceDTO resource) {
		Transaction tx = null;
		
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PrivilegeDTO.class).add(Restrictions.eq("resource",
					resource.getResourceId()));
			for(PrivilegeDTO privilege : (List<PrivilegeDTO>)criteria.list())
			{
				session.delete(privilege);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return 0;
	}
}
