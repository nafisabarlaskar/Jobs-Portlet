package com.org.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	Connection con = null;

	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
			if (con == null)
				throw new SQLException("Failed to get connection");
			else
				return con;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
