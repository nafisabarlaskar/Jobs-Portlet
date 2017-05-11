<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%--  <%@ include file="HomeLayout.jsp" %> --%>
<%--  <%@ include file="Layout.jsp" %> --%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">

<title>Home</title>
<script src="Resources/js/validate.js"></script>
</head>
<body>

	<div class="top-header">
		<img src="Resources/images/SAP.png" height="50px" padding="10px" />
	</div>
	<div class="header">
		<div class="header-top">
			<a href="Home.jsp"> Requisition Management </a>
		</div>
		<div class="header-bottom">
			<div>
				<a onclick="showHideElementsByClass('candidateLogin','home')">CANDIDATE
					LOGIN</a> <a onclick="showHideElementsByClass('adminLogin','home')">ADMIN
					LOGIN</a>
			</div>
		</div>
	</div>
	<%
		if (session.getAttribute("loginMessage") != null) {
	%>
	<p><%=session.getAttribute("loginMessage")%></p>

	<%
		}
	%>
	<div id="candidateLogin" style="display: none;" class="home">
		<p class="paragraph">Candidate Login</p>
		<form action="LoginServlet" method="post" class="login">
			<input type="hidden" name="loginId" value="1" /> <input type="text"
				placeholder="Email" name="email" required /> <input type="password"
				placeholder="Password" name="password" required /> <input
				class="formSubmit" type="submit" value="Login" />
		</form>

		<div class="register">
			<a href="CandidateRegister.jsp">Sign up?</a>
		</div>
	</div>

	<div id="adminLogin" style="display: none;" class="home">
		<p class="paragraph">Employer Login</p>

		<form action="LoginServlet" method="post" class="login">
			<input type="hidden" name="loginId" value="2" /> <input type="text"
				placeholder="Email" name="email" required /> <input type="password"
				placeholder="Password" name="password" required /> <input
				class="formSubmit " type="submit" value="Login" />
		</form>
		<div class="register">
			<a href="AdminRegister.jsp">Sign up?</a>
		</div>
	</div>

</body>
</html>