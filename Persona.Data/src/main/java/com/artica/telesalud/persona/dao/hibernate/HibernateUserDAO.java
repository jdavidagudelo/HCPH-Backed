package com.artica.telesalud.persona.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.DataConstraintViolation;
import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.persona.dao.UserDAO;
import com.artica.telesalud.persona.model.impl.UserDTO;

public class HibernateUserDAO extends HibernateDAO implements UserDAO,
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public List<UserDTO> getAll() {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class);

			@SuppressWarnings("unchecked")
			List<UserDTO> users = criteria.list();
			tx.commit();
			return users;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
	}

	
	public UserDTO insert(UserDTO user) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.save(user);
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
		return user;
	}

	
	public UserDTO update(UserDTO user) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.update(user);
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
		return user;
	}

	
	public UserDTO newBlankUser() {
		UserDTO user = new UserDTO();
		return user;
	}

	
	public UserDTO delete(UserDTO user) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			session.delete(user);
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
		return user;
	}

	
	public List<DataConstraintViolation> validate(UserDTO user) {
		return new ArrayList<DataConstraintViolation>(0);
	}

	
	public UserDTO findUserByName(String username) {
		Session session = null;
		UserDTO user = null;
		Transaction tx = null;
		 try{
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class).add(
					Restrictions.eq("username", username));
			user = (UserDTO) criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return user;

	}

	
	public UserDTO findUserById(Integer id) {

		Session session = null;
		UserDTO user = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class).add(
					Restrictions.eq("userId", id));
			user = (UserDTO) criteria.uniqueResult();
			tx.commit();

		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	
	public List<UserDTO> getUsers(int limit, int offset) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class)
					.setFirstResult(offset).setMaxResults(limit);
			List<UserDTO> result = criteria.list();
			tx.commit();
			return result;

		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	
	public int count() {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class);
			int count = criteria.list().size();
			tx.commit();
			return count;

		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
	}

	
	public UserDTO findUserByPersonId(Integer personId) {
		Session session = null;
		UserDTO user = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(UserDTO.class).add(
					Restrictions.eq("personId", personId));

			user = (UserDTO) criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!", e,
					HibernateUserDAO.class);
		} finally {
			if (session != null)
				session.close();
		}
		return user;
	}

}
