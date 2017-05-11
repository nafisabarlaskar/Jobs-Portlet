<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.org.service.*"%>
<%@ page import="java.util.*"%>
<%@ include file="HeaderLayout.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="Resources/css/jobRequisition.css">
<script src="Resources/js/validate.js"></script>
<script src="Resources/js/generateHtml.js"></script>
<title>Create Job Requisition</title>
</head>

<body>

	<%
		String names = (String) session.getAttribute("names");
	%>
	<%
		if ((String) session.getAttribute("failureMessage") != null) {
	%>
	<p><%=(String) session.getAttribute("failureMessage")%></p>
	<%
		}
	%>
	<%!public UserService user;
	public HashMap<Integer, String> hashMap;%>

	<%
		user = new UserService();
		hashMap = user.getUsers();
	%>

	<p class="paragraph">Create a Job Requisition</p>

	<div class="createJob">

		<form method="post" name="createJob" id="createJob">

			<div>
				Job Title: <span class="formSpan">*</span>
			</div>
			<div class="createJobTitle">
				<input type="text" name="title" id="title"
					placeholder="Enter the job title"
					onblur="validate('text', 'title')" required />
			</div>
			<div id="titleDiv" class="divValidation"></div>

			<div>
				Job Description: <span class="formSpan">*</span>
			</div>
			<div>
				<textarea class="createJobDescription" 
					name="description" placeholder="Enter the job description" required /></textarea>
			</div>

			<div>
				Department:<span class="formSpan">*</span>
			</div>
			<div>
				<select name="department">
					<option value="Computer Science">Computer Science</option>
					<option value="IT">IT</option>
					<option value="Mathematics and Computation">Mathematics
						and Computation</option>
					<option value="Computer Engineering">Computer Engineering</option>
				</select>
			</div>
			<div>
				Location:<span class="formSpan">*</span>
			</div>
			<div>
				<input type="text" name="location" id="location"
					placeholder="Location" onblur="validate('text', 'location')"
					required />
			</div>
			<div id="locationDiv" class="divValidation"></div>

			<div>COORDINATOR</div>
			<div>
				<select name="coordinatorId">
					<%
						HashMap<Integer, String> hashMapC = user.getUsers();
						Iterator itC = hashMapC.entrySet().iterator();
						while (itC.hasNext()) {
							Map.Entry pair = (Map.Entry) itC.next();
					%>
					<option value="<%=pair.getKey()%>">
						<%=pair.getValue()%>

					</option>

					<%
						itC.remove();

						}
					%>
				</select>

				<div>HIRING MANAGER</div>
				<div>
					<select name="hrManagerId">
						<%
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
					<div>RECRUITER1</div>

					<div>
						<select name="recruiterId1">
							<%
								HashMap<Integer, String> hashMapR1 = user.getUsers();
								Iterator itR1 = hashMapR1.entrySet().iterator();
								while (itR1.hasNext()) {
									Map.Entry pair = (Map.Entry) itR1.next();
							%>
							<option value="<%=pair.getKey()%>">
								<%=pair.getValue()%>

							</option>

							<%
								itR1.remove(); 

								}
							%>
						</select>
					</div>

					<div>VICE PRESIDENT</div>


					<div>
						<select name="vicePresidentId">
							<%
								HashMap<Integer, String> hashMapVP = user.getUsers();
								Iterator itV = hashMapVP.entrySet().iterator();
								while (itV.hasNext()) {
									Map.Entry pair = (Map.Entry) itV.next();
							%>
							<option value="<%=pair.getKey()%>">
								<%=pair.getValue()%>

							</option>

							<%
								itV.remove(); 

								}
							%>
						</select>
					</div>

					<div>RECRUITER2</div>
					<div>
						<select name="recruiterId2">
							<%
								HashMap<Integer, String> hashMapR2 = user.getUsers();
								Iterator itR2 = hashMapR2.entrySet().iterator();
								while (itR2.hasNext()) {
									Map.Entry pair = (Map.Entry) itR2.next();
							%>
							<option value="<%=pair.getKey()%>">
								<%=pair.getValue()%>

							</option>

							<%
								itR2.remove(); 

								}
							%>
						</select>
					</div>

					<div>Start Date:</div>
					<div>
						<input type="date" id="startDate" name="startDate"
							placeholder="start date" required />
					</div>

					<div>End Date:</div>
					<div>
						<input type="date" name="endDate" id="endDate"
							placeholder="end date"
							onblur="compareDate('startDate','endDate','endDateDiv', 'date')" required />
					</div>

					<div id="endDateDiv" class="divValidation"></div>

					<div class="submit1">
						<input type="reset" value="Reset"><input type="button"
							name="submit" class="submit1" value="Submit"
							onclick="onSubmitForm('createJob','JobReqServlet','/JobRequisition/Welcome.jsp','/JobRequisition/CreateJobRequisition.jsp')">

					</div>
		</form>
	</div>

</body>
</html>