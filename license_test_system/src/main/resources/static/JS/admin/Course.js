$(document).ready(function () {
	loadDataToUI();

	$('#btn-create-course').click(function () {
		$('.modal-add-course').addClass("show");
        let courseTypes = getCoursesList();
        $('#type-id').empty();
        courseTypes.forEach(function (item, index ) {
            $('#type-id').append("<option value='" + item.id + "'> Hạng " + item.name + "</option>");
        });
	});

	$('#btn-add-new-course').click(addNewCourse);

	$('.update').click(function () {
		$('.modal-update-course').addClass("show");
        let courseTypes = getCoursesList();
        $('#type-id-update').empty();
        courseTypes.forEach(function (item, index ) {
            $('#type-id-update').append("<option value='" + item.id + "'> Hạng " + item.name + "</option>");
        });
	})

	$('#btn-update-course').click(updateCouseInfo);

    //Search
    $("#txtSearch").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

function addNewCourse() {
	let courseName = $('#course-name-add').val();
	let typeId = $('#type-id').val();

	let object = {
		courseName: courseName,
		typeId: typeId,

		createdTime: "",
		description: "",
		id: 0,
		index: 0,
		minScore: 0,
		total: 0,
		typeName: ""
	};

	$.ajax({
		url: "http://localhost:8080/api/courses",
		method: "POST",
		data: JSON.stringify(object),
		dataType: "json",
		characterData: "utf-8",
		contentType: "application/json; charset=UTF-8",
		success : function () {
			alert("Tạo khoá học thành công!");
			closeModel();
			loadDataToUI();
		}
	})
}

function closeModel() {
	$('.modal').removeClass("show");
}

function getDataFormServer() {
	var arrayData = [];
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/api/courses",
		async: false,
		dataType: "json",
		success: function (res) {
			arrayData = res.data;
			// alert("Sucess");
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
		var rowhtml = $('<tr id="'+item.id+ '"></tr>');
		$.each(fields, function (fieldindex, fielditem) {
			var fieldname = fielditem.getAttribute('fieldName');
			var value = item[fieldname];

			if (fieldname) {
				rowhtml.append('<td class="' + fieldname + '">' + value + '</td>');
			}

		});
		rowhtml.append('<td><a href="http://localhost:8080/admin/courses/' + item.id + '/exam"><span class="fa fa-address-book"></span></a></td>')
		rowhtml.append('<td><a class="a-see-detail" href="http://localhost:8080/admin/courses/'+item.id +'/list-student">Xem chi tiết</a></td>')
		rowhtml.append('<td><button class="btn-table update" style="width: 45px" onclick="initFormUpdateCourse('+item.id+')">Sửa</button>' +
			'<button class="btn-table cancel" style="width: 45px" onclick="removeCourse('+item.id+')">Xoá</button></td>');
		$('.list-users tbody').append(rowhtml);

	})
}

function removeCourse(id) {

	if(confirm("Bạn muốn xoá lớp học này?")){
		$.ajax({
			method: "DELETE",
			url: "http://localhost:8080/api/courses/"+id,
			success : function () {
				loadDataToUI();
			}
		})
	} else {
		loadDataToUI();

	}

}

function initFormUpdateCourse(id) {
	$.ajax({
		method: "GET",
		url: "http://localhost:8080/api/courses/" + id,
		success: function (res) {
			let course = res.data;
			$('#course-name-update').val(course.courseName);
			$('#type-id-update').val(course.typeId);
			$('#course-id').val(id);
		}

	})
}

function updateCouseInfo(id) {
	let courseName = $('#course-name-update').val();
	let typeId = $('#type-id-update').val();
	let courseId = $('#course-id').val();
	let object = {
		courseName: courseName,
		typeId: typeId,

		createdTime: "",
		description: "",
		id: 0,
		index: 0,
		minScore: 0,
		total: 0,
		typeName: ""
	};
	$.ajax({
		url: "http://localhost:8080/api/courses/"+courseId,
		method: "PUT",
		data: JSON.stringify(object),
		dataType: "json",
		characterData: "utf-8",
		contentType: "application/json; charset=UTF-8",
		success : function () {
			alert("Cập nhật khoá học thành công!");
			closeModel();
			loadDataToUI();
		}
	})

}

function getCoursesList() {
	var arrayData = [];
        $.ajax({
            method: "GET",
            url: "/api/course-types",
            async: false,
            dataType: "json",
            success: function (res) {
                arrayData = res.data;
            },
            error: function (e) {
				console.log("Lỗi load danh sách các hạng bằng lái");
            }
        });
        return arrayData;
}