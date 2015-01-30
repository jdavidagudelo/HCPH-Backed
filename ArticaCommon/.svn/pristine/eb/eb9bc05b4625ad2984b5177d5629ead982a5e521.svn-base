package com.artica.telesalud.common.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UniqueConnectionProvider implements ConnectionProvider{
	
	private String connectionString = "",
				   user = "",
				   password = "";
	
	private Connection connection;
	
	
	
	public UniqueConnectionProvider(String connectionString, String user,
			String password) {
		super();
		this.connectionString = connectionString;
		this.user = user;
		this.password = password;
	}

	@Override
	public Connection getConnection() {
		try {
			if( connection == null || connection.isClosed() )
				openNewConnection();			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return connection;
	}
	
	private void openNewConnection() throws SQLException{
		//DriverManager.getDriver("com.mysql.jdbc.Driver");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("--ERROR: DRIVER NOT FOUND");
			e.printStackTrace();
		}
		connection = DriverManager.getConnection(connectionString, user, password);
	}
	
}
