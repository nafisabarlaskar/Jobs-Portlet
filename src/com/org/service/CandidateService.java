package com.org.service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import com.org.beans.CandidateBean;
import com.org.dao.CandidateDao;
import com.org.dao.CandidateDaoImpl;

public class CandidateService {

	public CandidateService() {
	}

	CandidateDao candidateDao = new CandidateDaoImpl();
	CandidateBean candidateBean = new CandidateBean();

	/**
	 * Retrieve the username of the candidate by validating it with the email
	 * and password
	 * 
	 * @param email
	 * @param password
	 * @return
	 * 
	 * @throws SQLException
	 */
	public String getCandidateUsername(String email, String password) throws SQLException {
		CandidateBean candidateBean = new CandidateBean();
		candidateBean.setEmail(email);
		candidateBean.setPassword(password);
		String username = candidateDao.getCandidateUsername(candidateBean);
		return username;
	}

	/**
	 * Check if the email and username are already in use
	 * 
	 * @param email
	 * @param userName
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean isCandidateExistsByEmail(String email, String userName) throws SQLException {
		boolean success = candidateDao.isCandidateExistsByEmail(email, userName);
		return success;
	}

	/**
	 * Check if the candidate with the given candidateId exists or not
	 * 
	 * @param candidateId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean isCandidateExists(Integer candidateId) throws SQLException {
		boolean success = candidateDao.isCandidateExists(candidateId);
		return success;
	}

	/**
	 * Create a new candidate with the given details
	 * 
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param password
	 * @param email
	 * @param country
	 * @param phone
	 * @return
	 * 
	 * @throws SQLException
	 */
	public String addCandidate(CandidateBean candidateBean) throws SQLException {
		
		String userNames = candidateDao.addCandidate(candidateBean);
		return userNames;
	}

	/**
	 * Retrieve the candidateId with the help of the username
	 * 
	 * @param userName
	 * @return
	 * 
	 * @throws SQLException
	 */
	public Integer getCandidateId(String userName) throws SQLException {
		Integer candidateId = candidateDao.getCandidateId(userName);
		return candidateId;

	}

	/**
	 * Retrieve all the details of the candidate with the given candidateId
	 * 
	 * @param candidateId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public List<CandidateBean> getCandidatesByCandidateId(Integer candidateId) throws SQLException {
		List<CandidateBean> candidates = candidateDao.getCandidatesByCandidateId(candidateId);
		return candidates;
	}

	/**
	 * Retrieve the list of candidates who have applied for the given job
	 * requisition
	 * 
	 * @param jobReqId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public List getAppliedCandidates(Integer jobReqId) throws SQLException {
		List jsonList = candidateDao.getCandidatesByJobReqId(jobReqId);
		return jsonList;
	}

	/**
	 * Update the candidate details by setting the CandidateBean parameters and
	 * passing it as an object
	 * 
	 * @param candidateId
	 * @param firstName
	 * @param lastName
	 * @param country
	 * @param phone
	 * @param resume
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean updateCandidate(CandidateBean candidateBean) throws SQLException {
		
		boolean success = candidateDao.updateCandidate(candidateBean);
		return success;
	}

}
