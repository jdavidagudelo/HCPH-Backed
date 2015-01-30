package com.artica.telesalud.tph.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.PatientIdentifierTypeDAO;
import com.artica.telesalud.tph.model.patient.PatientIdentifierType;

public class HibernatePatientIdentifierTypeDAO extends HibernateDAO implements
PatientIdentifierTypeDAO {

	
	public HibernatePatientIdentifierTypeDAO(String configFile) {
		super(configFile);
	}
	
	
	public PatientIdentifierType delete(PatientIdentifierType patientIdentifierType) {
		
		Transaction tx = null;
		Session session = getNewSession();
		try {
		    tx = session.beginTransaction();
		    
		    if(patientIdentifierType.getDateRetired()==null){
		    	patientIdentifierType.setDateRetired(new Date());
		    	patientIdentifierType.setRetired((short)1);
		    }
		    
			session.update(patientIdentifierType);
			session.flush();
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePatientIdentifierTypeDAO.class);
		}
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
		return patientIdentifierType;
		
	}

	@SuppressWarnings("unchecked")
	
	public List<PatientIdentifierType> getAll() {
		Transaction tx = null;
		Session session = null;
		try {
			
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PatientIdentifierType.class)
								.add(Restrictions.eq("retired", 0))
								.addOrder(Order.asc("name"));
			
			List<PatientIdentifierType> patientIdentifiers = criteria.list();
			tx.commit();			
			session.close();
			return patientIdentifiers;
		}catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			 throw new TelesaludException("Exception!",e,HibernatePatientIdentifierTypeDAO.class);
		}
		finally
		{
			if(session != null)
				session.close();
		}
	}
	
	
	public PatientIdentifierType getPatientIdentifierType(Integer p) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PatientIdentifierType.class).add(Restrictions.eq("patientIdentifierTypeId", p));
		
			PatientIdentifierType patientIdentifierType = (PatientIdentifierType)criteria.uniqueResult();
			tx.commit();
			return patientIdentifierType;
		
		}catch (Exception e) {
			if(tx != null)
			{
				tx.rollback();
			}
			 throw new TelesaludException("Exception!",e,HibernatePatientIdentifierTypeDAO.class);
		}
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
	}
	
	
	public PatientIdentifierType insert(PatientIdentifierType patientIdentifierType) {
		
		Transaction tx = null;
		Session session = getNewSession();
		try {
		    tx = session.beginTransaction();
		    
		    if(patientIdentifierType.getDateCreated()==null)
		    	patientIdentifierType.setDateCreated(new Date());
		    
		    session.save(patientIdentifierType);
		    session.flush();
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePatientIdentifierTypeDAO.class);
		}
		return patientIdentifierType;
		
	}

	
	public PatientIdentifierType newBlankPatientIdentifierType() {
		return new PatientIdentifierType();
	}

	
	public PatientIdentifierType update(PatientIdentifierType patientIdentifierType) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    session.update(patientIdentifierType);
		    session.flush();
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePatientIdentifierTypeDAO.class);
		}
		finally
		{
			if(session != null)
			{
				session.close();
			}
		}
		return patientIdentifierType;
		
	}
	
}
