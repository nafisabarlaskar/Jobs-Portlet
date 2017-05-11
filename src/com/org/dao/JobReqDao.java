package com.org.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.org.beans.JobReqBean;

public interface JobReqDao {
	
	/**
	 * Create a new job requisition by passing JobReqBean object as a parameter
	 * @param jobReqBean
	 * @return
	 * @throws SQLException
	 */
	public Integer addJobReq(JobReqBean jobReqBean) throws SQLException;
	/**
	 * Retrieve the titles of the JobRequsitions with which the user is associated with different roles 
	 * @param user_id
	 * @return
	 * @throws SQLException
	 
	 */
	public HashMap<Integer, String> getJobReqTitleByUserId(Integer user_id) throws SQLException;
	/**
	 * Retrieve the titles of the JobRequsitions with has been posted before the current date and will expire after the current date
	 * @return
	 * @throws SQLException
	 
	 */
	public HashMap<Integer, String> getJobReqTitle() throws SQLException;
	/**
	 * Retrieve the details of the JobRequsitions with has been posted before the current date and will expire after the current date
	 * @return
	 * @throws SQLException
	 
	 */
	public List<JobReqBean> getJobReqs() throws SQLException;
	/**
	 * Retrieve the title of the JobRequsition with the given job requisition id
	 * @param job_req_id
	 * @return
	 * @throws SQLException
	 
	 */
	public String getJobReqTitleByJobReqId(Integer job_req_id) throws SQLException;
	
}
