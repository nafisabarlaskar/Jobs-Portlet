package com.org.dao;

import java.sql.SQLException;
import java.util.HashMap;

public interface UserRolesDao {

	/**
	 * Add the role_id corresponding to the particular user for a given job requisition
	 * @param job_req_id
	 * @param user_id
	 * @param role_id
	 * @return
	 * @throws SQLException
	 */
	boolean addJobReqRoles(Integer job_req_id, Integer user_id, Integer role_id) throws SQLException;
	/**
	 * Retrieve the role_id and the corresponding user_id for the given job requisition id
	 * @param job_req_id
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, Integer> getJobReqRoles(Integer job_req_id) throws SQLException;

}
