package com.org.beans;

import java.io.*;

/**
 * @author I332022 saet and retrieves the fields associated with a user
 */
public class UserBean {

	private String userName;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String country;
	private Long phone;
	private String companyName;

	public UserBean() {

	}

	/**
	 * @return userName of the user
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            sets userName of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return companyName of the user
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            sets companyName of the user
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return password of the user
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            sets password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return email of the user
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            sets email of the user
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return firstName of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            sets firstName of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return lastName of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            sets lastName of the user
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return country of the user
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            sets country of the user
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return phone of the user
	 */
	public Long getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            sets phone of the user
	 */
	public void setPhone(Long phone) {
		this.phone = phone;
	}
}
