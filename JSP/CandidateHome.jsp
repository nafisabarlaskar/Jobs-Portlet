<%@page import="com.org.beans.JobReqBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@page import="javax.servlet.http.*"%>
<%@ include file="HeaderLayout.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<title>Home</title>
<style>
</style>
<script src="Resources/js/generateHtml.js"></script>
</head>

<body onload="onloadPage('JobReqServlet',createCandidateHomeUI)">
	<div>

		<% if((String)session.getAttribute("jobMessage") != null){ %>
		<p><%=(String)session.getAttribute("jobMessage") %></p>
		<%
		} 
	%>


		<div id="container"></div>

	</div>
	<div class="footer"></div>

</body>

</html>