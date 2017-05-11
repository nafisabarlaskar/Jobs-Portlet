package com.org.beans;

import java.io.InputStream;

/**
 * @author I332022 CandidateBean class has methods for setting and getting all
 *         the fields pertaining to a candidate
 *
 */
public class CandidateBean {

	public CandidateBean() {
	}

	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String country;
	private Long phone;
	private InputStream inputStream;
	private Integer candidateId;

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param username
	 *            set username
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            sets the candidates' password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            set email of the candidate
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return firstname
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstname
	 *            sets firstname of the candidate
	 */
	public void setFirstName(String firstname) {
		this.firstName = firstname;
	}

	/**
	 * @return lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastname
	 *            sets the lastname of the candidate
	 */
	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	/**
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            sets country of the candidate
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return phone
	 */
	public Long getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            sets the phone number of the candidate
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}

	/**
	 * @return resume as inputstream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream
	 *            sets the resume of the candidate as inputstream
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return candidateId
	 */
	public Integer getCandidateId() {
		return candidateId;
	}

	/**
	 * @param candidateId
	 *            sets the candidateId of the candidate
	 */
	public void setCandidateId(Integer candidateId) {
		this.candidateId = candidateId;
	}

}
