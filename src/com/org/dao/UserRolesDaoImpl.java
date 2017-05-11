
package com.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.org.model.ConnectionHandle;
import com.org.model.DataSource;

public class UserRolesDaoImpl implements UserRolesDao {

	public UserRolesDaoImpl() {
	}

	ConnectionHandle connectionHandle = new ConnectionHandle();
	DataSource dataSource = new DataSource();

	/*
	 * (non-Javadoc) Add the role_id corresponding to the particular user for a
	 * given job requisition
	 * 
	 * @see com.org.dao.UserRolesDao#addJobReqRoles(java.lang.Integer,
	 * java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public boolean addJobReqRoles(Integer jobReqId, Integer userId, Integer roleId) throws SQLException {
		Connection con = null;
		PreparedStatement preparedStatement = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String insert = "INSERT INTO ROLES (JR_ID, USER_ID, ROLE_ID) VALUES (?, ?, ?)";
				preparedStatement = con.prepareStatement(insert);
				preparedStatement.setInt(1, jobReqId);
				preparedStatement.setInt(2, userId);
				preparedStatement.setInt(3, roleId);
				preparedStatement.executeQuery();
				return true;
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return false;
	}

	/*
	 * (non-Javadoc) Retrieve the role_id and the corresponding user_id for the
	 * given job requisition id
	 * 
	 * @see com.org.dao.UserRolesDao#getJobReqRoles(java.lang.Integer)
	 */
	@Override
	public HashMap<Integer, Integer> getJobReqRoles(Integer jobReqId) throws SQLException {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT USER_ID, ROLE_ID FROM ROLES WHERE ROLES.JR_ID=?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, jobReqId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					hashMap.put(resultSet.getInt("ROLE_ID"), resultSet.getInt("USER_ID"));
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return hashMap;
	}
}
