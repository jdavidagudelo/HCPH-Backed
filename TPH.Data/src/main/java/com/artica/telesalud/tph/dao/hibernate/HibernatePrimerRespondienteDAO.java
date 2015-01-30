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
import com.artica.telesalud.tph.dao.PrimerRespondienteDAO;
import com.artica.telesalud.tph.model.evento.Lesionado;
import com.artica.telesalud.tph.model.evento.PrimerRespondiente;

public class HibernatePrimerRespondienteDAO extends HibernateDAO implements PrimerRespondienteDAO {

	public HibernatePrimerRespondienteDAO(String configFile) {
		super(configFile);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<PrimerRespondiente> getAll() {
		
		Session session = null;
		List<PrimerRespondiente> primerosRespondientes = new ArrayList<PrimerRespondiente>();
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PrimerRespondiente.class)
								   .add(Restrictions.eq("voided", (short)0));
		
			primerosRespondientes = criteria.list();
			tx.commit();
		
		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernatePrimerRespondienteDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return primerosRespondientes;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<PrimerRespondiente> getAll(Lesionado lesionado) {
		
		Session session = null;
		Transaction tx = null;
		List<PrimerRespondiente> primerosRespondientes = new ArrayList<PrimerRespondiente>();
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PrimerRespondiente.class)
								   .add(Restrictions.eq("lesionado", lesionado))
								   .add(Restrictions.eq("voided",(short)0));
		
			primerosRespondientes = criteria.list();
			tx.commit();
		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernatePrimerRespondienteDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return primerosRespondientes;
	}

	
	public PrimerRespondiente insert(PrimerRespondiente primerRespondiente) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(primerRespondiente.getDateCreated()==null)
		    	primerRespondiente.setDateCreated(new Date());
		    
		    primerRespondiente.setVoided((short)0);
		    primerRespondiente.setUuid(UUID.randomUUID().toString());
		    
		    session.save(primerRespondiente);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePrimerRespondienteDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return primerRespondiente;
	}

	
	public PrimerRespondiente update(PrimerRespondiente primerRespondiente) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(primerRespondiente.getDateChanged()==null)
		    	primerRespondiente.setDateChanged(new Date());
		    
		    session.update(primerRespondiente);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePrimerRespondienteDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return primerRespondiente;
	}

	
	public PrimerRespondiente newPrimerRespondiente() {
		PrimerRespondiente primerRespondiente = new PrimerRespondiente();
		return primerRespondiente;
	}

	
	public PrimerRespondiente delete(PrimerRespondiente primerRespondiente) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(primerRespondiente.getDateVoided()==null){
		    	primerRespondiente.setDateVoided(new Date());
		    	primerRespondiente.setVoided((short)1);
		    }
		    
			session.update(primerRespondiente);
		    tx.commit();
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePrimerRespondienteDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return primerRespondiente;
	}

	
	public PrimerRespondiente get(Lesionado lesionado, Integer conceptId) {
		
		Session session = null;
		PrimerRespondiente primerRespondiente = null;
		Transaction tx = null;

		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(PrimerRespondiente.class)
								   .add(Restrictions.eq("lesionado", lesionado))
								   .add(Restrictions.eq("primerRespondiente.conceptId", conceptId))
								   .add(Restrictions.eq("voided",(short)0));
		
			primerRespondiente = (PrimerRespondiente)criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernatePrimerRespondienteDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return primerRespondiente;
	}

}
