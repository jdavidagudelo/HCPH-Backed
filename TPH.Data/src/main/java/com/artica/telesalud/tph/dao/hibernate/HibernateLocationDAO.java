package com.artica.telesalud.tph.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.LocationDAO;
import com.artica.telesalud.tph.model.location.Location;

public class HibernateLocationDAO extends HibernateDAO implements LocationDAO {
	
	public HibernateLocationDAO(String configFile) {
		
		super(configFile);
		
	}

	@SuppressWarnings("unchecked")
	
	public List<Location> getAll() {
		
		Session session = null;
		Transaction tx = null;
		List<Location> locations = new ArrayList<Location>();
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Location.class).add(Restrictions.eq("retired",0));
			
			locations = criteria.list();
			tx.commit();
		
		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateLocationDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return locations;
	}
	
	
	public Integer getTotalLocations() {
		
		Integer total = 0;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Location.class);
			total=criteria.list().size();
			tx.commit();
			
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernateLocationDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}		
		return total;
	}

	
	public Location update(Location location) {
			
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    session.update(location);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernateLocationDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return location;	
	}
	
	
	public Location create(Location location) {
			
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    session.save(location);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernateLocationDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return location;	
	}

	
	public Location getLocation(Integer id) {
		
		Session session = null;
		Location location = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Location.class)
			   .add(Restrictions.eq("locationId", id));
	
			location = (Location)criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateLocationDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return location;
	}
}
