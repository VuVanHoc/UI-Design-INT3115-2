$(document).ready(function () {
	$.ajax({
		method : "POST",
		url : "/authenticate",
		success : function (res) {
			alert("Scuess");
		},
		error : function () {
			alert("Fail");
		}

	});
});