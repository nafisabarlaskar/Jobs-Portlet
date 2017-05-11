var data = [];

var XMLHttpRequestObject = false;

// if true then creates an XMLHttpRequestObject for browser including IE
if (window.XMLHttpRequest) {
	XMLHttpRequestObject = new XMLHttpRequest();
} else if (window.ActiveXObject) {
	XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
}

//ajax call to servlet to get data from the server and call another function to render the UI of the page
function onloadPage(actionServlet, functionName){ 
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.onreadystatechange = function() {
			if (XMLHttpRequestObject.readyState == 4
					&& XMLHttpRequestObject.status == 200) {
				var response = JSON.parse(XMLHttpRequestObject.responseText);
				functionName(response);
			}
		};
		XMLHttpRequestObject.open("GET", actionServlet, true);
		XMLHttpRequestObject.send();
	}
}

//ajax call to servlet to submit forms
function onSubmitForm(formName, actionServlet, successUrl, failureUrl) { 
	var formCheck = validateForm(formName);
	var formData = new FormData(document.getElementById(formName));
	if (formName == "createCandidate") {
		formData.append("createUpdate", "1");
	} else {
		if (formName == "createEmployer") {
			formData.append("createUpdate", "3");
		}
	}
	if (formCheck) {
		if (XMLHttpRequestObject) {
			XMLHttpRequestObject.onreadystatechange = function() {
				if (XMLHttpRequestObject.readyState == 4
						&& XMLHttpRequestObject.status == 200) {
					var response = XMLHttpRequestObject.responseText;
					if (response == "Success")
						window.location.href = successUrl;
					else
						window.location.href = failureUrl;
				}
			};

			XMLHttpRequestObject.open("POST", actionServlet);
			XMLHttpRequestObject.send(formData);
		}
	}
}

//function to allow only numbers to be typed in the number input field in the form
function validateNumberField(){ 
	var i = 48;
	for(i = 48; i < 58; i++){
		if(event.keyCode == i){
			return true;
		}
	}
	return false;
}

//JobRequisition class is created and objects are initialized
function JobRequisition(data) { 
	this.title = data.title;
	this.description = data.description;
	this.department = data.department;
	this.location = data.location;
	this.endDate = data.endDate;
	this.startDate = data.startDate;
	this.jobReqId = data.jobReqId;
}

//renders the html for a JobRequisition object
JobRequisition.prototype.renderHtml = function(jobRequisitionArray) { 

	jobRequisitionArray.push('<div class="jobsDisplay">');
	jobRequisitionArray.push('<div class="jobsDisplayTitle">Job Title: </div><div>', this.title,
			'</div>');
	jobRequisitionArray.push('<div class="jobsDisplayTitle">Department: </div><div>',
			this.department, '</div>');
	jobRequisitionArray.push('<div class="jobsDisplayTitle">Location: </div><div>',
			this.location, '</div>');
	jobRequisitionArray.push('<div class="jobsDisplayTitle">Posting Date: </div><div>',
			this.startDate, '</div>');
	jobRequisitionArray.push('<div><a href="ApplyJobReq.jsp?jobReqId=', this.jobReqId,
			'">Apply</a></div>');
	jobRequisitionArray.push("</div>");
}

//JobApplication class is created and objects can be initialized
function JobApplication(data) { 
	this.email = data.email;
	this.firstName = data.firstName;
	this.lastName = data.lastName;
	this.country = data.country;
}

//renders the html of a JobApplication object
JobApplication.prototype.renderHtml = function(jobApplicationArray) { 
	jobApplicationArray.push('<div class="createJob">');
	jobApplicationArray.push('<form enctype="multipart/form-data" method="post" name="candidateApplication" id="candidateApplication" >');
	jobApplicationArray.push('<input type="hidden" name="jobReqId"  value="',
			jobReqId, '">');
	jobApplicationArray.push('<input type="hidden" name="candidateId"  value="',
			candidateId, '">');
	jobApplicationArray.push('<input type="hidden" name="jobReqParameter" value="1">');
	jobApplicationArray.push('<div class="jobsDisplayTitle">First Name:</div>');
	jobApplicationArray.push('<div><input type="text" name="firstName" value="', this.firstName,
			'" readonly/></div>');
	jobApplicationArray.push('<div class="jobsDisplayTitle">Last Name:</div>');
	jobApplicationArray.push('<div><input  type="text" name="lastName" value="', this.lastName,
			'" readonly/></div>');
	jobApplicationArray.push('<div class="jobsDisplayTitle">Email:</div>');
	jobApplicationArray.push('<div><input type="text" name="email" value="', this.email, '" readonly/></div>');
	jobApplicationArray.push('<div class="jobsDisplayTitle">Country:</div>');
	jobApplicationArray.push('<div><input type="text" name="country" value="', this.country,
			'" readonly/></div>');
	jobApplicationArray.push('<div class="jobsDisplayTitle">Phone Number:</div>');
	jobApplicationArray.push('<div><input type="text" id="phone" name="phone" onkeypress="return validateNumberField(event);" maxlength="10" required /></div>');
	jobApplicationArray.push('<div class="divValidation" id="phoneDiv"></div>');
	jobApplicationArray.push('<div class="jobsDisplayTitle">Skills:</div>');
	jobApplicationArray.push('<div><input type="text" name="skills" required /></div>');
	jobApplicationArray.push('<div class="jobsDisplayTitle">Resume:</div>');
	jobApplicationArray.push('<div><input type="file" name="resume" value="Browse" required /></div>');
	jobApplicationArray.push('<div><input class="submit1" type="button" value="Submit" onclick="onSubmitForm(\'candidateApplication\',\'JobApplicationServlet\',\'/JobRequisition/CandidateHome.jsp\',\'/JobRequisition/CandidateHome.jsp\')"></div>');
	jobApplicationArray.push('</form>');
	jobApplicationArray.push('</div>');
}

