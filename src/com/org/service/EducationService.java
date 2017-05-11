
package com.org.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.org.beans.EducationBean;
import com.org.dao.EducationDao;
import com.org.dao.EducationDaoImpl;

public class EducationService {

	public EducationService() {
	}

	EducationDao educationDao = new EducationDaoImpl();
	EducationBean educationBean = new EducationBean();

	/**
	 * Create a record for candidate's education by passing education bean as
	 * parameter
	 * 
	 * @param candidateId
	 * @param school
	 * @param grade
	 * @param degree
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws SQLException
	 */
	public boolean addCandidateEducation(EducationBean educationBean) throws NullPointerException, SQLException {
		
		boolean success = educationDao.addCandidateEducation(educationBean);
		return success;
	}

	/**
	 * Retrieve the list of the candidate education records by passing the
	 * candidateId
	 * 
	 * @param candidateId
	 * @return
	 * @throws SQLException
	 */
	public List<EducationBean> getCandidateEducation(Integer candidateId) throws SQLException {
		List<EducationBean> list = educationDao.getCandidateEducation(candidateId);
		return list;
	}

	/**
	 * Check whether any education record of the candidate exists or not
	 * 
	 * @param educationId
	 * @return
	 * @throws SQLException
	 */
	public boolean isCandidateEducationExists(Integer educationId) throws SQLException {
		boolean success = educationDao.isCandidateEducationExists(educationId);
		return success;
	}

	/**
	 * Update the fields of the candidate education by passing the education
	 * bean containing the educationId and candidateId
	 * 
	 * @param candidateId
	 * @param school
	 * @param grade
	 * @param degree
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param educationId
	 * @return
	 * @throws SQLException
	 */
	public boolean updateCandidateEducation(Integer candidateId, String school, String grade, String degree,
			String description, Date startDate, Date endDate, Integer educationId)
					throws NullPointerException, SQLException {
		educationBean.setCandidateId(candidateId);
		educationBean.setSchool(school);
		educationBean.setDegree(degree);
		educationBean.setGrade(grade);
		educationBean.setDescription(description);
		educationBean.setStartDate(startDate);
		educationBean.setEndDate(endDate);
		educationBean.setEducationId(educationId);
		boolean success = educationDao.updateCandidateEducation(educationBean);
		return success;
	}

}
