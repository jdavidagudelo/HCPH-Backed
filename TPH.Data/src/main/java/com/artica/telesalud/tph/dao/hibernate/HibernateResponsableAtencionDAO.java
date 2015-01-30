package com.artica.telesalud.tph.dao.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.ResponsableAtencionDAO;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.ResponsableAtencion;

public class HibernateResponsableAtencionDAO extends HibernateDAO implements ResponsableAtencionDAO {

	public HibernateResponsableAtencionDAO(String configFile) {
		super(configFile);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<ResponsableAtencion> getAll() {
		
		Session session = null;
		List<ResponsableAtencion> responsablesAtencion = new ArrayList<ResponsableAtencion>();
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ResponsableAtencion.class)
								   .add(Restrictions.eq("voided", (short)0));
		
			responsablesAtencion = criteria.list();
			tx.commit();

		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateResponsableAtencionDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return responsablesAtencion;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<ResponsableAtencion> getAll(Lesionado lesionado) {
		
		Session session = null;
		List<ResponsableAtencion> responsablesAtencion = new ArrayList<ResponsableAtencion>();
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ResponsableAtencion.class)
								   .add(Restrictions.eq("lesionado", lesionado))
								   .add(Restrictions.eq("voided", (short)0));
		
			responsablesAtencion = criteria.list();
			tx.commit();
		
		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateResponsableAtencionDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return responsablesAtencion;
	}

	public ResponsableAtencion get(Integer responsableAtencionId) {
		
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ResponsableAtencion.class)
								   .add(Restrictions.eq("responsableAtencionId", responsableAtencionId))
								   .add(Restrictions.eq("voided", (short)0));
			ResponsableAtencion result = (ResponsableAtencion)criteria.uniqueResult();
			tx.commit();
			return result;
		
		} catch (Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateResponsableAtencionDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}

	public ResponsableAtencion insert(ResponsableAtencion responsableAtencion) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(responsableAtencion.getDateCreated()==null)
		    	responsableAtencion.setDateCreated(new Date());
		    
		    responsableAtencion.setVoided((short)0);
		    responsableAtencion.setUuid(UUID.randomUUID().toString());
		    
		    session.save(responsableAtencion);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernateResponsableAtencionDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return responsableAtencion;
	}

	
	public ResponsableAtencion update(ResponsableAtencion responsableAtencion) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(responsableAtencion.getDateChanged()==null)
		    	responsableAtencion.setDateChanged(new Date());
		    
		    session.update(responsableAtencion);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernateResponsableAtencionDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return responsableAtencion;
	}

	
	public ResponsableAtencion newResponsableAtencion() {
		ResponsableAtencion responsableAtencion = new ResponsableAtencion();
		return responsableAtencion;
	}

	
	public ResponsableAtencion delete(ResponsableAtencion responsableAtencion) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(responsableAtencion.getDateVoided()==null){
		    	responsableAtencion.setDateVoided(new Date());
		    	responsableAtencion.setVoided((short)1);
		    }
		    
			session.update(responsableAtencion);
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernateResponsableAtencionDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return responsableAtencion;
	}
}
