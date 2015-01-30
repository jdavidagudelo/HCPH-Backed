package com.artica.telesalud.patientmasterindex.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.patientmasterindex.dao.EhrDAO;
import com.artica.telesalud.patientmasterindex.model.Ehr;

public class HibernateEhrDAO extends HibernateDAO implements EhrDAO {

	@Override
	public Ehr get(String uuid) {
		Session session = getNewSession();
		Ehr ehr = null;
		Transaction t = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Ehr.class).add(
					Restrictions.eq("uuid", uuid));
			ehr = (Ehr) criteria.uniqueResult();
			t.commit();
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, HibernateEhrDAO.class);
		}
		return ehr;
	}
	public HibernateEhrDAO(String configFile) {
		super(configFile);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Ehr> getExternalHistories(String uuid) {
		Session session = getNewSession();
		List<Ehr> ehr = new ArrayList<Ehr>();
		Transaction t = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Ehr.class).add(
					Restrictions.ne("uuid", uuid));
			ehr =  criteria.list();
			t.commit();
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, HibernateEhrDAO.class);
		}
		return ehr;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Ehr> getAll() {
		Session session = getNewSession();
		List<Ehr> ehr = new ArrayList<Ehr>();
		Transaction t = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Ehr.class);
			ehr =  criteria.list();
			t.commit();
		} catch (Exception e) {
			if (t != null)
				t.rollback();
			e.printStackTrace();
			throw new TelesaludException("Exception!", e, HibernateEhrDAO.class);
		}
		return ehr;
	}
}
