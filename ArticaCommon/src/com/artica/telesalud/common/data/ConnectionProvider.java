package com.artica.telesalud.common.data;

import java.sql.Connection;

public interface ConnectionProvider {
	public Connection getConnection();
}
