var currentPage = 0;
var totalCourses = getData("/api/courses/total");
var courseTypes = getData("/api/course-types");

$(document).ready(function () {
    showPage();
    $('.btn-add-course').on('click', openModalAddCourse); //Click Thêm khóa học
    $('#add-new-course').on('click', addNewCourse); //submit thêm khóa học
    $('.btn-update').click(function () {
        updateCourse($(this).parents('tr'));
    });
    // $('button .btn-detele').on('click', addNewCourse);
});

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
        error: function (e) {
            arrayData = e.responseText;
            // alert("Error");
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
            res = response;
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
            res = response;
        }
    });
    return res;
}

function openModalAddCourse() {
    $('.modal-add-course').addClass("show");
    loadCourseTypeList();
}

function updateCourse(tr){
    $('.modal-update-course').addClass("show");
    $('#updateCourseName').val(tr.find('td').eq(1).text());
    courseId = tr.attr('course-id');
    courseTypeId = tr.attr('course-type-id');
    $('#updateCourseType option').remove();
    $('#updateCourseType').append("<option>" + tr.find('td').eq(2).text() + "</option>");
    data = {
        courseName: "string",
        typeId: courseTypeId
    }
    $('#update-course').click(function () {
        putData("/api/courses/" + courseId, JSON.stringify(data));
        alert("Cập nhật thành công");
        $('.modal-update-course').removeClass("show");
        showPage();
    });
}

function resetModalAddCourse() {
    $('#courseName').val("");
    $('#courseType').prop('selectedIndex', 0);
}

function addNewCourse() {
    let course_name_input = $('#courseName').val();
    let course_type_input = $('#courseType option:selected').val();
    let newCourse = {
        courseName: course_name_input,
        typeId: course_type_input
    }
    var result = postData("/api/courses", JSON.stringify(newCourse));
    alert("Thêm khóa học thành công");
    resetModalAddCourse();
}

function loadCourseTypeList(){
    $('#courseType').empty();
    courseTypes.forEach(function (item, index ) {
        $('#courseType').append("<option value='" + item.id + "'>" + item.name + "</option>");
    });
}

function showPage() {
    showDataCourses(getDataCourses(currentPage)); //Page 0

    $('a').click(function () {
        switch ($(this).attr('href')) {
            case "#first":
                if(currentPage > 0){
                    currentPage = 0;
                    showDataCourses(getDataCourses(currentPage)); //Page 0
                }
                break;
            case "#prev":
                if(currentPage-1 >= 0){
                    currentPage = currentPage - 1;
                    showDataCourses(getDataCourses(currentPage));
                }
                break;
            case "#next":
                currentPage = currentPage + 1;
                if(currentPage < Math.ceil(totalCourses/10)){
                    showDataCourses(getDataCourses(currentPage));
                }
            case "#last":
                lastPage = Math.ceil(totalCourses/10)-1;
                if(currentPage < lastPage){
                    currentPage = lastPage;
                    showDataCourses(getDataCourses(currentPage));
                }
                break;
        }
    });
}

function getDataCourses(pageNo) {
    var api_courses = "/api/courses?pageNo=" + pageNo + "";
    var courses = getData(api_courses);
    return courses;
}

function showDataCourses(courses) {
    rows = courses.length;
    first = currentPage*10+(rows == 0 ? 0 : 1);
    last = currentPage*10+rows;
    $('#firstResult').text(first);
    $('#lastResult').text(last);
    $('#totalResult').text(totalCourses);

    $('.list-courses tbody').empty();
    $.each(courses, function (index, item) {
        var rowhtml = $('<tr course-id="' + item.id + '" course-type-id="' + item.typeId + '"></tr>');
        rowhtml.append('<td>' + (index+1) + '</td>');
        rowhtml.append('<td>' + item.courseName + '</td>');
        rowhtml.append('<td>' + getNameCourseType(item.typeId) + '</td>');
        rowhtml.append('<td>' + item.createdDate + '</td>');
        rowhtml.append('<td>' + item.updatedDate + '</td>');
        rowhtml.append('<td><input type="button" value="Sửa" class="btn-update"></input> | ' +
            '<input type="button" class="btn-delete" value="Xóa"></a></td>');

        rowhtml.append('<td class=""><a href="/admin/courses/' + item.id + '/courseDetail">Chi tiết</a></td>');
        $('.list-courses tbody').append(rowhtml);
    })
}

function getNameCourseType(typeId) {
    var name = this.getData("/api/course-types/" + typeId).name;
    return name;
}