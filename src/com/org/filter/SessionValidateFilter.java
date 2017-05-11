package com.org.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

/**
 * Servlet Filter implementation class SessionValidateFilter
 */
//@WebFilter("/SessionValidateFilter")
public class SessionValidateFilter implements Filter {

    /**
     * Default constructor. 
     */
    public SessionValidateFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		 HttpServletRequest request_http = (HttpServletRequest) request;
		    HttpServletResponse response_http = (HttpServletResponse) response;
		    HttpSession session = request_http.getSession(false);
		    //String names = null;

		    if (session == null || session.getAttribute("names") == null ) {
		        response_http.sendRedirect("CandidateLogin.jsp"); // No logged-in user found, so redirect to login page.
		    } else {
		    	 response_http.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		         response_http.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		         response_http.setDateHeader("Expires", 0);
		    	chain.doFilter(request, response); // Logged-in user found, so just continue request.
		    }
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
