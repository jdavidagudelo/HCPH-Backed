package com.artica.telesalud.common.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryManager {

	private static HibernateSessionFactoryManager instance;
	private Session session;
	private SessionFactory factory;
	//private String configFile;
	
	public static HibernateSessionFactoryManager getInstance(){
		if( instance == null )
			instance = new HibernateSessionFactoryManager();
		return instance;
	}
	
	protected HibernateSessionFactoryManager(){
		
	}
	
	public void createFactory(String configFile){
		factory = new Configuration().configure(configFile).buildSessionFactory();
	}
	
	public void destroyFactory(){
		if( factory != null )
			factory.close();
	}
	
	public SessionFactory getFactory(){
		return factory;
	}
	
	private void buildSession(){
		try {
//			SessionFactory factory = HibernateSessionFactoryManager.getInstance().getFactory();
			if( session == null || !session.isOpen())
				session = factory.openSession();
			else
				session = factory.getCurrentSession();
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public Session getNewSession(){
		try {
			
			Session sesion = factory.openSession();
			return sesion;
		}
		catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public Session getSession(){
		if( session == null || !session.isOpen())
			buildSession();

		return session;
	}
}
