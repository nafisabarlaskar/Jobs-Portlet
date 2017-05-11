package com.org.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.org.beans.JobReqBean;
import com.org.dao.JobReqDao;
import com.org.dao.JobReqDaoImpl;

public class JobReqService {

	public JobReqService() {

	}

	JobReqDao jobReqDao = new JobReqDaoImpl();
	JobReqBean jobReqBean = new JobReqBean();

	/**
	 * Create a new job requisition by passing JobReqBean object as a parameter
	 * 
	 * @param title
	 * @param description
	 * @param location
	 * @param department
	 * @param startDate
	 * @param endDate
	 * @param creator_id
	 * @return
	 * 
	 * @throws SQLException
	 */
	public Integer addJobReq(JobReqBean jobReqBean) throws SQLException {
		Integer jobReqId = jobReqDao.addJobReq(jobReqBean);
		return jobReqId;
	}

	/**
	 * Retrieve the titles of the JobRequsitions with which the user is
	 * associated with different roles
	 * 
	 * @param userId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getJobTitleByUserId(Integer userId) throws SQLException {
		HashMap<Integer, String> hashMap = jobReqDao.getJobReqTitleByUserId(userId);
		return hashMap;
	}

	/**
	 * Retrieve the titles of the JobRequsitions with has been posted before the
	 * current date and will expire after the current date
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getJobTitle() throws SQLException {
		HashMap<Integer, String> hashMap = jobReqDao.getJobReqTitle();
		return hashMap;
	}

	/**
	 * Retrieve the details of the JobRequsitions with has been posted before
	 * the current date and will expire after the current date
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public List<JobReqBean> getJobReqs() throws SQLException {
		List<JobReqBean> list = jobReqDao.getJobReqs();
		return list;
	}

	/**
	 * Retrieve the title of the JobRequsition with the given job requisition id
	 * 
	 * @param jobReqId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public String getJobReqTitleByJobReqId(Integer jobReqId) throws SQLException {
		String title = jobReqDao.getJobReqTitleByJobReqId(jobReqId);
		return title;
	}
}
