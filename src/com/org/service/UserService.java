package com.org.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.org.beans.UserBean;
import com.org.dao.UserDao;
import com.org.dao.UserDaoImpl;

public class UserService {

	public UserService() {
	}

	UserDao userDao = new UserDaoImpl();
	UserBean userBean = new UserBean();

	/**
	 * Retrieve the userName of the user by setting the email and password
	 * instance in the userbean object and passing the userbean object
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public String getUserName(String email, String password) throws SQLException {
		userBean.setEmail(email);
		userBean.setPassword(password);
		String userName = userDao.getUserName(userBean);
		return userName;
	}

	/**
	 * Retrieve the user id of the userName by checking it against the userName
	 * 
	 * @param userName
	 * @return
	 * 
	 * @throws SQLException
	 */
	public Integer getUserIdByUserName(String userName) throws SQLException {
		Integer id = userDao.getUserIdByUsername(userName);
		return id;
	}

	/**
	 * Retrieve all the details of the user and return it as a hashmap having
	 * the user_id as key and firstname+lastname as value
	 * 
	 * @return
	 * 
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getUsers() throws SQLException {
		HashMap<Integer, String> hashMap = userDao.getUsers();
		return hashMap;
	}

	/**
	 * Retrieve those users whose name starts with the initials typed by the
	 * client
	 * 
	 * @param name
	 * @return
	 * 
	 * @throws SQLException
	 */
	public List getUsersByName(String name) throws SQLException {
		List jsonList = userDao.getUsersByName(name);
		return jsonList;
	}

	/**
	 * Check if the email and userName is already in use by any user
	 * 
	 * @param email
	 * @param userName
	 * @return
	 * 
	 * @throws SQLException
	 */
	public boolean isUserExists(String email, String userName) throws SQLException {
		boolean success = userDao.isUserExists(email, userName);
		return success;
	}

	/**
	 * Create a new user with all the details being passed as a UserBean object
	 * by setting the parameters of the UserBean object
	 * 
	 * @param userName
	 * @param firstname
	 * @param lastName
	 * @param password
	 * @param email
	 * @param phone
	 * @param companyName
	 * @return
	 * 
	 * @throws SQLException
	 */
	public String addUser(UserBean userBean) throws SQLException {
		
		String userNames = userDao.addUser(userBean);
		return userNames;
	}
}
