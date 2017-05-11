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

import com.org.beans.CandidateBean;
import com.org.model.ConnectionHandle;
import com.org.model.DataSource;

public class CandidateDaoImpl implements CandidateDao {

	public CandidateDaoImpl() {
	}

	ConnectionHandle connectionHandle = new ConnectionHandle();
	DataSource dataSource = new DataSource();

	/*
	 * (non-Javadoc) Create a new candidate with the given details
	 * 
	 * @see com.org.dao.CandidateDao#addCandidate(com.org.beans.CandidateBean)
	 */
	@Override
	public String addCandidate(CandidateBean candidateBean) throws SQLException {
		String userName = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String insert = "INSERT INTO CANDIDATE (ID, USERNAME, FIRSTNAME, LASTNAME, PASSWORD, EMAIL, PHONE, COUNTRY) VALUES (TRP.nextval, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = con.prepareStatement(insert);
				userName = candidateBean.getUserName();
				preparedStatement.setString(1, candidateBean.getUserName());
				preparedStatement.setString(2, candidateBean.getFirstName());
				preparedStatement.setString(3, candidateBean.getLastName());
				preparedStatement.setString(4, candidateBean.getPassword());
				preparedStatement.setString(5, candidateBean.getEmail());
				preparedStatement.setLong(6, candidateBean.getPhone());
				preparedStatement.setString(7, candidateBean.getCountry());
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return userName;
	}

	/*
	 * (non-Javadoc) Retrieve the list of candidates who have applied for the
	 * given job requisition
	 * 
	 * @see com.org.dao.CandidateDao#getCandidatesByJobReqId(java.lang.Integer)
	 */
	@Override
	public List<Map<String, String>> getCandidatesByJobReqId(Integer jobReqId) throws SQLException {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT CA.SKILLS AS SKILLS, CA.CANDIDATE_ID AS CANDIDATE_ID, CA.PHONE, CD.ID, CD.FIRSTNAME, CD.LASTNAME, CD.COUNTRY, CD.EMAIL FROM CANDIDATE_APPLICATION CA INNER JOIN CANDIDATE CD ON CA.CANDIDATE_ID=CD.ID WHERE CA.JR_ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, jobReqId);
				resultSet = preparedStatement.executeQuery();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

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
			connectionHandle.closeConnection(con);
		}

		return list;
	}

	/*
	 * (non-Javadoc) Retrieve the username of the candidate by validating it
	 * with the email and password
	 * 
	 * @see com.org.dao.CandidateDao#getCandidateUsername(com.org.beans.
	 * CandidateBean)
	 */
	@Override
	public String getCandidateUsername(CandidateBean candidateBean) throws SQLException {
		String userName = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT USERNAME FROM CANDIDATE WHERE EMAIL = ? AND PASSWORD = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setString(1, candidateBean.getEmail());
				preparedStatement.setString(2, candidateBean.getPassword());
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
	 * (non-Javadoc) Check if the email and username are already in use
	 * 
	 * @see com.org.dao.CandidateDao#isCandidateExistsByEmail(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public boolean isCandidateExistsByEmail(String email, String userName) throws SQLException {

		PreparedStatement preparedStatement = null;
		Connection con = null;
		boolean success = false;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT ID FROM CANDIDATE WHERE EMAIL = ? OR USERNAME = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setString(1, email);
				preparedStatement.setString(2, userName);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next())
					success = true;
				else
					success = false;
			}
		} finally {
			connectionHandle.closeConnection(con);
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
		}
		return success;
	}

	/*
	 * (non-Javadoc) Retrieve all the details of the candidate with the given
	 * candidate_id
	 * 
	 * @see
	 * com.org.dao.CandidateDao#getCandidatesByCandidateId(java.lang.Integer)
	 */
	@Override
	public List<CandidateBean> getCandidatesByCandidateId(Integer candidateId) throws SQLException {
		List<CandidateBean> list = new ArrayList<CandidateBean>();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT USERNAME, FIRSTNAME, LASTNAME, EMAIL, COUNTRY, PHONE FROM CANDIDATE WHERE ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, candidateId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					CandidateBean candidateBean = new CandidateBean();
					candidateBean.setUserName(resultSet.getString("USERNAME"));
					candidateBean.setFirstName(resultSet.getString("FIRSTNAME"));
					candidateBean.setLastName(resultSet.getString("LASTNAME"));
					candidateBean.setEmail(resultSet.getString("EMAIL"));
					candidateBean.setCountry(resultSet.getString("COUNTRY"));
					candidateBean.setPhone(resultSet.getLong("PHONE"));
					list.add(candidateBean);
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return list;
	}

	/*
	 * (non-Javadoc) Retrieve the candidate_id with the help of the username
	 * 
	 * @see com.org.dao.CandidateDao#getCandidateId(java.lang.String)
	 */
	@Override
	public Integer getCandidateId(String userName) throws SQLException {
		Integer candidateId = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		DataSource dataSource = new DataSource();
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT ID FROM CANDIDATE WHERE USERNAME = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setString(1, userName.toString());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					candidateId = resultSet.getInt("ID");
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return candidateId;
	}

	/*
	 * (non-Javadoc) Check if the candidate with the given candidate_id exists
	 * or not
	 * 
	 * @see com.org.dao.CandidateDao#isCandidateExists(java.lang.Integer)
	 */
	@Override
	public boolean isCandidateExists(Integer candidateId) throws SQLException {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT EMAIL FROM CANDIDATE WHERE ID = ? ";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, candidateId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next())
					success = true;
				else
					success = false;
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return success;
	}

	/*
	 * (non-Javadoc) Update the candidate details by setting the CandidateBean
	 * parameters and passing it as an object
	 * 
	 * @see
	 * com.org.dao.CandidateDao#updateCandidate(com.org.beans.CandidateBean)
	 */
	@Override
	public boolean updateCandidate(CandidateBean candidateBean) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String update = "UPDATE CANDIDATE SET FIRSTNAME = ?, LASTNAME = ?, PHONE =?, COUNTRY = ?, RESUME = ? WHERE ID = ?";
				preparedStatement = con.prepareStatement(update);
				preparedStatement.setString(1, candidateBean.getFirstName());
				preparedStatement.setString(2, candidateBean.getLastName());
				preparedStatement.setLong(3, candidateBean.getPhone());
				preparedStatement.setString(4, candidateBean.getCountry());
				preparedStatement.setBlob(5, candidateBean.getInputStream());
				preparedStatement.setInt(6, candidateBean.getCandidateId());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				return true;
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return false;
	}
}
