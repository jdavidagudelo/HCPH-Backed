package com.artica.telesalud.tph.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.CountryDAO;
import com.artica.telesalud.tph.model.location.Country;

public class HibernateCountryDAO  extends HibernateDAO implements CountryDAO {
public HibernateCountryDAO(String configFile) {
		
		super(configFile);
		
	}
	
	public Country getCountryByName(String countryName) {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Country.class);
			criteria.add(Restrictions.ilike("name", countryName));
			
			criteria.addOrder(Order.asc("name"));

			@SuppressWarnings("unchecked")
			List<Country> states = criteria.list();
			if(states.size() > 0)
			{
				return states.get(0);
			}
			tx.commit();
			return null;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateStateProvinceDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}

}
