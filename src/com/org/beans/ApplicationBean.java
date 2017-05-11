package com.org.beans;

import java.io.InputStream;

/**
 * @author I332022 Sets and retrieves the fields associated with a job
 *         application
 */
public class ApplicationBean {

	public ApplicationBean() {
	}

	private Long phone;
	private InputStream inputStream;
	private Integer jobReqId;
	private Integer candidateId;
	private String skills;

	/**
	 * @return skills
	 */
	public String getSkills() {
		return skills;
	}

	/**
	 * @param skills
	 *            sets the skills of the candidate required for applying in the
	 *            job
	 */
	public void setSkills(String skills) {
		this.skills = skills;
	}

	/**
	 * @return phone
	 */
	public Long getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            sets the phone number of the candidate applying in the job
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}

	/**
	 * @return resume as inputStream
	 */
	public InputStream getinputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            sets the resume of the candidate applying in the job as
	 *            inputStream
	 */
	public void setinputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return jobReqId
	 */
	public Integer getJobReqId() {
		return jobReqId;
	}

	/**
	 * @param jobReqId
	 *            sets the jobReqId of the job in which the candidate is
	 *            applying
	 */
	public void setJobReqId(Integer jobReqId) {
		this.jobReqId = jobReqId;
	}

	/**
	 * @return candidateId
	 */
	public Integer getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId
	 *            sets the candidateId of the candidate applying in the job
	 */
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

}
