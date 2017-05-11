package com.org.beans;

/**
 * @author I332022 UserRolesBean class has methods for setting and getting all
 *         the fields pertaining to a candidate
 */
public class UserRolesBean {

	private Integer jobReqId;
	private Integer recruiterId1;
	private Integer recruiterId2;
	private Integer vicePresidentId;
	private Integer hrManagerId;
	private Integer coordinatorId;

	public UserRolesBean() {

	}

	/**
	 * @return jobReqId
	 */
	public Integer getJobReqId() {
		return jobReqId;
	}

	/**
	 * @param jobReqId
	 * sets the jobReqId of the bean in which the user is associated with a role
	 */
	public void setJobReqId(Integer jobReqId) {
		this.jobReqId = jobReqId;
	}

	/**
	 * @return recruiterId1
	 */
	public Integer getRecruiter1Id() {
		return recruiterId1;
	}

	/**
	 * @param recruiterId1
	 * sets the id of the first recruiter role
	 */
	public void setRecruiter1Id(Integer recruiterId1) {
		this.recruiterId1 = recruiterId1;
	}

	/**
	 * @return recruiterId2
	 */
	public Integer getRecruiter2Id() {
		return recruiterId2;
	}

	/**
	 * @param recruiterId2
	 *  sets the user id of the second recruiter role
	 */
	public void setRecruiter2Id(Integer recruiterId2) {
		this.recruiterId2 = recruiterId1;
	}

	/**
	 * @return coorinatorId
	 */
	public Integer getCoordinatorId() {
		return coordinatorId;
	}

	/**
	 * @param coordinatorId
	 *  sets the user id of the user associated with coordinator role
	 */
	public void setCoordinatorId(Integer coordinatorId) {
		this.coordinatorId = coordinatorId;
	}

	/**
	 * @return vicePresidentId
	 */
	public Integer getVicePresidentId() {
		return vicePresidentId;
	}

	/**
	 * @param vicePresidentId
	 *  sets the user id of the user associated with vice president role
	 */
	public void setVicePresidentId(Integer vicePresidentId) {
		this.vicePresidentId = vicePresidentId;
	}

	/**
	 * @return hrManagerId
	 */
	public Integer getHrManagerId() {
		return hrManagerId;
	}

	/**
	 * @param hrManagerId
	 *  sets the user id of the user associated with hrManager role
	 */
	public void setHrManagerId(Integer hrManagerId) {
		this.hrManagerId = hrManagerId;
	}

}
