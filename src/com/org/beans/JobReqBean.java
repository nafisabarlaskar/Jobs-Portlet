package com.org.beans;

import java.util.Date;

public class JobReqBean {

	private String title;
	private String description;
	private String department;
	private String location;
	private Date startDate;
	private Date endDate;
	private Integer creatorId;
	private Integer jobReqId;
	UserRolesBean userRolesBean;

	public JobReqBean() {

	}

	/**
	 * @return userRolesBean object for the roles created in the jobReq
	 */
	public UserRolesBean getUserRoleBean() {
		return userRolesBean;
	}

	/**
	 * @param userRolesBean
	 *            set roles of the posted jobreq as UserRolesBean object
	 */
	public void setUserRoleBean(UserRolesBean userRolesBean) {
		this.userRolesBean = userRolesBean;
	}

	/**
	 * @return title of the posted jobreq
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            set title of the posted jobreq
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return description about the posted jobReq
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            set description about the posted jobreq
	 */
	public void setDesciption(String description) {
		this.description = description;
	}

	/**
	 * @return department of the posted jobReq
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            set department of the posted jobreq
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return location of the jobReq
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            set location of the posted jobreq
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return jobReqId
	 */
	public Integer getJobReqId() {
		return jobReqId;
	}

	/**
	 * @param jobReqId
	 *            set jobReqId of the posted jobreq
	 */
	public void setJobReqId(Integer jobReqId) {
		this.jobReqId = jobReqId;
	}

	/**
	 * @return startDate of the posted jobreq
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            set start date of the posted jobreq
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return endDate of the posted jobreq
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            set expiry date of the posted jobreq
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return creatorId of the creator of the job req
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId
	 *            sets the id of the user who created the job requisition
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

}
