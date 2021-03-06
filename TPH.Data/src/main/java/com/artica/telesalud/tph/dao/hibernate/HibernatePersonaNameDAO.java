package com.artica.telesalud.tph.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.PersonNameDAO;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.model.person.PersonName;

public class HibernatePersonaNameDAO extends HibernateDAO implements
		PersonNameDAO {

	public HibernatePersonaNameDAO(String configFile) {
		super(configFile);
	}

	
	public PersonName delete(PersonName personName) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(personName.getDateVoided()==null){
		    	personName.setDateVoided(new Date());
		    	personName.setVoided((short)1);
		    }
		    
			session.update(personName);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonaNameDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return personName;
		
	}

	@SuppressWarnings("unchecked")
	
	public List<PersonName> getAll() {
		
		Session session = null;
		List<PersonName> personNames = new ArrayList<PersonName>();
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PersonName.class).add(Restrictions.eq("voided", (short)0));
			
			personNames = criteria.list();
		tx.commit();
		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernatePersonaNameDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return personNames;
		
	}

	
	public PersonName insert(PersonName personName) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(personName.getDateCreated()==null)
		    	personName.setDateCreated(new Date());
		    
		    session.saveOrUpdate(personName);
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonaNameDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return personName;
		
	}

	
	public PersonName newBlankPersonName() {
		PersonName pn = new PersonName();
		pn.setVoided((short)0);
		return pn;
	}

	
	public PersonName update(PersonName personName) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(personName.getDateChanged()==null)
		    	personName.setDateChanged(new Date());
		    
		    personName = (PersonName)session.merge(personName);
		    session.update(personName);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonaNameDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return personName;
		
	}

	
	public PersonName getPreferredPersonName(Person person) {
		
		Session session = null;
		PersonName personName = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PersonName.class)
						               .add(Restrictions.eq("person", person))
						               .add(Restrictions.eq("preferred", 1));
			
			personName = (PersonName)criteria.uniqueResult();
			tx.commit();
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonaNameDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return personName;
	}
}