//creates JobRequisition objects and generates the UI for the CandidateHome.jsp page
function createCandidateHomeUI(data) { 
	var jobRequisitionObjects = [];
	for (var i = 0; i < data.length; i++) {
		jobRequisitionObjects.push(new JobRequisition(data[i]));
	}
	var jobRequisitionArray = [];
	for (var j = 0; j < jobRequisitionObjects.length; j++) {
		jobRequisitionObjects[j].renderHtml(jobRequisitionArray);
	}
	document.getElementById("container").innerHTML = jobRequisitionArray.join("");
}

//creates JobRequisition objects and generates the UI for the ApplyJobReq.jsp page
function createJobApplicationUI(data) { 
	var jobApplicationObjects = [];
	for (var i = 0; i < data.length; i++) {
		jobApplicationObjects.push(new JobApplication(data[i]));
	}
	var jobApplicationArray = [];
	for (var j = 0; j < jobApplicationObjects.length; j++) {
		jobApplicationObjects[j].renderHtml(jobApplicationArray);
	}
	document.getElementById("container").innerHTML = jobApplicationArray.join("");
}

//creates a Form object and initializes the properties
function Form(name, id, method) { 
	this.name = name;
	this.id = id;
	this.method = method;
}
Form.prototype.renderHtml = function(formArray) { //renders a form element
	formArray.push('<form method="', this.method, '" name="', this.name,
			'" id="', this.id, '">');
}

//creates a Input object and initializes the properties
function Input(type, id, name, value, event, functionName) { 
	this.type = type;
	this.id = id;
	this.name = name;
	this.event = event;
	this.functionName = functionName;
	this.value = value;
}

//sets the properties needed while submitting the form
Input.prototype.setSubmitParameters = function(formName, actionServlet,
		successUrl, failureUrl) { 
	this.formName = formName;
	this.actionServlet = actionServlet;
	this.successUrl = successUrl;
	this.failureUrl = failureUrl;
}

//generates the html of input element of a form
Input.prototype.renderHtml = function(inputArray) { 

	switch (this.type) {
	case "hidden":
		inputArray.push('<div><input type="', this.type, '" name="', this.name, '" value="', this.value, '" /></div>');
		break;
	case "button":
		inputArray.push('<div><input class="submit1" type="', this.type,
				'" id="', this.id, '" name="', this.name, '" value="',
				this.value, '" ', this.event, '="', this.functionName, '(\'',
				this.formName, '\',\' ', this.actionServlet, '\',\'',
				this.successUrl, '\',\'', this.failureUrl, '\')" /></div>');
		break;
	default:
		inputArray.push('<div><input type="', this.type, '" id="', this.id,
				'" name="', this.name, '" ', this.event, '="', this.functionName,
				'(\'', this.type, '\', \'', this.id, '\')" /></div>');
		break;
	}
}

//creates a DivElement object and initializes the properties
function DivElement(className, message, type, id) { 
	this.className = className;
	this.message = message;
	this.type = type;
	this.id = id;
}

//generates the div element in the html
DivElement.prototype.renderHtml = function(divArray) { 
	if (this.type == "span") {
		divArray.push('<div>', this.message, ':<span class="', this.className,
				'">*</span></div>');
	} else {
		divArray.push('<div id="', this.id, '" class="', this.className,
				'"></div>');
	}
}

//CandidateRegister object is created for rendering the CandidateRegister.jsp page
function CandidateRegister() { 

}

