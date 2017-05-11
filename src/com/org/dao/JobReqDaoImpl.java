package com.org.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.org.beans.JobReqBean;
import com.org.model.ConnectionHandle;
import com.org.model.DataSource;

public class JobReqDaoImpl implements JobReqDao {

	public JobReqDaoImpl() {
	}

	ConnectionHandle connectionHandle = new ConnectionHandle();
	DataSource dataSource = new DataSource();

	/*
	 * (non-Javadoc) Create a new job requisition by passing JobReqBean object
	 * as a parameter
	 * 
	 * @see com.org.dao.JobReqDao#addJobReq(com.org.beans.JobReqBean)
	 */
	@Override
	public Integer addJobReq(JobReqBean jobReqBean) throws SQLException {
		Integer generatedKey = null;
		Connection con = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String insert = "INSERT INTO JOB_REQUISITION (ID, TITLE, DESCRIPTION, DEPARTMENT, LOCATION, START_DATE, END_DATE, CREATOR_ID) VALUES (SQNC.nextval, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement1 = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
				java.sql.Date startDate = new Date(jobReqBean.getStartDate().getTime());
				java.sql.Date endDate = new Date(jobReqBean.getEndDate().getTime());
				preparedStatement1.setString(1, jobReqBean.getTitle());
				preparedStatement1.setString(2, jobReqBean.getDescription());
				preparedStatement1.setString(3, jobReqBean.getDepartment());
				preparedStatement1.setString(4, jobReqBean.getLocation());
				preparedStatement1.setDate(5, startDate);
				preparedStatement1.setDate(6, endDate);
				preparedStatement1.setInt(7, jobReqBean.getCreatorId());
				preparedStatement1.executeQuery();
				preparedStatement2 = con.prepareStatement("SELECT SQNC.CURRVAL FROM JOB_REQUISITION");
				resultSet = preparedStatement2.executeQuery();
				if (resultSet.next()) {
					generatedKey = (int) resultSet.getLong(1);
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement1);
			connectionHandle.closePreparedStatement(preparedStatement2);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return generatedKey;

	}

	/*
	 * (non-Javadoc) Retrieve the titles of the JobRequsitions with which the
	 * user is associated with different roles
	 * 
	 * @see com.org.dao.JobReqDao#getJobReqTitleByUserId(java.lang.Integer)
	 */
	@Override
	public HashMap<Integer, String> getJobReqTitleByUserId(Integer userId) throws SQLException {
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT DISTINCT ID, TITLE FROM JOB_REQUISITION INNER JOIN ROLES ON JOB_REQUISITION.ID = ROLES.JR_ID WHERE ROLES.USER_ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, userId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
					hashMap.put(resultSet.getInt("ID"), resultSet.getString("TITLE"));
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}

		return hashMap;
	}

	/*
	 * (non-Javadoc) Retrieve the titles of the JobRequsitions with has been
	 * posted before the current date and will expire after the current date
	 * 
	 * @see com.org.dao.JobReqDao#getJobReqTitle()
	 */
	@Override
	public HashMap<Integer, String> getJobReqTitle() throws SQLException {
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		DataSource dataSource = new DataSource();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT ID, TITLE FROM JOB_REQUISITION WHERE START_DATE <= CURRENT_DATE AND END_DATE >= CURRENT_DATE";
				preparedStatement = con.prepareStatement(select);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
					hashMap.put(resultSet.getInt("ID"), resultSet.getString("TITLE"));
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return hashMap;
	}

	/*
	 * (non-Javadoc) Retrieve the title of the JobRequsition with the given job
	 * requisition id
	 * 
	 * @see com.org.dao.JobReqDao#getJobReqTitleByJobReqId(java.lang.Integer)
	 */
	@Override
	public String getJobReqTitleByJobReqId(Integer jobReqId) throws SQLException {
		String jobTitle = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		DataSource dataSource = new DataSource();
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT TITLE FROM JOB_REQUISITION WHERE ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, jobReqId);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
					jobTitle = resultSet.getString("TITLE");
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeResultSet(resultSet);
			connectionHandle.closeConnection(con);
		}
		return jobTitle;
	}

	/*
	 * (non-Javadoc) Retrieve the details of the JobRequsitions with has been
	 * posted before the current date and will expire after the current date
	 * 
	 * @see com.org.dao.JobReqDao#getJobReqs()
	 */
	@Override
	public List<JobReqBean> getJobReqs() throws SQLException {

		List<JobReqBean> list = new ArrayList<JobReqBean>();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT ID,TITLE,DESCRIPTION,LOCATION,DEPARTMENT,START_DATE,END_DATE FROM JOB_REQUISITION WHERE START_DATE <= CURRENT_DATE AND END_DATE >= CURRENT_DATE";
				preparedStatement = con.prepareStatement(select);
				ResultSet resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					JobReqBean jobReqBean = new JobReqBean();
					jobReqBean.setJobReqId(resultSet.getInt("ID"));
					jobReqBean.setTitle(resultSet.getString("TITLE"));
					jobReqBean.setDesciption(resultSet.getString("DESCRIPTION"));
					jobReqBean.setLocation(resultSet.getString("LOCATION"));
					jobReqBean.setDepartment(resultSet.getString("DEPARTMENT"));
					jobReqBean.setStartDate(resultSet.getDate("START_DATE"));
					jobReqBean.setEndDate(resultSet.getDate("END_DATE"));
					list.add(jobReqBean);
				}
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return list;
	}

}
