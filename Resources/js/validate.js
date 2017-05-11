var flag = 1;
var check = false;
var divState = {};

// display one div while 
function showHideElementsByClass(id, class1) {
	if (document.getElementById(id)) {
		var divid = document.getElementById(id);
		var divs = document.getElementsByClassName(class1);
		for (var i = 0; i < divs.length; i++) {
			divs[i].style.display = "none";
		}
		divid.style.display = "block";
	}
	return false;
}
//display one div and hide the other
function showHide(id1, id2) { 
	var div1 = document.getElementById(id1);
	if (id2 == null) {
		if (div1.style.display !== "none") {
			div1.style.display = "none";
		} else
			div1.style.display = "block";
	} else {
		var div2 = document.getElementById(id2);
		if (div1.style.display !== "none") {
			div1.style.display = "none";
			div2.style.display = "block";

		} else {
			div1.style.display = "block";
			div2.style.display = "none";
		}
	}
}

//set of regular expressions for validating user input in a form
var fieldInputRegex = { 
	text : /^[A-Za-z]+$/,
	textarea : /^[A-Za-z]+$/,
	email : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
	phone : /^\d+$/,
	password : /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/
};

//set of error messages to display when the user types wrong input
var errorInputMessage = { 
	email : "Not a valid e-mail address",
	text : "Text Field can only have letters",
	phone : "Field must contain only digits",
	password : "Password must have 6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter",
	date : "Start Date should be less than End date",
	emptyField : "Field cannot be empty"
};

//test the user input against the corresponding valid regular expression
function validateInput(inputValue, type) { 
	return (fieldInputRegex[type].test(inputValue));
}
//display error message in a div element when validation fails
function displayErrorMessage(type, errorFieldId, check) { 
	var errorElement = document.getElementById(errorFieldId);
	if (check){
		errorElement.innerHTML = "";
	}
	else{
		errorElement.innerHTML = errorInputMessage[type];
	}
}

//validate the corresponding field when it is called onblur
function validate(type, id) { 
	var inputValue = document.getElementById(id).value;
	var errorFieldId = id + "" + "Div";
	if (id == "email") {
		type="email";
	}
	else if (id=="phone") {
		type="phone";
	}
	displayErrorMessage(type, errorFieldId, validateInput(inputValue, type));
}
// compare the start date and end date and display corresponding error message
function compareDate(startDateId, endDateId, errorFieldId, inputType) {
	var sDate = document.getElementById(startDateId).value;
	var eDate = document.getElementById(endDateId).value;
	var startDate = new Date(sDate);
	var endDate = new Date(eDate);
	var check = startDate < endDate;
	displayErrorMessage(inputType, errorFieldId, check);
	return check;
}

//validate all the elements in a form
function validateForm(formName) { 
	var formIds = document.forms[formName].elements;
	var canSubmit = true;
	for (var i = 0; i < formIds.length; i++) {
		var errorFieldId = formIds[i].id + "Div";
		var inputValue = formIds[i].value;
		var inputType = formIds[i].type;
		var inputId = formIds[i].id;
		if (inputValue == null) {
			canSubmit = false;
			displayErrorMessage(emptyField, errorFieldId, canSubmit);
			break;
		} else {
			if (inputId == "startDate") {
				var startDateId = inputId;
			} else {
				switch (inputId) {
				case "email":
				case "phone":
					canSubmit = validateInput(inputValue, inputId);
					displayErrorMessage(inputId, errorFieldId, canSubmit);
					break;
				case "endDate":
					canSubmit = compareDate(startDateId, inputId, errorFieldId, inputType);
					break;
				case "":
					break;
				default:
					canSubmit = validateInput(inputValue, inputType);
					displayErrorMessage(inputType, errorFieldId, canSubmit);
					break;
				}
			}
		}
		if (!canSubmit) {
			break;
		}
	}
	return canSubmit;
}
