package com.org.service;

import java.sql.SQLException;
import java.util.HashMap;

import com.org.dao.UserRolesDao;
import com.org.dao.UserRolesDaoImpl;

public class UserRolesService {

	UserRolesDao userRolesDao = new UserRolesDaoImpl();

	/**
	 * Add the role_id corresponding to the particular user for a given job
	 * requisition
	 * 
	 * @param jobReqId
	 * @param recruiterId1
	 * @param recruiterid2
	 * @param vicePresidentId
	 * @param hrManagerId
	 * @param coordinatorId
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean addJobReqRole(Integer jobReqId, Integer recruiterId1, Integer recruiterid2, Integer vicePresidentId,
			Integer hrManagerId, Integer coordinatorId) throws SQLException {
		boolean recruiter1 = false;
		boolean recruiter2 = false;
		boolean coordinator = false;
		boolean hrManager = false;
		boolean vicePresident = false;
		boolean success;
		if (recruiterId1 != null) {
			recruiter1 = userRolesDao.addJobReqRoles(jobReqId, recruiterId1, 3);
		}
		if (recruiterid2 != null) {
			recruiter2 = userRolesDao.addJobReqRoles(jobReqId, recruiterid2, 5);
		}
		if (hrManagerId != null) {
			hrManager = userRolesDao.addJobReqRoles(jobReqId, hrManagerId, 2);
		}
		if (vicePresidentId != null) {
			vicePresident = userRolesDao.addJobReqRoles(jobReqId, vicePresidentId, 4);
		}
		if (coordinatorId != null) {
			coordinator = userRolesDao.addJobReqRoles(jobReqId, coordinatorId, 1);
		}
		if (recruiter1 != false && recruiter2 && coordinator && hrManager && vicePresident)
			success = true;
		else
			success = false;
		return success;
	}

	/**
	 * Retrieve the role_id and the corresponding user_id for the given job
	 * requisition id
	 * 
	 * @param jobReqId
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, Integer> getJobReqRoles(Integer jobReqId) throws SQLException {
		HashMap<Integer, Integer> hashMap = userRolesDao.getJobReqRoles(jobReqId);
		return hashMap;
	}
}
