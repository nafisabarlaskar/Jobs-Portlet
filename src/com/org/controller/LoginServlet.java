package com.org.controller;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.org.service.CandidateService;
import com.org.service.UserService;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String logId = request.getParameter("loginId");

		int loginId = Integer.parseInt(logId);
		try {
			if (loginId == 1) {
				CandidateService candidateService = new CandidateService();
				String names;
				names = candidateService.getCandidateUsername(email, password);
				if (names != null) {
					HttpSession session = request.getSession();
					session.setAttribute("names", names);
					session.setAttribute("username", "candidate");
					response.sendRedirect("/JobRequisition/CandidateHome.jsp");
				} else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CandidateLogin.jsp");
					requestDispatcher.include(request, response);
				}

			}
			if (loginId == 2) {
				UserService userService = new UserService();
				String names;
				names = userService.getUserName(email, password);
				if (names != null) {
					HttpSession session = request.getSession();
					session.setAttribute("names", names);
					session.setAttribute("username", "admin");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Welcome.jsp");
					requestDispatcher.forward(request, response);
				} else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/AdminLogin.jsp");
					requestDispatcher.include(request, response);
				}

			}

		} catch (SQLException e) {
			request.setAttribute("message", e);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
			requestDispatcher.include(request, response);
		}
	}
}
