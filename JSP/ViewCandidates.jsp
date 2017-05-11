<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.org.controller.JobApplicationServlet"%>
<%@page import="com.org.service.*"%>
<%@page import="java.util.*"%>
<%@ page import="com.google.gson.Gson"%>
<%@ include file="HeaderLayout.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<title>View Applicants</title>
<script src="Resources/js/jobRequisition.js"></script>
</head>

<body>

	<%
		String names = (String) session.getAttribute("names");
	%>
	<p class="paragraph">View Applied Candidates</p>
	<p class="paragraph">
		Select Job Title: <select id="jobReqId" onchange="loadApplicant();">
			<%
				UserService user = new UserService();
				Integer user_id = user.getUserIdByUserName(names);

				JobReqService jobRequisition = new JobReqService();
				HashMap<Integer, String> hashMap = jobRequisition.getJobTitleByUserId(user_id);
				Iterator it = hashMap.entrySet().iterator();

				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry) it.next();
			%>
			<option value="<%=pair.getKey()%>">
				<%=pair.getValue()%>
			</option>

			<%
				it.remove();
				}
			%>
		</select>
	</p>


	<div id="recordsTable"></div>

</body>

</html>