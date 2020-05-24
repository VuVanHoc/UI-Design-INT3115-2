var courseId = Number(pathArray = window.location.pathname.split('/')[3]);
var responseCreateExam;
var examId;
var existedExam;

$(document).ready(function () {
	loadDataToUI();

	$('.btn-back').on("click", function () {
		window.history.back();
	});
});



function loadDataToUI(){
	loadListUser();
	addQuestionToExam(); //Khi chọn câu hỏi trong ngân hàng, câu hỏi thêm vào bảng câu hỏi đã chọn (action cột 2)
	removeQuestionFromExam();
	//Load list user in course
	existedExam = getSchedule("/api/courses/" + courseId + "/users");
	// console.log(existedExam);
	if(existedExam){
		loadExistedUsers(existedExam);
		submitWithAction("UPDATE");
	}else{
		submitWithAction("CREATE");
	}

}

//Khi chọn 1 câu hỏi ở cột 2 => Thêm câu hỏi vào cột 3
function addQuestionToExam() {
	$('#table1 input[type="checkbox"]').click(function () {
		//Nếu click checkbox "chọn tất cả" ở đầu
		if($(this).attr('id') === "checkbox-all-1"){
			if($('#checkbox-all-1').prop('checked') === true){
				$('#table1 input[type="checkbox"]').prop('checked', true);
			}else{
				$('#table1 input[type="checkbox"]').prop('checked', false);
			}
		}else{
			let newRow = $('<tr></tr>');
			let innerRow = $(this).parents('tr')[0].innerHTML;
			if($(this).prop('checked') === true){ // Nếu checked vào 1 câu hỏi
				newRow.append(innerRow);
				$('#table2 tbody').append(newRow);
				$('input[name="' + $(this).attr('name') + '"]').prop('checked', true);
				$('#number-question-added').text("(" + $('#table2 tbody>tr').length + ")"); //Số câu hỏi đã chọn
			}else{ // unchecked
				$('#table2 input[name="' + $(this).attr('name') + '"]').parents('tr').remove();
				$('input[name="' + $(this).attr('name') + '"]').prop('checked', false);
				$('#number-question-added').text("(" + $('#table2 tbody>tr').length + ")"); //Số câu hỏi đã chọn
			}
		}
	});
}

//Xóa câu hỏi khỏi cột 3
function removeQuestionFromExam() {
	$('#table2').on("click", "input", function () {
		if($(this).prop('checked') === false){ // Nếu unchecked vào 1 câu hỏi ở cột 3
			$(this).parents('tr').remove();
			$('input[name="' + $(this).attr('name') + '"]').prop('checked', false);
			$('#number-question-added').text("(" + $('#table2 tbody>tr').length + ")"); //Số câu hỏi đã chọn
		}
	});
}

function loadListUser() {
	// let pageNo = 0;
	// loadMore(pageNo);
	// $('#table1').on('scroll', function() {
	//     if($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
	//         //Load more
	//         pageNo = pageNo + 1;
	//         loadMore(pageNo);
	//         if(loadMore(pageNo) == "Không có câu hỏi nào"){
	//             alert("Đã hết câu hỏi");
	//             $('#table1').off('scroll');
	//         }
	//     }
	// });
	data = getData("/api/users?pageNo=0&pageSize=100");
	data.forEach(function (item, index) {
		row = $('<tr> </tr>');
		row.append('<td>' + (index+1) + '</td>');

		row.append('<td>' + (item.fullName) + '</td>');
		row.append('<td>' + (item.email) + '</td>');
		row.append('<td><input type="checkbox" name="cb_' + (index+1) + '">' +
			'<input type="hidden" class="questionIdClass" value="' + item.id +'"></td>');
		$('#table1 tbody').append(row);
	})
}

function loadMore(pageNo) {
	// data = getData("/api/questions?pageNo=" + pageNo + "&pageSize=10");
	if(data === "Không có câu hỏi nào"){
		return data;
	}
	data.forEach(function (item, index) {
		row = $('<tr> </tr>');
		row.append('<td>' + (pageNo*10 + index+1) + '</td>');

		row.append('<td>' + (item.fullName) + '</td>');
		row.append('<td>' + (item.email) + '</td>');
		row.append('<td><input type="checkbox" name="cb_' + (index+1) + '">' +
			'<input type="hidden" class="questionIdClass" value="' + item.id +'"></td>');
		$('#table1 tbody').append(row);
	})
}

function loadExistedUsers(existedExam) {
	examId = existedExam.id;

	var array_question_id = getData("/api/courses/" + courseId + "/users");
	console.log(array_question_id);
	for (let i = 0; i < array_question_id.length; i++) {
		$('#table1 input[value="' + array_question_id[i] + '"]').prev().click();
	}
}

function submitWithAction(action) {
	$('.btn-save').click(function () {


		let usersData = new Array();
		$('#tb2 .questionIdClass').each(function (index, item) {
			usersData.push(Number($(item).val()));
		});
		let userIdDatas = {
			userIds: usersData
		};

		if(action === "UPDATE"){
			setTimeout(function(){
				let resultExam = postData("/api/courses/" + courseId +"/users", JSON.stringify(userIdDatas));
				alert("Đã ghi nhận học viên mới!");
			}, 1000);
		}else if(action === "CREATE"){
			setTimeout(function(){
				let resultExam = postData("/api/courses/" + courseId +"/users", JSON.stringify(userIdDatas));
				alert("Đã ghi nhận học viên mới!");
			}, 1000);
		}
	});
}

function getSchedule(url) {
	var arrayData = [];
	$.ajax({
		method: "GET",
		url: url,
		async: false,
		dataType: "json",
		success: function (res) {
			arrayData = res.data;
		},
		error: function () {
			arrayData = null;
		}
	});
	return arrayData;
}

function getData(url) {
	var arrayData = [];
	$.ajax({
		method: "GET",
		url: url,
		async: false,
		dataType: "json",
		success: function (res) {
			arrayData = res.data;
		},
		error: function () {
			// arrayData = null;
		}
	});
	return arrayData;
}

function postData(url, jsonData) {
	var res = [];
	$.ajax({
		url: url,
		method: "POST",
		data: jsonData,
		dataType: "json",
		characterData: "utf-8",
		contentType: "application/json; charset=UTF-8",
		success : function (response) {
			res = response.data;
		}
	});
	return res;
}

function putData(url, jsonData) {
	var res = [];
	$.ajax({
		url: url,
		method: "PUT",
		data: jsonData,
		dataType: "json",
		characterData: "utf-8",
		contentType: "application/json; charset=UTF-8",
		success : function (response) {
			res = response.data;
		}
	});
	return res;
}

function convertDateTime(datetime) {
	var dateString = datetime,
		dateTimeParts = dateString.split(' '),
		timeParts = dateTimeParts[1].split(':'),
		dateParts = dateTimeParts[0].split('-');
	let date = new Date(dateParts[2], parseInt(dateParts[0], 10) - 1, dateParts[1], (timeParts[0]), timeParts[1]);
	var userTimezoneOffset = date.getTimezoneOffset() * 60000;
	return new Date(date.getTime() - userTimezoneOffset);
}