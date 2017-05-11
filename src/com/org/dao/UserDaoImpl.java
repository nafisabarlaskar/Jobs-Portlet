package com.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.beans.UserBean;
import com.org.model.ConnectionHandle;
import com.org.model.DataSource;

public class UserDaoImpl implements UserDao {

	public UserDaoImpl() {
	}

	ConnectionHandle connectionHandle = new ConnectionHandle();
	DataSource dataSource = new DataSource();

	/*
	 * (non-Javadoc) Retrieve the username of the user by setting the email and
	 * password instance in the userbean object and passing the userbean object
	 * 
	 * @see com.org.dao.UserDao#getUserName(com.org.beans.UserBean)
	 */
	@Override
	public String getUserName(UserBean userBean) throws SQLException {
		String userName = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT USERNAME FROM USERS WHERE EMAIL = ? AND PASSWORD = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setString(1, userBean.getEmail());
				preparedStatement.setString(2, userBean.getPassword());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					userName = resultSet.getString("USERNAME");
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return userName;
	}

	/*
	 * (non-Javadoc) Retrieve the user id of the username by checking it against
	 * the username
	 * 
	 * @see com.org.dao.UserDao#getUserIdByUsername(java.lang.String)
	 */
	@Override
	public Integer getUserIdByUsername(String userName) throws SQLException {
		Integer userId = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT EID FROM USERS WHERE USERNAME = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setString(1, userName.toString());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					userId = resultSet.getInt("EID");
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return userId;
	}

	/*
	 * (non-Javadoc) Retrieve all the details of the user and return it as a
	 * hashmap having the user_id as key and firstname+lastname as value
	 * 
	 * @see com.org.dao.UserDao#getUsers()
	 */
	@Override
	public HashMap<Integer, String> getUsers() throws SQLException {
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		String name = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT EID, FIRSTNAME, LASTNAME FROM USERS";
				preparedStatement = con.prepareStatement(select);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					name = (resultSet.getString("FIRSTNAME")).concat("  " + resultSet.getString("LASTNAME"));
					hashMap.put(resultSet.getInt("EID"), name);
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return hashMap;
	}

	/*
	 * (non-Javadoc) Retrieve those users whose name starts with the initials
	 * typed by the client
	 * 
	 * @see com.org.dao.UserDao#getUsersByName(java.lang.String)
	 */
	@Override
	public List<Map<Integer, String>> getUsersByName(String name) throws SQLException {
		List<Map<Integer, String>> list = new ArrayList<Map<Integer, String>>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSetMetaData resultSetMetaData = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT EID, FIRSTNAME, LASTNAME, USERNAME FROM USERS WHERE FIRSTNAME LIKE '%" + name
						+ "%' OR LASTNAME LIKE '%" + name + "%' OR USERNAME LIKE '%" + name + "%'";
				preparedStatement = con.prepareStatement(select);
				resultSet = preparedStatement.executeQuery();
				resultSetMetaData = resultSet.getMetaData();
				while (resultSet.next()) {
					Map map = new HashMap();
					for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
						String key = resultSetMetaData.getColumnName(i);
						String value = resultSet.getString(key);
						map.put(key, value);
					}
					list.add(map);
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeResultSetMetaData(resultSetMetaData);
			connectionHandle.closeConnection(con);
		}
		return list;
	}

	/*
	 * (non-Javadoc) Check if the email and username is already in use by any
	 * user
	 * 
	 * @see com.org.dao.UserDao#isUserExists(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean isUserExists(String email, String userName) throws SQLException {
		boolean statement = false;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT USERNAME FROM USERS WHERE EMAIL = ? OR USERNAME = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, userName);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next())
					statement = true;
				else
					statement = false;
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return statement;
	}

	/*
	 * (non-Javadoc) Create a new user with all the details being passed as a
	 * UserBean object
	 * 
	 * @see com.org.dao.UserDao#addUser(com.org.beans.UserBean)
	 */
	@Override
	public String addUser(UserBean userBean) throws SQLException {

		String userName = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String insert = "INSERT INTO USERS (EID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, EMAIL, PHONE, COMPANY) VALUES (USRS.nextval, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = con.prepareStatement(insert);
				userName = userBean.getUserName();
				preparedStatement.setString(1, userBean.getUserName());
				preparedStatement.setString(2, userBean.getFirstName());
				preparedStatement.setString(3, userBean.getLastName());
				preparedStatement.setString(4, userBean.getPassword());
				preparedStatement.setString(5, userBean.getEmail());
				preparedStatement.setLong(6, userBean.getPhone());
				preparedStatement.setString(7, userBean.getCompanyName());
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return userName;
	}
}
