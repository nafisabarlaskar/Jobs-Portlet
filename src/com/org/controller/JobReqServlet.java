package com.org.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import com.org.service.UserRolesService;
import com.google.gson.Gson;
import com.org.beans.JobReqBean;
import com.org.service.JobReqService;
import com.org.service.UserService;

@WebServlet("/JobReqServlet")
@MultipartConfig(maxFileSize = 16177215)
public class JobReqServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String location = request.getParameter("location");
		String department = request.getParameter("department");
		String sDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		String coordinator = request.getParameter("coordinatorId");
		String hrManager = request.getParameter("hrManagerId");
		String vicePresident = request.getParameter("vicePresidentId");
		String recruiter1 = request.getParameter("recruiterId1");
		String recruiter2 = request.getParameter("recruiterId2");

		Date startDate = null;

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			startDate = formatter.parse(sDate);

		} catch (ParseException e) {
			request.setAttribute("message", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}

		Date endDate = null;

		try {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			endDate = formatter.parse(eDate);

		} catch (ParseException e) {
			request.setAttribute("message", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}
		HttpSession sessions = request.getSession(false);
		String names = (String) sessions.getAttribute("names");

		UserService userService = new UserService();
		JobReqBean jobReqBean = new JobReqBean();
		Integer userId;

		try {
			userId = userService.getUserIdByUserName(names);
			JobReqService jobReqService = new JobReqService();
			
			jobReqBean.setTitle(title);
			jobReqBean.setDesciption(description);
			jobReqBean.setLocation(location);
			jobReqBean.setDepartment(department);
			jobReqBean.setStartDate(startDate);
			jobReqBean.setEndDate(endDate);
			jobReqBean.setCreatorId(userId);
			
			Integer jobReqId = jobReqService.addJobReq(jobReqBean);

			Integer recruiterId1 = Integer.valueOf(recruiter1);
			Integer recruiterId2 = Integer.valueOf(recruiter2);
			Integer vicePresidentId = Integer.valueOf(vicePresident);
			Integer hrManagerId = Integer.valueOf(hrManager);
			Integer coordinatorId = Integer.valueOf(coordinator);

			UserRolesService userRolesService = new UserRolesService();
			boolean success = userRolesService.addJobReqRole(jobReqId, recruiterId1, recruiterId2, vicePresidentId,
					hrManagerId, coordinatorId);

			if (success) {
				sessions.setAttribute("successMessage",
						"You have successfully created a new JobRequisition#" + jobReqId + " : " + title);
				String greetings = "Success";
				response.setContentType("text/plain");
				response.getWriter().write(greetings);
			} else {
				sessions.setAttribute("failureMessage", "Failed to create a new JobRequisition");
				String greetings = "Failed to create a job requisition";
				response.setContentType("text/plain");
				response.getWriter().write(greetings);
			}
		} catch (SQLException e) {
			request.setAttribute("message", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		JobReqService jobReqService = new JobReqService();
		Gson gson = new Gson();
		List jsonList;
		try {
			jsonList = jobReqService.getJobReqs();
			String jsonString = gson.toJson(jsonList);

			response.setContentType("application/json");
			response.getWriter().write(jsonString);

		} catch (SQLException e1) {
			request.setAttribute("message", e1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}

	}

}
