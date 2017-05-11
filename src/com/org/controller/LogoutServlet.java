package com.org.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String logId = request.getParameter("logoutId");
		int logoutId = Integer.parseInt(logId);

		HttpSession session = request.getSession();

		if (logoutId == 1) {
			session.invalidate();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
			requestDispatcher.forward(request, response);

		} else if (logoutId == 2) {
			session.invalidate();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Home.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
