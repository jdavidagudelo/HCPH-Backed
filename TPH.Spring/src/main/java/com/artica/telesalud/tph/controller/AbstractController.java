package com.artica.telesalud.tph.controller;

import javax.servlet.ServletContext;

public abstract class AbstractController {
	public synchronized void refreshHibernateSession(ServletContext context)
	{
		try
		{
			/*HibernateSessionFactoryManager manager = HibernateSessionFactoryManager
				.getInstance();
		
		manager.destroyFactory();
			manager.createFactory(
					context.getInitParameter(HibernateProperties.HIBERNATE_CONFIGURATION_FILE_NAME));*/
		}
		catch(Exception e)
		{
			
		}
		
	}
}
