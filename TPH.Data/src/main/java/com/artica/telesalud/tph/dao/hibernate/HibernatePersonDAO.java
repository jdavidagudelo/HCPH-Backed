package com.artica.telesalud.tph.dao.hibernate;

import java.util.ArrayList;
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
import com.artica.telesalud.tph.dao.PersonDAO;
import com.artica.telesalud.tph.model.person.Person;
import com.artica.telesalud.tph.model.person.PersonAddress;
import com.artica.telesalud.tph.model.person.PersonName;

public class HibernatePersonDAO extends HibernateDAO implements PersonDAO {
	
	private String configFile;

	public HibernatePersonDAO(String configFile) {
		
		super(configFile);
		this.configFile=configFile;
		
	}

	
	public Person delete(Person person) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(person.getDateVoided()==null){
		    	person.setDateVoided(new Date());
		    	person.setVoided((short)1);
		    }
		    
			session.update(person);
		    tx.commit();
		} catch(Exception e) {
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return person;
		
	}

	@SuppressWarnings("unchecked")
	
	public List<Person> getAll() {
		List<Person> persons = null;
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class).add(Restrictions.eq("voided", (short)0));
			
			persons = criteria.list();
			
			HibernatePersonaNameDAO daoName=new HibernatePersonaNameDAO(configFile);
			HibernatePersonaAddressDAO daoAddress=new HibernatePersonaAddressDAO(configFile);
			for(Person person : persons){
				
				PersonName personName = daoName.getPreferredPersonName(person);
				
				if(personName==null){
					personName=new PersonName();
				}
				
				person.setPreferredName(personName);
				
				PersonAddress personAddress = daoAddress.getPreferredPersonAddress(person);
				
				if(personAddress==null)
					personAddress=daoAddress.newBlankPersonAddress();
				
				person.setPreferredAddress(personAddress);
				
			}
			tx.commit();
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return persons;
	}

	
	public Person insert(Person person) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(person.getDateCreated()==null)
		    	person.setDateCreated(new Date());
		    
		    session.saveOrUpdate(person);
		    tx.commit();
		    
		    
		    if(person.getPreferredName()!=null){
			    HibernatePersonaNameDAO daoName=new HibernatePersonaNameDAO(configFile);
			    person.getPreferredName().setPerson(person);
			    daoName.insert(person.getPreferredName());
		    }
		    
		    if(person.getPreferredAddress()!=null){
			    HibernatePersonaAddressDAO daoAddress=new HibernatePersonaAddressDAO(configFile);
			    person.getPreferredAddress().setPerson(person);
			    daoAddress.insert(person.getPreferredAddress());
		    }
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return person;
		
	}

	
	public Person newBlankPerson() {
		
		Person person = new Person();
		person.setPreferredName(null);
		person.setPreferredAddress(null);
		person.setVoided((short)0);
		person.setUuid(UUID.randomUUID().toString());
		return person;
		
	}

	
	public Person update(Person person) {
		
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
		    tx = session.beginTransaction();
		    
		    if(person.getDateChanged()==null)
		    	person.setDateChanged(new Date());

		    person = (Person)session.merge(person);
		    session.update(person);
		    tx.commit();
		    
		} catch(Exception e) {
			if(tx != null && session.isOpen())
			{
				tx.rollback();
			}
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		return person;
		
	}
	
	
	public Person findPerson(Integer identifier) throws TelesaludException{
		
		Person person = null;
		Session session = null;
		Transaction tx = null;
		try{
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class)
							    .add(Restrictions.eq("personId", identifier));
			
			person = (Person)criteria.uniqueResult();
			
			HibernatePersonaNameDAO daoName=new HibernatePersonaNameDAO(configFile);
			HibernatePersonaAddressDAO daoAddress=new HibernatePersonaAddressDAO(configFile);
				
			PersonName personName = daoName.getPreferredPersonName(person);

			if(personName==null){
				personName=new PersonName();
			}
			person.setPreferredName(personName);
			
			PersonAddress personAddress = daoAddress.getPreferredPersonAddress(person);
			
			if(personAddress==null)
				personAddress=daoAddress.newBlankPersonAddress();
			
			person.setPreferredAddress(personAddress);
			tx.commit();
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}		
		return person;
	}
	
	@SuppressWarnings("unchecked")
	
	public Person findPersonByRegister(String registro) throws TelesaludException{
		
		Person person = null;
		Session session = null;
		Transaction tx = null;
		try{
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class)
							    .add(Restrictions.eq("registro", registro));
			List<Person> persons = criteria.list();
			if(persons.size()>0){
			
				person = persons.get(0);
				
				HibernatePersonaNameDAO daoName=new HibernatePersonaNameDAO(configFile);
				HibernatePersonaAddressDAO daoAddress=new HibernatePersonaAddressDAO(configFile);
					
				PersonName personName = daoName.getPreferredPersonName(person);
	
				if(personName==null){
					personName=new PersonName();
				}
				person.setPreferredName(personName);
				
				PersonAddress personAddress = daoAddress.getPreferredPersonAddress(person);
				
				if(personAddress==null)
					personAddress=daoAddress.newBlankPersonAddress();
				
				person.setPreferredAddress(personAddress);
			}
			tx.commit();
			
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}		
		return person;
		
	}

	@SuppressWarnings("unchecked")
	
	public List<Person> getPersons(String criterio, Integer limit, Integer offset) {
		
		List<Person> persons = new ArrayList<Person>();
		Transaction tx = null;
		Session session = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class)
									   .addOrder(Order.asc("dateCreated"));
			
			criteria.setMaxResults(limit);
			criteria.setFirstResult(offset);
	
			persons = criteria.list();
			
			HibernatePersonaNameDAO daoName=new HibernatePersonaNameDAO(configFile);
			HibernatePersonaAddressDAO daoAddress=new HibernatePersonaAddressDAO(configFile);
			for(Person person : persons){
				
				PersonName personName = daoName.getPreferredPersonName(person);
				
				if(personName==null){
					personName=new PersonName();
				}
				
				person.setPreferredName(personName);
				
				PersonAddress personAddress = daoAddress.getPreferredPersonAddress(person);
				
				if(personAddress==null)
					personAddress=daoAddress.newBlankPersonAddress();
				
				person.setPreferredAddress(personAddress);
				
			}
			tx.commit();
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
	
		return persons;
	}
	
	
	public Integer getTotalPersons() {
		
		Integer total = 0;
		Session session = null;
		Transaction tx = null;
		try {
			session = getNewSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Person.class);
			total=criteria.list().size();
			tx.commit();
		} catch(Exception e) {

			if(tx != null && session.isOpen())
				tx.rollback();
		    throw new TelesaludException("Exception!",e,HibernatePersonDAO.class);
		}finally{
			if(session!=null)
				session.close();
		}
		
		return total;
	}
}
