package com.artica.telesalud.tph.dao.hibernate;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.UserDAO;
import com.artica.telesalud.tph.model.user.User;

public class HibernateUserDAO extends HibernateDAO implements UserDAO {
	
	public HibernateUserDAO(String configFile) {
		
		super(configFile);
		
	}

	public User getUserByPerson(Integer personId) {
		Session session = null;
		Transaction tx = null;
		User user = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class)
			.add(Restrictions.eq("personId", personId));

			user = (User)criteria.uniqueResult();
			tx.commit();
		
		} catch (Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateUserDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}	
		return user;
	}
	
	
	public User getUser(Integer userId) {
		Session session = null;
		User user = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class)
			.add(Restrictions.eq("userId", userId));

			user = (User)criteria.uniqueResult();
		tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateUserDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}		
		return user;
	}
}
