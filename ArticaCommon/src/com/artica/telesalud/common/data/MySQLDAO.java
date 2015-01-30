package com.artica.telesalud.common.data;

import java.sql.Connection;

public class MySQLDAO {
	private ConnectionProvider provider;
	
	public MySQLDAO(ConnectionProvider provider){
		this.provider = provider;
	}
	
	public Connection getConnection() {
		return provider.getConnection();
	}
	
}
