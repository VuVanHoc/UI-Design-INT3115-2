$(document).ready(function () {
	loadDataToUI();

    //Search
    $("#txtSearch").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

	//add new user
	$('#btn-register').click(function () {
		var fullName = $('#txt-fullname').val();
		var username = $('#txt-username').val();
		var phone = $('#txt-phone').val();
		var pass = $('#password-add').val();
		var cfpw = $('#cfpw-add').val();
		var roleId = $('#roleName').val();

		console.log("Name" + fullName);
		var object = {
			roleId: roleId,
			fullName: fullName,
			username: username,
			password: pass,
			confirmPassword: cfpw,
			phone: phone
		};
		$.ajax({
			method: "POST",
			url: "http://localhost:8080/api/users",
			data: JSON.stringify(object),
			characterData: "utf-8",
			contentType: "application/json; charset=UTF-8",
			dataType: "json",
			success: function () {
				alert("Đăng ký thành công!");
				closeModel();
				loadDataToUI();

			}
		})
	});


//	update
	$('#btn-update').click(function () {
		var fullName = $('#txt-fullname-update').val();
		var username = $('#txt-username-update').val();
		var phone = $('#txt-phone-update').val();
		var roleId = $('#roleName-update').val();
		var userId = $('#id-user').val();
		var object = {
			roleId: roleId,
			fullName: fullName,
			email: username,
			password: "123",
			confirmPassword: "123",
			phone: phone
		};
		$.ajax({
			method: "PUT",
			url: "http://localhost:8080/api/users/" + userId,
			data: JSON.stringify(object),
			characterData: "utf-8",
			contentType: "application/json; charset=UTF-8",
			dataType: "json",
			success: function () {
				alert("Cập nhật thành công!");
				closeModel();
				loadDataToUI();

			}
		})
	})

});

function closeModel() {
	$('.modal').removeClass("show");
}

function getDataFormServer() {
	var arrayData = [];
	$.ajax({
		method: "GET",
		url: "/api/users",
		async: false,
		dataType: "json",
		success: function (res) {
			arrayData = res.data;
		},
		error: function () {
			alert("Error")
		}
	});
	return arrayData;
}

function loadDataToUI() {
	let data = this.getDataFormServer();
	console.log(data);

	var fields = $('.list-users th[fieldName]');
	$('.list-users tbody').empty();
	$.each(data, function (index, item) {
		var rowhtml = $('<tr id="' + item.id + '"></tr>');
		$.each(fields, function (fieldindex, fielditem) {
			var fieldname = fielditem.getAttribute('fieldName');
			var value = item[fieldname];
			if (fieldname) {
				rowhtml.append('<td class="' + fieldname + '">' + value + '</td>');
			}

		});
		rowhtml.append('<td><button class="btn-table update" onclick="initFormUpdate(' + item.id + ')">Sửa</button>' +
			'<button class="btn-table cancel" onclick="removeUser(' + item.id + ')">Xoá</button></td>');
		$('.list-users tbody').append(rowhtml);

	})
}

function removeUser(userId) {
	if (confirm("Bạn muốn xoá tài khoản này?")) {
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/api/users/" + userId,
			success: function () {
				alert("Xoa thanh cong");
				loadDataToUI();
			}
		})
	}
}

function initFormUpdate(userId) {
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/api/users/" + userId,
		success: function (res) {
			var user = res.data;
			console.log(user.fullName);
			$('#txt-fullname-update').val(user.fullName);
			$('#txt-username-update').val(user.username);
			$('#txt-phone-update').val(user.phone);
			$('#roleName-update').val(user.roleId);
			$('#id-user').val(userId);
		}

	})
}
