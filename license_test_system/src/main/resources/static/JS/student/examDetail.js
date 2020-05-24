const LOCATION_API = "http://localhost:8080";
var courseId = $('#courseId').val();
var current_course = getCourse(courseId);
// var current_coursetype = getCourseType(current_course.typeId);
var examSchedule = this.getData(LOCATION_API + "/api/courses/" + courseId + "/exam-schedule");
var start_time = new Date(examSchedule.startTime);
var end_time = new Date(examSchedule.endTime);

$(document).ready(function () {
    console.log(examSchedule);
    loadInfo();
});

function getData(url) {
    var fakeData = [];
    $.ajax({
        method: "GET",
        url: url,
        async: false,
        dataType: "json",
        success: function (res) {
            fakeData = res.data; // gán data
        },
        error: function (xhr) {
            fakeData = JSON.parse(xhr.responseText);
            alert(fakeData.error);
            window.history.back();
        }
    });
    return fakeData;
}

function loadInfo() {
    $('#courseName').text(current_course.courseName);
    $('#startTime').text(start_time.getHours() + " giờ " + start_time.getMinutes() + " phút, ngày " + start_time.getDate() + " tháng " + (start_time.getMonth()+1) + " năm " + start_time.getFullYear());
    $('#endTime').text(end_time.getHours() + " giờ " + end_time.getMinutes() + " phút, ngày " + end_time.getDate() + " tháng " + (end_time.getMonth()+1) + " năm " + end_time.getFullYear());
    $('#examTime').text((end_time-start_time)/60/1000 + " phút");
    if($('#note').val() != null) $('#note').text(examSchedule.note);
    // $('#examId').val(examSchedule.id);
    // console.log("Exam ID: " + examSchedule.id);
}

function loadExam() {
    // if(Date.now() >= start_time.getTime() && Date.now() <= end_time.getTime()){
        var url_exam = LOCATION_API.concat("/student/courses/", $('#courseId').val(), "/exam");
        window.location.href = url_exam;
    // }else {
    //     if(Date.now() < start_time.getTime()){
    //         alert("Chưa đến giờ thi");
    //     }
    //     if(Date.now() > end_time.getTime()){
    //         alert("Đã hết giờ thi");
    //     }
    // }

}

// function getCourseType(id) {
//     var courseType = this.getData(LOCATION_API + "/api/course-types/" + id);
//     return courseType;
// }

function getCourse(id) {
    var course = this.getData(LOCATION_API + "/api/courses/" + id);
    return course;
}