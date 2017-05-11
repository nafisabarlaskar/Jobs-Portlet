<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="HeaderLayout.jsp"%>
<%@ page import="com.org.service.*"%>
<%@ page import="com.org.beans.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="Resources/js/jobRequisition.js"></script>
<script src="Resources/js/validate.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<title>Candidate Profile</title>
</head>
<body>
	<%
		String names = (String) session.getAttribute("names");
		CandidateService candidateService = new CandidateService();

		Integer candidateId = (Integer) candidateService.getCandidateId(names);
		List<CandidateBean> candidateDetails = candidateService.getCandidatesByCandidateId(candidateId);

		for (CandidateBean x : candidateDetails) {
	%>
	<div class="jobsDisplay">
		<div class="jobsDisplayTitle">
			Profile <a onclick="showHide('candidate', 'candidateDetails')">Edit</a>
		</div>
		<div style="display: block;" id="candidateDetails">
			<div class="jobsDisplayTitle">First Name:</div>
			<div><%=x.getFirstName()%></div>
			<div class="jobsDisplayTitle">Last Name:</div>
			<div><%=x.getLastName()%></div>
			<div class="jobsDisplayTitle">Username:</div>
			<div><%=x.getUserName()%></div>
			<div class="jobsDisplayTitle">Phone:</div>
			<div><%=x.getPhone()%></div>
			<div class="jobsDisplayTitle">Email:</div>
			<div><%=x.getEmail()%></div>
			<div class="jobsDisplayTitle">Country:</div>
			<div><%=x.getCountry()%></div>
			<div class="jobsDisplayTitle">
				<a onclick="showHide('education', 'education');">Add Education</a>
			</div>
			<div class="jobsDisplayTitle">
				<a onclick="showHide('viewApplication')">View Applied Jobs </a>
			</div>
		</div>
	</div>
	<div class="popUpForm" id="candidate">
		<div id="popUp" class="popUp">
			<form class="editDetails" action="AccountServlet" method="post"
				enctype="multipart/form-data">
				<a id="close" class="close"
					onclick="showHide('candidate', 'candidateDetails')"><img
					src="Resources/images/circle.png" /></a> <input type="hidden"
					name="createUpdate" value="2" /> <input type="hidden"
					value="<%=candidateId%>" name="candidateId" />
				<div class="jobsDisplayTitle">First Name:</div>
				<div>
					<input type="text" name="firstName" value="<%=x.getFirstName()%>">
				</div>
				<div class="jobsDisplayTitle">Last Name:</div>
				<div>
					<input type="text" value="<%=x.getLastName()%>" name="lastName">
				</div>
				<div class="jobsDisplayTitle">Phone:</div>
				<div>
					<input type="number" value="<%=x.getPhone()%>" name="phone">
				</div>
				<div class="jobsDisplayTitle">Country:</div>
				<div>
					<input type="text" value="<%=x.getCountry()%>" name="country">
				</div>
				<div class="jobsDisplayTitle">Resume:</div>
				<div>
					<input type="file" value="Resume" name="resume" />
				</div>
				<div>
					<input type="submit" value="Save" />
				</div>
			</form>
		</div>
	</div>

	<%
		}
	%>
	</div>
	<!-- 	<div class="educationContainer"> -->
	<%
		EducationService education = new EducationService();
		List<EducationBean> list = education.getCandidateEducation(candidateId);
		Integer id = null;
		Integer count = 0;
		for (EducationBean e : list) {
			count++;
			id = e.getEducationId();
	%>
	<div class="jobsDisplay">
		<div class="jobsDisplayTitle">
			Education
			<%=count%>
			<a onclick="showHide('form<%=id%>', 'text<%=id%>')"> Edit</a>
		</div>
		<div id="text<%=id%>" class="">
			<div class="jobsDisplayTitle">School:</div>
			<div><%=e.getSchool()%></div>
			<div class="jobsDisplayTitle">Degree:</div>
			<div><%=e.getDegree()%></div>
			<div class="jobsDisplayTitle">Grade:</div>
			<div><%=e.getGrade()%></div>
			<div class="jobsDisplayTitle">Description:</div>
			<div><%=e.getDescription()%></div>
			<div class="jobsDisplayTitle">Start Date:</div>
			<div><%=e.getStartDate()%></div>
			<div class="jobsDisplayTitle">End Date:</div>
			<div><%=e.getEndDate()%></div>
		</div>
	</div>
	<div class="popUpForm" id="form<%=id%>">
		<div id="popUp" class="popUp">
			<form action="CandidateEducationServlet" method="post"
				class="editDetails">
				<a id="close" class="close"
					onclick="showHide('form<%=id%>', 'text<%=id%>')"><img
					src="Resources/images/circle.png" /></a>
				<div>
					<input type="hidden" name="educationId" value="<%=id%>">
				</div>
				<div>
					<input type="hidden" value="<%=candidateId%>" name="candidateId" />
				</div>
				<div class="jobsDisplayTitle">School:</div>
				<div>
					<input type="text" name="school" value="<%=e.getSchool()%>" />
				</div>
				<div class="jobsDisplayTitle">Degree:</div>
				<div>
					<input type="text" name="degree" value="<%=e.getDegree()%>" />
				</div>
				<div class="jobsDisplayTitle">Grade:</div>
				<div>
					<input type="text" name="grade" value="<%=e.getGrade()%>">
				</div>
				<div class="jobsDisplayTitle">Description:</div>
				<div>
					<input type="text" name="description"
						value="<%=e.getDescription()%>" />
				</div>
				<div class="jobsDisplayTitle">Start Date:</div>
				<div>
					<input type="date" name="startDate" value="<%=e.getStartDate()%>" />
				</div>
				<div class="jobsDisplayTitle">End Date:</div>
				<div>
					<input type="date" name="endDate" value="<%=e.getEndDate()%>" />
				</div>
				<div class="jobsDisplayTitle">
					<input type="submit" value="Save" />
				</div>
			</form>
		</div>
	</div>
	<%
		}
	%>
	<!-- 	</div> -->
	<%
		if (request.getAttribute("updateMessage") != null) {
	%>
	<p>
		Error:
		<%=request.getAttribute("updateMessage")%></p>
	<%
		}
	%>
	<div class="popUpForm" id="education">
		<div id="popUp" class="popUp">
			<form action="CandidateEducationServlet" method="post"
				class="editDetails">
				<a id="close" class="close" onclick="showHide('education', null)"><img
					src="Resources/images/circle.png" /></a> <input type="hidden"
					value="<%=candidateId%>" name="candidateId" /> <input
					type="hidden" value="0" name="educationId" />
				<div class="jobsDisplayTitle">School:</div>
				<div>
					<input type="text" name="school" required />
				</div>
				<div class="jobsDisplayTitle">Degree:</div>
				<div>
					<input type="text" name="degree" required />
				</div>
				<div class="jobsDisplayTitle">Grade:</div>
				<div>
					<input type="text" name="grade" />
				</div>
				<div class="jobsDisplayTitle">Start Date:</div>
				<div>
					<input type="date" name="startDate" required />
				</div>
				<div class="jobsDisplayTitle">End Date:</div>
				<div>
					<input type="date" name="endDate" required />
				</div>
				<div class="jobsDisplayTitle">Description:</div>
				<div>
					<input type="text" name="description" />
				</div>
				<div>
					<input type="submit" value="Save">
				</div>
			</form>
		</div>
	</div>

	<div id="viewApplication" class="popUpForm">
		<div id="popUp" class="popUp">
			<div class="editDetails">
				<a id="close" class="close"
					onclick="showHide('viewApplication', null)"><img
					src="Resources/images/circle.png" /></a>
				<%
					ApplicationService jobApplication = new ApplicationService();
					List<JobReqBean> jobReqBean = jobApplication.getAppliedJobReqs(candidateId);
					for (JobReqBean j : jobReqBean) {
				%>

				<div class="jobsApplied">
					<div class="jobsDisplayTitle">Job Title:</div>
					<div style="font-weight: normal"><%=j.getTitle()%></div>
					<div class="jobsDisplayTitle">Location:</div>
					<div style="font-weight: normal"><%=j.getLocation()%></div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>