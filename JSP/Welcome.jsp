<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.IOException"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.http.*"%>
<%@ include file="HeaderLayout.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<title>Welcome user</title>
</head>

<body>

	<%
		String names = (String) session.getAttribute("names");
	%>

	<p class="paragraph">
		Hi
		<%=names%>
		<%
			if (session.getAttribute("successMessage") != null) {
		%>
		<%=session.getAttribute("successMessage")%>
	</p>

	<%
		}
	%>
</body>
</html>