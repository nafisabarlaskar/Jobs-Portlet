package com.org.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

import com.org.beans.ApplicationBean;
import com.org.dao.ApplicationDao;
import com.org.dao.ApplicationDaoImpl;
import com.org.beans.JobReqBean;

public class ApplicationService {

	public ApplicationService() {
	}

	ApplicationBean applicationBean = new ApplicationBean();
	ApplicationDao applicationDao = new ApplicationDaoImpl();

	/**
	 * Insert candidate application details in the database
	 * 
	 * @param candidateId
	 * @param phone
	 * @param skills
	 * @param jobReqId
	 * @param inputStream
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean addCandidateApplication(ApplicationBean applicationBean) throws SQLException {
		
		boolean success = applicationDao.addCandidateApplication(applicationBean);
		return success;
	}

	/**
	 * Check if candidate has already applied in the same job requisition
	 * 
	 * @param jobReqId
	 * @param candidateId
	 * @return
	 * @throws SQLException
	 */
	public boolean isApplicationExists(Integer jobReqId, Integer candidateId) throws SQLException {
		boolean success = applicationDao.isApplicationExists(jobReqId, candidateId);
		return success;
	}

	/**
	 * @param applicationId
	 * @return
	 * 
	 * @throws SQLException
	 * @throws IOException
	 */
	public InputStream getResume(int applicationId) throws SQLException, IOException {
		InputStream inputStream = applicationDao.getResume(applicationId);
		return inputStream;
	}

	/**
	 * Get all of the job applications that the candidate has applied to
	 * 
	 * @param candidateId
	 * @return
	 * @throws SQLException
	 */
	public List<JobReqBean> getAppliedJobReqs(Integer candidateId) throws SQLException {
		List<JobReqBean> list = applicationDao.getAppliedJobReq(candidateId);
		return list;
	}

}
