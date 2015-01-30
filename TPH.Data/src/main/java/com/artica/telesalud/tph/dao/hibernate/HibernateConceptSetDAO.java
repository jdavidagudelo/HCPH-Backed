package com.artica.telesalud.tph.dao.hibernate;

import java.util.Date;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.ConceptSetDAO;
import com.artica.telesalud.tph.model.concept.ConceptSet;

public class HibernateConceptSetDAO extends HibernateDAO implements ConceptSetDAO {

	public HibernateConceptSetDAO(String configFile) {
		super(configFile);
	}
	
	
	public ConceptSet insert(ConceptSet conceptSet) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(conceptSet.getDateCreated()==null)
		    	conceptSet.setDateCreated(new Date());
		    conceptSet.setUuid(UUID.randomUUID().toString());
		    session.save(conceptSet);    
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateConceptSetDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return conceptSet;
	}

}
