package com.org.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.beans.ApplicationBean;
import com.org.beans.JobReqBean;
import com.org.model.ConnectionHandle;
import com.org.model.DataSource;

public class ApplicationDaoImpl implements ApplicationDao {

	public ApplicationDaoImpl() {
	}

	ConnectionHandle connectionHandle = new ConnectionHandle();
	DataSource dataSource = new DataSource();

	/*
	 * (non-Javadoc) Inserts the candidate details into the
	 * CANDIDATE_APPLICATION table
	 * 
	 * @see com.org.dao.ApplicationDao#addCandidateApplication(com.org.beans.
	 * ApplicationBean)
	 */
	@Override
	public boolean addCandidateApplication(ApplicationBean applicationBean) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = null;
		DataSource dataSource = new DataSource();
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String insert = "INSERT INTO CANDIDATE_APPLICATION ( ID, JR_ID, CANDIDATE_ID, PHONE, SKILLS, RESUME) VALUES (CA.nextval, ?, ?, ?, ?, ?)";
				preparedStatement = con.prepareStatement(insert);
				preparedStatement.setInt(1, applicationBean.getJobReqId());
				preparedStatement.setInt(2, applicationBean.getCandidateId());
				preparedStatement.setLong(3, applicationBean.getPhone());
				preparedStatement.setString(4, applicationBean.getSkills());
				preparedStatement.setBlob(5, applicationBean.getinputStream());
				preparedStatement.executeUpdate();
				return true;
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return false;
	}

	/*
	 * (non-Javadoc) Checks if the user has applied to the same job requisition
	 * 
	 * @see com.org.dao.ApplicationDao#isApplicationExists(java.lang.Integer,
	 * java.lang.Integer)
	 */
	@Override
	public boolean isApplicationExists(Integer jobReqId, Integer candidateId) throws SQLException {
		boolean success = false;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		DataSource dataSource = new DataSource();
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT ID FROM CANDIDATE_APPLICATION WHERE JR_ID = ? AND CANDIDATE_ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, jobReqId);
				preparedStatement.setInt(2, candidateId);
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
	 * (non-Javadoc) Retrieves the resume from the candidate's application
	 * 
	 * @see com.org.dao.ApplicationDao#getResume(java.lang.Integer)
	 */
	@Override
	public InputStream getResume(Integer applicationId) throws SQLException, IOException {
		DataSource datasource = new DataSource();
		InputStream inputStream = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = datasource.getConnection();
			if (con != null) {
				String select = "SELECT RESUME FROM CANDIDATE_APPLICATION WHERE ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, applicationId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					inputStream = resultSet.getBinaryStream("resume");
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return inputStream;
	}

	/*
	 * (non-Javadoc) Retrieves the job applications which the candidate has
	 * applied to
	 * 
	 * @see com.org.dao.ApplicationDao#getAppliedJobReq(java.lang.Integer)
	 */
	@Override
	public List<JobReqBean> getAppliedJobReq(Integer candidateId) throws SQLException {
		List<JobReqBean> list = new ArrayList<JobReqBean>();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT JR.TITLE, JR.ID, JR.DEPARTMENT, JR.LOCATION FROM JOB_REQUISITION JR INNER JOIN CANDIDATE_APPLICATION CA ON CA.JR_ID=JR.ID WHERE CANDIDATE_ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, candidateId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					JobReqBean jobReqBean = new JobReqBean();
					jobReqBean.setJobReqId(resultSet.getInt("ID"));
					jobReqBean.setTitle(resultSet.getString("TITLE"));
					jobReqBean.setLocation(resultSet.getString("LOCATION"));
					jobReqBean.setDepartment(resultSet.getString("DEPARTMENT"));
					list.add(jobReqBean);
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return list;

	}

}