//creates objects of Input, DivElement, Form class and pushes into an array
CandidateRegister.prototype.setupUIElements = function(registerUI) { 

	registerUI.push(new Form("createCandidate", "createCandidate", "post"));
	registerUI.push(new Input("hidden", "createUpdate", "createUpdate", "1",
			"", ""));
	registerUI.push(new DivElement("formSpan", "Enter your username: ", "span",
			""));
	registerUI.push(new Input("text", "userName", "userName", "", "", ""));
	registerUI.push(new DivElement("divValidation", "", "", "userNameDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your firstname: ",
			"span", ""));
	registerUI.push(new Input("text", "firstName", "firstName", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "firstNameDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your lastname: ", "span",
			""));
	registerUI.push(new Input("text", "lastName", "lastName", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "lastNameDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your phone number: ",
			"span", ""));
	registerUI.push(new Input("text", "phone", "phone", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "phoneDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your country name: ",
			"span", ""));
	registerUI.push(new Input("text", "country", "country", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "countryDiv"));
	registerUI.push(new DivElement("formSpan", "Create your password: ",
			"span", ""));
	registerUI.push(new Input("password", "password", "password", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "passwordDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your email address: ",
			"span", ""));
	registerUI.push(new Input("text", "email", "email", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "emailDiv"));
	var submit = new Input("button", "", "", "Submit", "onclick",
			"onSubmitForm");
	submit.setSubmitParameters("createCandidate", "AccountServlet",
			"/JobRequisition/CandidateHome.jsp",
			"/JobRequisition/CandidateRegister.jsp");
	registerUI.push(submit);
}

//renders the html of all the objects stored in the array
CandidateRegister.prototype.renderHtml = function(registerUI) { 

	var candidateArray = [];
	candidateArray.push('<p class="paragraph">Candidate Registration</p>');
	candidateArray.push('<div class="createJob">');
	for (i = 0; i < registerUI.length; i++) {
		registerUI[i].renderHtml(candidateArray);
	}
	candidateArray.push("</form>");
	candidateArray.push("</div>");

	document.getElementById("candidateRegister").innerHTML = candidateArray
			.join("");
}

//creates a CandidateRegister object and calls its renderHtml function to render the CandidateRegister.jsp page
function onloadCandidateRegistration() { 
	var candidateRegister = new CandidateRegister();
	var registerUI = [];
	candidateRegister.setupUIElements(registerUI);
	candidateRegister.renderHtml(registerUI);
}

function EmployerRegister() { //EmployerRegister object is created for rendering the EmployerRegister.jsp page

}
EmployerRegister.prototype.setupUIElements = function(registerUI) {//creates objects of Input, DivElement, Form class and pushes into an array

	registerUI.push(new Form("createEmployer", "createEmployer", "post"));
	registerUI.push(new Input("hidden", "createUpdate", "createUpdate", "3",
			"", ""));
	registerUI.push(new DivElement("formSpan", "Enter your username: ", "span",
			""));
	registerUI.push(new Input("text", "userName", "userName", "", "", ""));
	registerUI.push(new DivElement("divValidation", "", "", "userNameDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your firstname: ",
			"span", ""));
	registerUI.push(new Input("text", "firstName", "firstName", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "firstNameDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your lastname: ", "span",
			""));
	registerUI.push(new Input("text", "lastName", "lastName", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "lastNameDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your phone number: ",
			"span", ""));
	registerUI.push(new Input("text", "phone", "phone", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "phoneDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your country name: ",
			"span", ""));
	registerUI.push(new Input("text", "country", "country", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "countryDiv"));
	registerUI.push(new DivElement("formSpan", "Create your password: ",
			"span", ""));
	registerUI.push(new Input("password", "password", "password", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "passwordDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your email address: ",
			"span", ""));
	registerUI.push(new Input("text", "email", "email", "", "onblur",
			"validate"));
	registerUI.push(new DivElement("divValidation", "", "", "emailDiv"));
	registerUI.push(new DivElement("formSpan", "Enter your company name: ",
			"span", ""));
	registerUI.push(new Input("text", "companyName", "companyName", "", "",
			""));
	var submit = new Input("button", "", "", "Submit", "onclick",
			"onSubmitForm");
	submit.setSubmitParameters("createEmployer", "AccountServlet",
			"/JobRequisition/Welcome.jsp",
			"/JobRequisition/AdminRegister.jsp");
	registerUI.push(submit);
}
EmployerRegister.prototype.renderHtml = function(registerUI) {//renders the html of all the objects stored in the array

	var employerArray = [];
	employerArray.push('<p class="paragraph">Employer Registration</p>');
	employerArray.push('<div class="createJob">');
	for (i = 0; i < registerUI.length; i++) {
		registerUI[i].renderHtml(employerArray);
	}
	employerArray.push("</form>");
	employerArray.push("</div>");

	document.getElementById("adminRegister").innerHTML = employerArray
			.join("");
}

//creates a EmployerRegister object and calls its renderHtml function to render the EmployerRegister.jsp page
function onloadEmployerRegistration() { 
	var employerRegister = new EmployerRegister();
	var registerUI = [];
	employerRegister.setupUIElements(registerUI);
	employerRegister.renderHtml(registerUI);
}

