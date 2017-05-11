package com.org.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import com.org.beans.ApplicationBean;
import com.org.beans.CandidateBean;
import com.org.service.ApplicationService;
import com.google.gson.Gson;
import com.org.service.CandidateService;
import com.org.service.JobReqService;
import com.org.controller.AccountServlet;
import com.org.enums.Constants;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "JobApplicationServlet", urlPatterns = { "/JobApplicationServlet" })
@MultipartConfig(maxFileSize = 16177215)
public class JobApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public JobApplicationServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("jobReqId");
		String jobReqParam = request.getParameter("jobReqParameter");
		String candId = request.getParameter("candidateId");
		Integer jobReqParameter = Integer.valueOf(jobReqParam);
		Integer jobReqId = Integer.valueOf(id);
		
		CandidateService candidateService = new CandidateService();
		
		try {
			if (jobReqParameter == 1) {
				String phoneNumber = request.getParameter("phone");
				String skills = request.getParameter("skills");
				Long phone = Long.parseLong(phoneNumber.trim());
				InputStream inputStream = null;
				Part filePart = request.getPart("resume");

				if (filePart != null) {
					inputStream = filePart.getInputStream();
				}
				HttpSession sessions = request.getSession(false);
				Integer candidateId = Integer.valueOf(candId);
				ApplicationService applicationService = new ApplicationService();
				ApplicationBean applicationBean = new ApplicationBean();
				boolean applicationExists;

				applicationExists = applicationService.isApplicationExists(jobReqId, candidateId);
				
				if (applicationExists) {
					sessions.setAttribute("jobMessage", "You have already applied in this job#" + jobReqId);
					response.setContentType("text/plain");
					response.getWriter().write(Constants.FAILURE_MESSAGE);
				} else {
					boolean success;
					applicationBean.setCandidateId(candidateId);
					applicationBean.setPhone(phone);
					applicationBean.setSkills(skills);
					applicationBean.setJobReqId(jobReqId);
					applicationBean.setinputStream(inputStream);
					success = applicationService.addCandidateApplication(applicationBean);
					if (success) {
						sessions.setAttribute("jobMessage", "You have successfully applied in the job#"+jobReqId);
						response.setContentType("text/plain");
						response.getWriter().write(Constants.SUCCESS_MESSAGE);
					} 
				}
			} else if (jobReqParameter == 2) {
				Gson gson = new Gson();
				List jsonList;
				jsonList = candidateService.getAppliedCandidates(jobReqId);
				String jsonString = gson.toJson(jsonList);
				response.setContentType("application/json");
				response.getWriter().write(jsonString);

			}
		} catch (SQLException e1) {
			request.setAttribute("message", e1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("candidateId");
		Integer candidateId = Integer.valueOf(id);
		Gson gson = new Gson();
		CandidateService candidateService = new CandidateService();
		List jsonList;
		try {
			jsonList = candidateService.getCandidatesByCandidateId(candidateId);
			String jsonString = gson.toJson(jsonList);

			response.setContentType("application/json");
			response.getWriter().write(jsonString);

		} catch (SQLException e) {
			request.setAttribute("message", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}
	}

}
