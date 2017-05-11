package com.org.beans;

import java.util.Date;

/**
 * @author I332022 has getter and setter methods for all the fields of a
 *         candidate's education list
 */
public class EducationBean {

	public EducationBean() {
		// TODO Auto-generated constructor stub
	}

	private String school;
	private String grade;
	private String degree;
	private String description;
	private Date startDate;
	private Date endDate;
	private Integer candidateId;
	private Integer educationId;

	/**
	 * @return school
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * @param school
	 *            set school of the candidate's education degree
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}

	/**
	 * @param grade
	 *            set grade of the candidate's education degree
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}

	/**
	 * @return degree
	 */
	public String getDegree() {
		return degree;
	}

	/**
	 * @param degree
	 *            set degree name of the candidate's education
	 */
	public void setDegree(String degree) {
		this.degree = degree;
	}

	/**
	 * @return description about the type of education
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            set description about candidate's education degree
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return startDate of the education degree
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            set startDate of the candidate's education degree
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return endDate of the candidate's education degree
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            set endDate of the candidate's education degree
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return candidateId
	 */
	public Integer getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId
	 *            set candidateId of the candidate's education degree
	 */
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

	/**
	 * @return educationId of the candidate's education degree
	 */
	public Integer getEducationId() {
		return educationId;
	}

	/**
	 * @param educationId
	 *            set educationId of the candidate's education degree
	 */
	public void setEducationId(Integer educationId) {
		this.educationId = educationId;
	}
}
