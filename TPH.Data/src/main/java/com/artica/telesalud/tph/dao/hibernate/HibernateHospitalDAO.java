package com.artica.telesalud.tph.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.HospitalDAO;
import com.artica.telesalud.tph.model.evento.Hospital;

public class HibernateHospitalDAO extends HibernateDAO implements HospitalDAO {

	public HibernateHospitalDAO(String configFile) {
		super(configFile);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Hospital> getAll() {
		Session session = getNewSession();
		
		Transaction t=session.beginTransaction();
		try {
		Criteria criteria = session.createCriteria(Hospital.class)
								   .add(Restrictions.eq("voided", (short)0))
								   .addOrder(Order.asc("nombre"));

		List<Hospital> hospitales = criteria.list();
		
		t.commit();
		
		return hospitales;
		} catch (Exception e) {
			if(t!=null)
				t.rollback();
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateHospitalDAO.class);
		}
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Hospital> getAllVoided() {
		Session session  = getNewSession();
		
		Transaction t=session.beginTransaction();
		try {
		Criteria criteria = session.createCriteria(Hospital.class);
		
		List<Hospital> hospitales = criteria.list();
		
		t.commit();
		
		return hospitales;
		} catch (Exception e) {
			if(t!=null)
				t.rollback();
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateHospitalDAO.class);
		}
	}
	
	
	public Hospital get(Integer hospitalId) {
		Session session  = getNewSession();
		
		Transaction t=session.beginTransaction();
		try {
		Criteria criteria = session.createCriteria(Hospital.class)
								   .add(Restrictions.eq("hospitalId", hospitalId));
		
		Hospital hospital = (Hospital)criteria.uniqueResult();
		
		t.commit();
		
		return hospital;
		} catch (Exception e) {
			if(t!=null)
				t.rollback();
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateHospitalDAO.class);
		}
	}

	
	public Hospital insert(Hospital hospital) {
		Transaction tx = null;
		Session session  = getNewSession();
		try {
		    tx = session.beginTransaction();
		    
		    if(hospital.getDateCreated()==null)
		    	hospital.setDateCreated(new Date());
		    
		    hospital.setVoided((short)0);
		    hospital.setUuid(UUID.randomUUID().toString());
		    
		    session.save(hospital);
		    session.flush();
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateHospitalDAO.class);
		}
		return hospital;
	}

	
	public Hospital update(Hospital hospital) {
		Transaction tx = null;
		Session session  = getNewSession();
		try {
		    tx = session.beginTransaction();
		    
		    if(hospital.getDateChanged()==null)
		    	hospital.setDateChanged(new Date());
		    
		    session.update(hospital);
		    session.flush();
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
			e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateHospitalDAO.class);
		}
		return hospital;
	}

	
	public Hospital newHospital() {
		Hospital hospital = new Hospital();
		return hospital;
	}

	
	public Hospital delete(Hospital hospital) {
		Transaction tx = null;
		Session session  = getNewSession();
		try {
		    tx = session.beginTransaction();
		    
		    if(hospital.getDateVoided()==null){
		    	hospital.setDateVoided(new Date());
		    	hospital.setVoided((short)1);
		    }
		    
			session.update(hospital);
			session.flush();
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateHospitalDAO.class);
		}
		return hospital;
	}

}
