package com.artica.telesalud.tph.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.EventAddressDAO;
import com.artica.telesalud.tph.model.evento.EventAddress;

public class HibernateEventAddressDAO extends HibernateDAO implements EventAddressDAO {
	public HibernateEventAddressDAO(String configFile) {
		super(configFile);
	}
	
	public EventAddress insert(EventAddress eventAddress) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    
		    session.saveOrUpdate(eventAddress);
		    
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen() && session.isOpen())
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return eventAddress;
	}
}
