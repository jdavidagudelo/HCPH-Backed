package com.artica.telesalud.common.service;

import java.util.HashMap;

import com.artica.telesalud.common.data.DAOFactory;

public class DAOFactoryInstantiator {

	public static <T extends DAOFactory> T instantiateDaoFactory(Class<T> baseInterface, String daoFactoryName, HashMap<String, String> params){
		try {
			T factory = baseInterface.cast(baseInterface.getClassLoader().loadClass(daoFactoryName).newInstance());
			if( params != null)
				factory.configure(params);
			return factory;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
