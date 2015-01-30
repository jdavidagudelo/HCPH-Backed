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
import com.artica.telesalud.tph.dao.PatientIdentifierDAO;
import com.artica.telesalud.tph.model.patient.Patient;
import com.artica.telesalud.tph.model.patient.PatientIdentifier;

public class HibernatePatientIdentifierDAO extends HibernateDAO implements
		PatientIdentifierDAO {

	public HibernatePatientIdentifierDAO(String configFile) {
		super(configFile);
	}

	
	public PatientIdentifier delete(PatientIdentifier patientIdentifier) {
		
		Session session = null;
		Transaction tx = null;
		try {
			
			session = getNewSession();
			tx = session.beginTransaction();
		    if(patientIdentifier.getDateVoided()==null){
		    	patientIdentifier.setDateVoided(new Date());
		    	patientIdentifier.setVoided((short)1);
		    }
		    
			session.update(patientIdentifier);
			tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePatientIdentifierDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return patientIdentifier;
		
	}

	@SuppressWarnings("unchecked")
	
	public List<PatientIdentifier> getAll(PatientIdentifier patientIdentifier) {
		
		Session session = null;
		List<PatientIdentifier> patientIdentifiers = new ArrayList<PatientIdentifier>();
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PatientIdentifier.class).add(Restrictions.eq("voided", (short)0));
			
			patientIdentifiers = criteria.list();
			tx.commit();
		}catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			 throw new TelesaludException("Exception!",e,HibernatePatientIdentifierDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return patientIdentifiers;
		
	}

	
	public PatientIdentifier getPreferredIdentifier(Patient patient) {
		
		Session session = null;
		PatientIdentifier patientIdentifier = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PatientIdentifier.class)
						               .add(Restrictions.eq("patient", patient))
						               .add(Restrictions.eq("preferred", 1));
			
			patientIdentifier = (PatientIdentifier)criteria.uniqueResult();
			tx.commit();
			
		}catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			 throw new TelesaludException("Exception!",e,HibernatePatientIdentifierDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return patientIdentifier;
	}

	
	public PatientIdentifier insert(PatientIdentifier patientIdentifier) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(patientIdentifier.getDateCreated()==null)
		    	patientIdentifier.setDateCreated(new Date());
		    
		    patientIdentifier.setVoided((short)0);
		    
		    session.saveOrUpdate(patientIdentifier);
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePatientIdentifierDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return patientIdentifier;
		
	}

	
	public PatientIdentifier newBlankPatientIdentifier() {
		return new PatientIdentifier();
	}

	
	public PatientIdentifier update(PatientIdentifier patientIdentifier) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    session.update(patientIdentifier);
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePatientIdentifierDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return patientIdentifier;
		
	}

}
