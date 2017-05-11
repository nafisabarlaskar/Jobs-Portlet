package com.org.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.org.service.CandidateService;
import com.org.service.UserService;
import com.org.beans.CandidateBean;
import com.org.beans.UserBean;
import com.org.enums.Constants;

@WebServlet("/AccountServlet")
@MultipartConfig(maxFileSize = 16177215)
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public AccountServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userName = request.getParameter("userName");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		String phoneNumber = request.getParameter("phone");
		String createUpdate = request.getParameter("createUpdate");
		Long phone = Long.parseLong(phoneNumber.trim());

		int formId = Integer.parseInt(createUpdate);

		HttpSession sessions = request.getSession(false);
		String name = (String) sessions.getAttribute("names");

		CandidateBean candidateBean = new CandidateBean();
		UserBean userBean = new UserBean();
		CandidateService candidateService = new CandidateService();

		UserService userService = new UserService();
		try {
			if (formId == 1) {
				boolean candidateExists = candidateService.isCandidateExistsByEmail(email, userName);
				if (!candidateExists) {
					candidateBean.setUserName(userName);
					candidateBean.setFirstName(firstName);
					candidateBean.setLastName(lastName);
					candidateBean.setPassword(password);
					candidateBean.setEmail(email);
					candidateBean.setCountry(country);
					candidateBean.setPhone(phone);
					String names = candidateService.addCandidate(candidateBean);
					sessions.setAttribute("names", names);
					sessions.setAttribute("username", "candidate");
					response.setContentType("text/plain");
					response.getWriter().write(Constants.SUCCESS_MESSAGE);
				} else {
					sessions.setAttribute("loginMessage", "Email or username already exists");
					response.setContentType("text/plain");
					response.getWriter().write(Constants.FAILURE_MESSAGE);
				}
			}

			else if (formId == 2) {
				Part filePart = request.getPart("resume");
				InputStream resume = null;

				if (filePart != null) {
					resume = filePart.getInputStream();
				}
				String candId = request.getParameter("candidateId");
				Integer candidateId = Integer.valueOf(candId);
				boolean candidateExists = candidateService.isCandidateExists(candidateId);
				if (!candidateExists) {
					candidateBean.setCandidateId(candidateId);
					candidateBean.setCountry(country);
					candidateBean.setFirstName(firstName);
					candidateBean.setLastName(lastName);
					candidateBean.setPhone(phone);
					candidateBean.setInputStream(resume);
					boolean updateCandidate = candidateService.updateCandidate(candidateBean);
					if (updateCandidate) {
						sessions.setAttribute("names", name);
						sessions.setAttribute("username", "candidate");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CandidateProfile.jsp");
						requestDispatcher.forward(request, response);
					} else {
						sessions.setAttribute("updateMessage", "Update has failed! Please enter valid input!");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CandidateProfile.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			} else if (formId == 3) {
				String companyName = request.getParameter("companyName");
				boolean userExists;
				userExists = userService.isUserExists(email, userName);
				if (!userExists) {
					userBean.setUserName(userName);
					userBean.setFirstName(firstName);
					userBean.setLastName(lastName);
					userBean.setEmail(email);
					userBean.setPassword(password);
					userBean.setCompanyName(companyName);
					userBean.setPhone(phone);
					
					String username = userService.addUser(userBean);
					
					if (username != null) {
						sessions.setAttribute("names", username);
						sessions.setAttribute("username", "admin");
						response.setContentType("text/plain");
						response.getWriter().write(Constants.SUCCESS_MESSAGE);
					} else {
						sessions.setAttribute("loginMessage", "Invalid Username");
						response.setContentType("text/plain");
						response.getWriter().write(Constants.FAILURE_MESSAGE);
					}
				} else {
					sessions.setAttribute("loginMessage", Constants.USERNAME_EMAIL_EXISTS);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
					requestDispatcher.forward(request, response);
				}
			}
		} catch (SQLException e1) {
			request.setAttribute("message", e1);
			RequestDispatcher requestDipatcher = request.getRequestDispatcher("/Error.jsp");
			requestDipatcher.include(request, response);
		}

	}

}
