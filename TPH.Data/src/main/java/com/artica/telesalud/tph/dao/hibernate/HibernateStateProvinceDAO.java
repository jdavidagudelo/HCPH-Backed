package com.artica.telesalud.tph.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.CountryDAO;
import com.artica.telesalud.tph.dao.StateProvinceDAO;
import com.artica.telesalud.tph.model.location.Country;
import com.artica.telesalud.tph.model.location.StateProvince;

public class HibernateStateProvinceDAO extends HibernateDAO implements StateProvinceDAO {
	private CountryDAO countryDAO;
	public HibernateStateProvinceDAO(String configFile) {
		
		super(configFile);
		countryDAO = new HibernateCountryDAO(configFile);
		
	}

		@SuppressWarnings("unchecked")
		
	public List<StateProvince> getAll() {
		
		Session session = null;
		List<StateProvince> states = new ArrayList<StateProvince>();
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(StateProvince.class);
			criteria.addOrder(Order.asc("name"));

			states = criteria.list();
			tx.commit();
			
		} catch (Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
			throw new TelesaludException("Exception!",e,HibernateStateProvinceDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return states;
	}

		@SuppressWarnings("unchecked")
		
		public StateProvince getStateByName(String countryName, String stateName) {
			Session session = null;
			Transaction tx = null;
			try {
				session = getNewSession();
				tx = session.beginTransaction();
				Country country = countryDAO.getCountryByName(countryName);
				Criteria criteria = session.createCriteria(StateProvince.class);
				criteria.add(Restrictions.ilike("name", stateName));
				
				criteria.add(Restrictions.eq("country", country));
				criteria.addOrder(Order.asc("name"));

				List<StateProvince> states = criteria.list();
				tx.commit();
				if(states.size() > 0)
				{
					return states.get(0);
				}
				return null;
				
			} catch (Exception e) {
				if(tx != null && session.isOpen())
					tx.rollback();
				throw new TelesaludException("Exception!",e,HibernateStateProvinceDAO.class);
			}finally{
				if(session!=null)
					session.close();
			}
		}

}
