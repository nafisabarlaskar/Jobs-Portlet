package com.org.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.org.beans.CandidateBean;

public interface CandidateDao {
	/**
	 * Retrieve the list of candidates who have applied for the given job requisition
	 * @param job_req_id
	 * @return
	 * @throws SQLException
	 *  
	 */
	public List<Map<String, String>>  getCandidatesByJobReqId(Integer job_req_id) throws SQLException ;
	/**
	 * Retrieve the username of the candidate by validating it with the email and password
	 * @param candidateBean
	 * @return
	 * @throws SQLException
	 * 
	 */
	public String  getCandidateUsername (CandidateBean candidateBean) throws SQLException ;
	/**
	 * Check if the candidate with the given candidate_id exists or not
	 * @param candidate_id
	 * @return
	 * @throws SQLException
	 *  
	 */
	public boolean  isCandidateExists (Integer candidate_id) throws SQLException ;
	/**
	 * Check if the email and username are already in use 
	 * @param email
	 * @param username
	 * @return
	 * @throws SQLException
	 *  
	 */
	public boolean isCandidateExistsByEmail(String email, String username) throws SQLException ;
	/**
	 * Retrieve all the details of the candidate with the given candidate_id
	 * @param candidate_id
	 * @return
	 * @throws SQLException
	 *  
	 */
	public List<CandidateBean> getCandidatesByCandidateId(Integer candidate_id) throws SQLException ;
	/**
	 * Retrieve the candidate_id with the help of the username
	 * @param username
	 * @return
	 * @throws SQLException
	 *  
	 */
	public Integer getCandidateId(String username) throws SQLException ;
	/**
	 * Create a new candidate with the given details
	 * @param candidateBean
	 * @return
	 * @throws SQLException
	 *  
	 */
	public String addCandidate(CandidateBean candidateBean) throws SQLException ;
	/**
	 * Update the candidate details by setting the CandidateBean parameters and passing it as an object
	 * @param candidateBean
	 * @return
	 * @throws SQLException
	 *  
	 */
	public boolean updateCandidate(CandidateBean candidateBean) throws SQLException ;
}
