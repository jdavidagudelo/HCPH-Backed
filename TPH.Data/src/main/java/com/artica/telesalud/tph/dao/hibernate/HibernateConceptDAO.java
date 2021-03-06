package com.artica.telesalud.tph.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.artica.telesalud.common.data.HibernateDAO;
import com.artica.telesalud.common.exception.TelesaludException;
import com.artica.telesalud.tph.dao.ConceptDAO;
import com.artica.telesalud.tph.model.concept.Concept;
import com.artica.telesalud.tph.model.concept.ConceptSet;

public class HibernateConceptDAO extends HibernateDAO implements ConceptDAO {

	public HibernateConceptDAO(String configFile) {
		super(configFile);
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Concept> getAll() {
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Concept.class)
									   .add(Restrictions.eq("retired", (short)0));
			List<Concept> result = criteria.list();
			tx.commit();
			return result;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}

	
	public Concept insert(Concept concept) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(concept.getDateCreated()==null)
		    	concept.setDateCreated(new Date());
		    
		    concept.setRetired((short)0);
		    concept.setUuid(UUID.randomUUID().toString());
		    
		    session.save(concept);
		    
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx!=null)
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return concept;
	}

	
	public Concept update(Concept concept) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(concept.getDateChanged()==null)
		    	concept.setDateChanged(new Date());
		    
		    session.update(concept);
		    session.flush();
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return concept;
	}

	
	public Concept newConcept() {
		Concept concept = new Concept();
		return concept;
	}

	
	public Concept delete(Concept concept) {
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(concept.getDateRetired()==null){
		    	concept.setDateRetired(new Date());
		    	concept.setRetired((short)1);
		    }
		    
			session.update(concept);
			
		    tx.commit();
		    
		    
		} catch(Exception e) {
			if(tx!=null)
				tx.rollback();
		    e.printStackTrace();
		    throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return concept;
	}
	
	
	public List<Concept> getConceptsByClass(Integer intClass){
		
		Session session = null;
		Transaction tx  = null;
		try {
			
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Concept.class)
									   .add(Restrictions.eq("conceptClass.conceptClassId", intClass))
									   .addOrder(Order.asc("description"));
			
			@SuppressWarnings("unchecked")
			List<Concept> concepts = criteria.list();
		tx.commit();
		return concepts;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}

	@SuppressWarnings("unchecked")
	
	public List<Concept> getConceptsByClass(String query, int maxResult, int offset, Integer intClass){
		
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Concept.class)
									   .add(Restrictions.eq("conceptClass.conceptClassId", intClass))
									   .addOrder(Order.asc("description"));
			
			if(maxResult>0)
				criteria.setMaxResults(maxResult);
			
			if(offset>=0)
				criteria.setFirstResult(offset);
			
			Criterion expDescription = Restrictions.ilike("description", "%" + query.replaceAll(" ", "%") + "%");
	        Criterion expShortName = Restrictions.ilike("shortName", "%" + query.replaceAll(" ", "%") + "%");
	        
	        LogicalExpression orExp = Restrictions.or(expDescription, expShortName);
			
			criteria.add(orExp);
			
			List<Concept> concepts = criteria.list();
			tx.commit();
			return concepts;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Concept> getConceptsBySet(String query, int maxResult, int offset, Integer intSet){
		
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Query criteria = session.createQuery("Select c FROM ConceptSet cs inner join cs.concept c WHERE cs.conceptSet.conceptId = :ConceptSet AND c.shortName LIKE :Query ORDER BY c.shortName");
			
			
			
//			Criteria criteria = session.createCriteria(Concept.class)
//									   .add(Restrictions.eq("conceptClass.conceptClassId", intClass))
//									   .addOrder(Order.asc("description"));
			
			if(maxResult>0)
				criteria.setMaxResults(maxResult);
			
			if(offset>=0)
				criteria.setFirstResult(offset);
			
			criteria.setParameter("ConceptSet", intSet);
			criteria.setParameter("Query", "%" + query.replaceAll(" ", "%") + "%");
			
//			Criterion expDescription = Restrictions.ilike("description", "%" + query.replaceAll(" ", "%") + "%");
//	        Criterion expShortName = Restrictions.ilike("shortName", "%" + query.replaceAll(" ", "%") + "%");
//	        
//	        LogicalExpression orExp = Restrictions.or(expDescription, expShortName);
//			
//			criteria.add(orExp);
			
			List<Concept> concepts = criteria.list();
			tx.commit();
			return concepts;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	
	public List<Concept> getConceptsBySetActive(String query, int maxResult, int offset, Integer intSet){
		
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Query criteria = session.createQuery("Select c FROM ConceptSet cs inner join cs.concept c WHERE c.retired != 1 AND cs.conceptSet.conceptId = :ConceptSet AND c.shortName LIKE :Query ORDER BY c.shortName");
			
			if(maxResult>0)
				criteria.setMaxResults(maxResult);
			
			if(offset>=0)
				criteria.setFirstResult(offset);
			
			criteria.setParameter("ConceptSet", intSet);
			criteria.setParameter("Query", "%" + query.replaceAll(" ", "%") + "%");
			
			List<Concept> concepts = criteria.list();
		tx.commit();
			return concepts;
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	
	public Long getTotalConceptsByClass(String query, Integer intClass) {
		
		Long total;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Query criteria = session.createQuery("Select count(c) FROM Concept c WHERE c.conceptClass.conceptClassId=:ConceptClass AND c.description LIKE :Query");
		
			criteria.setParameter("Query", "%" + query.replaceAll(" ", "%") + "%");
			criteria.setParameter("ConceptClass", intClass);
			
			total=(Long)criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return total;
	}
	
	
	public Long getTotalConceptsBySet(String query, Integer intSet) {
		
		Long total;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Query criteria = session.createQuery("Select count(c) FROM ConceptSet cs inner join cs.concept c WHERE cs.conceptSet.conceptId = :ConceptSet AND c.shortName LIKE :Query");
		
			criteria.setParameter("Query", "%" + query.replaceAll(" ", "%") + "%");
			criteria.setParameter("ConceptClass", intSet);
			
			total=(Long)criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return total;
	}
	
	
	public Concept getConcept(Integer concept) {
		
		Concept concepto = null;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Concept.class)
			   .add(Restrictions.eq("conceptId", concept));

			concepto = (Concept)criteria.uniqueResult();
		tx.commit();
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}		
		return concepto;
	}
	
	
	public List<Concept> getFindings(String query, int maxResult, int offset) {
		return getConceptsByClass(query, maxResult, offset, 5);
	}
	
	
	public Long getTotalFindings(String query) {
		return getTotalConceptsByClass(query, 5);
	}
	
	
	public List<Concept> getProcedures(String query, int maxResult, int offset) {
		return getConceptsByClass(query, maxResult, offset, 2);
	}
	
	
	public Long getTotalProcedures(String query) {
		return getTotalConceptsByClass(query, 2);
	}
	
	
	public List<Concept> getDrugs(String query, int maxResult, int offset) {
		return getConceptsByClass(query, maxResult, offset, 3);
	}
	
	
	public Long getTotalDrugs(String query) {
		return getTotalConceptsByClass(query, 3);
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	
	public List<Concept> getSet(Integer conceptSet) {
		
		List<ConceptSet> listConceptSets;
		List<Concept> concepts;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Query criteria = session.createQuery("Select c FROM ConceptSet cs inner join cs.concept c WHERE cs.conceptSet.conceptId = :ConceptSet ORDER BY c.shortName");
			
			criteria.setParameter("ConceptSet", conceptSet);
			
			concepts = criteria.list();
			
			//			Criteria criteria = session.createCriteria(ConceptSet.class);
//			criteria.addOrder(Order.asc("sortWeight"));
//			criteria.add(Restrictions.eq("conceptSet.conceptId",conceptSet));
//			
//			listConceptSets=criteria.list();
//			concepts=new ArrayList<Concept>();
//			for(ConceptSet conSet: listConceptSets)
//				concepts.add(conSet.getConcept());
			tx.commit();
		
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return concepts;
	}

	@SuppressWarnings("unchecked")
	
	public List<Concept> getSetActive(Integer conceptSet) {
		
		List<Concept> concepts;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Query criteria = session.createQuery("Select c FROM ConceptSet cs inner join cs.concept c WHERE c.retired != 1 AND cs.conceptSet.conceptId = :ConceptSet ORDER BY c.shortName");
			
			criteria.setParameter("ConceptSet", conceptSet);
			
			concepts = criteria.list();
			
			
			//			Criteria criteria = session.createCriteria(ConceptSet.class);
//			criteria.addOrder(Order.asc("sortWeight"));
//			criteria.add(Restrictions.eq("conceptSet.conceptId",conceptSet));
//			
//			listConceptSets=criteria.list();
//			concepts=new ArrayList<Concept>();
//			for(ConceptSet conSet: listConceptSets)
//				concepts.add(conSet.getConcept());
			tx.commit();
		
		} catch (Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
			e.printStackTrace();
			throw new TelesaludException("Exception!",e,HibernateConceptDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return concepts;
	}
}
