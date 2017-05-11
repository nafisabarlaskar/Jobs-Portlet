package com.org.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ConnectionHandle {

	public ConnectionHandle() {
	}

	public void closeConnection(Connection con) throws SQLException {
		try {
			con.close();
		} catch (SQLException e) {
			throw new SQLException("Cannot close database connection");
		}
	}

	public void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			throw new SQLException("Cannot close sql connection");
		}
	}

	public void closeResultSet(ResultSet resultSet) throws SQLException {
		try {
			resultSet.close();
		} catch (SQLException e) {
			throw new SQLException("Cannot close sql connection");
		}
	}

	public void closeResultSetMetaData(ResultSetMetaData resultSetMetaData) throws SQLException {
		try {
			((Connection) resultSetMetaData).close();
		} catch (SQLException e) {
			throw new SQLException("Cannot close sql connection");
		}
	}

}
