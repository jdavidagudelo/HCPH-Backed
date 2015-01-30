package com.artica.telesalud.tph.message.service.properties;

import java.util.ResourceBundle;

public class HibernateProperties {

	private HibernateProperties()
	{
		
	}
	private static final HibernateProperties INSTANCE = new HibernateProperties();
	public static final ResourceBundle CONFIGURATION_RESOURCES = ResourceBundle
			.getBundle("com.artica.telesalud.tph.message.service.properties.Hibernate");
	public static HibernateProperties getInstance()
	{
		return INSTANCE;
	}
	public String getHibernateTphCfgXml()
	{
		return CONFIGURATION_RESOURCES.getString("hibernate.tph.cfg.xml");
	}
	public String getHibernateFactoryClass()
	{
		return CONFIGURATION_RESOURCES.getString("hibernate.factory.class");
	}
}
