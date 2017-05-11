var XMLHttpRequestObject = false;

if (window.XMLHttpRequest) {
	XMLHttpRequestObject = new XMLHttpRequest();
} else if (window.ActiveXObject) {
	XMLHttpRequestObject = new ActiveXObject("Microsoft.XMLHTTP");
}

function loadApplicant() { //load the applications of candidates for the given job requisition

	var response = null;
	var results;
	var x = document.getElementById("jobReqId").value;
	if (XMLHttpRequestObject) {
		XMLHttpRequestObject.onreadystatechange = function() {
			if (XMLHttpRequestObject.readyState == 4
					&& XMLHttpRequestObject.status == 200) {

				response = JSON.parse(XMLHttpRequestObject.responseText);

				var tbl = [];
				tbl.push('<table class="viewApplicants">');
				tbl.push('<tr class="viewApplicantsHeader">');
				tbl.push('<td >ID</td>');
				tbl.push('<td >FIRSTNAME</td>');
				tbl.push('<td >LASTNAME</td>');
				tbl.push('<td >PHONE</td>');
				tbl.push('<td >EMAIL</td>');
				tbl.push('<td >COUNTRY</td>');
				tbl.push('<td >SKILLS</td>');
				tbl.push('</tr>');

				for (var i = 0; i < response.length; i++) {
					tbl.push('<tr class="viewApplicantsList">');
					tbl.push('<td >' + response[i].CANDIDATE_ID + '</td>');
					tbl.push('<td >' + response[i].FIRSTNAME + '</td>');
					tbl.push('<td >' + response[i].LASTNAME + '</td>');
					tbl.push('<td >' + response[i].PHONE + '</td>');
					tbl.push('<td >' + response[i].EMAIL + '</td>');
					tbl.push('<td >' + response[i].COUNTRY + '</td>');
					tbl.push('<td >' + response[i].SKILLS + '</td>');
					tbl.push('</tr>');
				}
				tbl.push('</table>');
				document.getElementById("recordsTable").innerHTML = tbl
						.join("");

			}
		};
		XMLHttpRequestObject.open("POST", "JobApplicationServlet?jobReqId=" + x
				+ "&jobReqParameter=2", true);
		XMLHttpRequestObject.send();
	}
}
