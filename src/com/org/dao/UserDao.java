package com.org.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.org.beans.UserBean;

public interface UserDao{

	/**
	 * Retrieve the username of the user by setting the email and password instance in the userbean object and passing the userbean object
	 * @param userBean
	 * @return
	 * @throws SQLException
	 */
	public String  getUserName(UserBean userBean) throws SQLException;
	/**
	 * Retrieve the user id of the username by checking it against the username
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public Integer getUserIdByUsername(String username) throws SQLException;
	/**
	 * Retrieve all the details of the user and return it as a hashmap having the user_id as key and firstname+lastname as value
	 * @return
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getUsers() throws SQLException;
	/**
	 * Retrieve those users whose name starts with the initials typed by the client
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public List<Map<Integer, String>> getUsersByName(String name) throws SQLException;
	/**
	 * Check if the email and username is already in use by any user
	 * @param email
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public boolean isUserExists(String email, String username) throws SQLException;
	/**
	 * Create a new user with all the details being passed as a UserBean object
	 * @param userBean
	 * @return
	 * @throws SQLException
	 */
	public String addUser(UserBean userBean) throws SQLException;
	
}
