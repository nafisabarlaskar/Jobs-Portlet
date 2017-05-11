<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="Layout.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<script src="Resources/js/jobRequisition.js"></script>
</head>
<body>
	<%
		if ((String) session.getAttribute("names") == null) {
			response.sendRedirect("Home.jsp");
		} else {
			if (session.getAttribute("username") == "admin") {
	%>
	<div class="header-bottom">

		<div>

			<a href="Welcome.jsp">HOME</a> <a href="CreateJobRequisition.jsp">CREATE
				REQUISITION</a> <a href="ViewCandidates.jsp">VIEW APPLICANTS</a> <a
				href="LogoutServlet?logoutId=2">LOGOUT</a>
		</div>
	</div>
	</div>
	<%
		}
	%>
	<%
		if (session.getAttribute("username") == "candidate") {
	%>
	<div class="header-bottom">

		<div>
			<a href="CandidateHome.jsp">JOBS</a> <a href="CandidateProfile.jsp">VIEW
				PROFILE</a> <a href="LogoutServlet?logoutId=1">LOGOUT</a>
		</div>
	</div>
	</div>
	</div>
	<%
		}
		}
	%>
</body>
</html>