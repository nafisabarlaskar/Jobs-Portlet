package com.org.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.org.beans.EducationBean;
import com.org.model.ConnectionHandle;
import com.org.model.DataSource;

public class EducationDaoImpl implements EducationDao {

	public EducationDaoImpl() {
	}

	ConnectionHandle connectionHandle = new ConnectionHandle();
	DataSource dataSource = new DataSource();

	/*
	 * (non-Javadoc) Create a record for candidate's education by passing
	 * education bean as parameter
	 * 
	 * @see com.org.dao.EducationDao#addCandidateEducation(com.org.beans.
	 * EducationBean)
	 */
	public boolean addCandidateEducation(EducationBean educationBean) throws SQLException {

		PreparedStatement preparedStatement = null;
		Connection con = null;

		try {
			con = dataSource.getConnection();
			if (con != null) {
				java.sql.Date start_date = new Date(educationBean.getStartDate().getTime());
				java.sql.Date end_date = new Date(educationBean.getEndDate().getTime());
				String insert = "INSERT INTO EDUCATION ( ID, CANDIDATE_ID, SCHOOL, DEGREE, GRADE, DESCRIPTION, START_DATE, END_DATE) VALUES (EDU.nextval, ?, ?, ?, ?, ?, ?, ?)";
				preparedStatement = con.prepareStatement(insert);
				preparedStatement.setInt(1, educationBean.getCandidateId());
				preparedStatement.setString(2, educationBean.getSchool());
				preparedStatement.setString(3, educationBean.getDegree());
				preparedStatement.setString(4, educationBean.getGrade());
				preparedStatement.setString(5, educationBean.getDescription().toString());
				preparedStatement.setDate(6, start_date);
				preparedStatement.setDate(7, end_date);
				preparedStatement.executeUpdate();
				return true;
			}
		}

		finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return false;

	}

	/*
	 * (non-Javadoc) Retrieve the list of the candidate education records by
	 * passing the candidate_id
	 * 
	 * @see com.org.dao.EducationDao#getCandidateEducation(java.lang.Integer)
	 */
	@Override
	public List<EducationBean> getCandidateEducation(Integer candidateId) throws SQLException {

		List<EducationBean> list = new ArrayList<EducationBean>();
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT * FROM EDUCATION WHERE CANDIDATE_ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, candidateId.intValue());
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					EducationBean educationBean = new EducationBean();
					educationBean.setCandidateId(resultSet.getInt("CANDIDATE_ID"));
					educationBean.setSchool(resultSet.getString("SCHOOL"));
					educationBean.setGrade(resultSet.getString("GRADE"));
					educationBean.setDegree(resultSet.getString("DEGREE"));
					educationBean.setDescription(resultSet.getString("DESCRIPTION"));
					educationBean.setStartDate(resultSet.getDate("START_DATE"));
					educationBean.setEndDate(resultSet.getDate("END_DATE"));
					educationBean.setEducationId(resultSet.getInt("ID"));
					list.add(educationBean);
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
	 * (non-Javadoc) Check whether any education record of the candidate exists
	 * or not
	 * 
	 * @see
	 * com.org.dao.EducationDao#isCandidateEducationExists(java.lang.Integer)
	 */
	@Override
	public boolean isCandidateEducationExists(Integer educationId) throws SQLException {

		boolean success = false;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		ResultSet resultSet = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				String select = "SELECT SCHOOL FROM EDUCATION WHERE ID = ?";
				preparedStatement = con.prepareStatement(select);
				preparedStatement.setInt(1, educationId.intValue());
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
	 * (non-Javadoc) Update the fields of the candidate education by passing the
	 * education bean containing the education_id and candidate_id
	 * 
	 * @see com.org.dao.EducationDao#updateCandidateEducation(com.org.beans.
	 * EducationBean)
	 */
	@Override
	public boolean updateCandidateEducation(EducationBean educationBean) throws SQLException {
		PreparedStatement preparedStatement = null;
		Connection con = null;
		try {
			con = dataSource.getConnection();
			if (con != null) {
				java.sql.Date startDate = new Date(educationBean.getStartDate().getTime());
				java.sql.Date endDate = new Date(educationBean.getEndDate().getTime());
				String update = "UPDATE EDUCATION SET SCHOOL = ?, DEGREE = ?, GRADE = ?, DESCRIPTION = ?, START_DATE =?, END_DATE = ? WHERE ID = ?";
				preparedStatement = con.prepareStatement(update);
				preparedStatement.setString(1, educationBean.getSchool());
				preparedStatement.setString(2, educationBean.getDegree());
				preparedStatement.setString(3, educationBean.getGrade());
				preparedStatement.setString(4, educationBean.getDescription().toString());
				preparedStatement.setDate(5, startDate);
				preparedStatement.setDate(6, endDate);
				preparedStatement.setInt(7, educationBean.getEducationId());
				preparedStatement.executeUpdate();
				return true;
			}
		} finally {
			connectionHandle.closePreparedStatement(preparedStatement);
			connectionHandle.closeConnection(con);
		}
		return false;
	}

}
