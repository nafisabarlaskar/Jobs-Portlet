package com.org.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.org.beans.EducationBean;
import com.org.service.EducationService;

@WebServlet("/CandidateEducationServlet")
public class CandidateEducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CandidateEducationServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String school = request.getParameter("school");
		String degree = request.getParameter("degree");
		String grade = request.getParameter("grade");
		String description = request.getParameter("description");
		String sDate = request.getParameter("startDate");
		String eDate = request.getParameter("endDate");
		String canId = request.getParameter("candidateId");
		String eduId = request.getParameter("educationId");

		Integer educationId;
		boolean success = false;

		educationId = Integer.valueOf(eduId);

		Integer candidateId = Integer.valueOf(canId);

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
		
		EducationService educationService = new EducationService();
		EducationBean educationBean = new EducationBean();
		
		boolean successId;
		try {
			successId = educationService.isCandidateEducationExists(educationId);
			if (!successId){
				educationBean.setCandidateId(candidateId);
				educationBean.setSchool(school);
				educationBean.setGrade(grade);
				educationBean.setDegree(degree);
				educationBean.setDescription(description);
				educationBean.setStartDate(startDate);
				educationBean.setEndDate(endDate);
				success = educationService.addCandidateEducation(educationBean);
			}
			else {
				success = educationService.updateCandidateEducation(candidateId, school, grade, degree, description,
						startDate, endDate, educationId);
			}
			response.sendRedirect("CandidateProfile.jsp");
		} catch (SQLException e1) {
			request.setAttribute("message", e1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}

	}

}
