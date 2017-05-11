<%@page import="com.org.controller.JobApplicationServlet"%>
<%@page import="com.org.service.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="com.org.beans.*"%>
<%@ page import="com.org.service.CandidateService"%>
<%@ page import="com.org.service.JobReqService"%>
<%@ include file="HeaderLayout.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<script src="Resources/js/jobRequisition.js"></script>
<script src="Resources/js/generateHtml.js"></script>
<script src="Resources/js/validate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">

<style>
</style>
<title>Apply</title>

</head>
<%
		String names = (String) session.getAttribute("names");
		String id = request.getParameter("jobReqId");
		Integer jobReqId = Integer.valueOf(id);

		JobReqService jobReq = new JobReqService();
		String title = jobReq.getJobReqTitleByJobReqId(jobReqId);
	%>
	<p class="paragraph"><%=title%></p>
	<div id="container"></div>
	<%
		CandidateService candidateService = new CandidateService();
		Integer candidateId = candidateService.getCandidateId(names);
	%>
	<script>
	  var candidateId =  <%=candidateId %>;
	  var jobReqId = <%=jobReqId%>
	</script>

<body onload="onloadPage('JobApplicationServlet?candidateId='
				+ candidateId,createJobApplicationUI)">

	

</body>
</html>