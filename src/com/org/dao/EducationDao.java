package com.org.dao;

import java.sql.SQLException;
import java.util.List;

import com.org.beans.EducationBean;

public interface EducationDao {
	
	/**
	 * Create a record for candidate's education by passing education bean as parameter
	 * @param educationBean
	 * @return
	 * @throws SQLException
	 */
	public boolean addCandidateEducation(EducationBean educationBean) throws SQLException;
	/**
	 * Retrieve the list of the candidate education records by passing the candidate_id
	 * @param candidate_id
	 * @return
	 * @throws SQLException
	 
	 */
	public List<EducationBean> getCandidateEducation(Integer candidateId) throws SQLException;
	/**
	 * Check whether any education record of the candidate exists or not
	 * @param education_id
	 * @return
	 * @throws SQLException
	 
	 */
	public boolean isCandidateEducationExists(Integer education_id) throws SQLException;
	/**
	 * Update the fields of the candidate education by passing the education bean containing the education_id and candidate_id
	 * @param educationBean
	 * @return
	 * @throws SQLException
	 
	 */
	public boolean updateCandidateEducation(EducationBean educationBean) throws SQLException;

}
