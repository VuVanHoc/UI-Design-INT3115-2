const LOCATION_API = "http://localhost:8080";
const username = localStorage.getItem("username");
const userId = getData("/api/users/username/" + username).id;

$(document).ready(function () {
    schedule();
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
            // fakeData = JSON.parse(xhr.responseText);
            // alert(fakeData.message);
        }
    });
    return fakeData;
}

function schedule() {
    var courses_of_me = getData(LOCATION_API + '/api/users/' + userId + '/courses');
    courses_of_me.forEach(function (item) {
        var courseId = item.id;
        var exam = getExam(courseId);
        // console.log(Date.parse('11/13/2019 7:00'));
        // console.log(Date.parse('11/13/2019 7:20'));
        // if(exam != 0){
        //     if(Date.parse(exam.startTime) > (Date.now())){
        //         $('.schedule').append("<p><a href='" + (LOCATION_API + "/student/courses") + "'>Khóa học " + item.courseName + "</a> có kỳ thi sắp diễn ra vào <a href='" + (LOCATION_API + "/student/courses/" + courseId + "/examDetail")+ "'>" + formatTime(exam.startTime) + "</a></p>");
        //     }else{
        //         $('.schedule').append("<p>Khóa học " + item.courseName + " đã kết </p>");
        //     }
        // }
    });

}

function formatTime(time) {
    var timeDate = new Date(time);
    return timeDate.getHours() + ":" + format_two_digits(timeDate.getMinutes()) + " "
        + format_two_digits(timeDate.getDate()) + "/" + format_two_digits(timeDate.getMonth()) + "/" + timeDate.getFullYear();
}

function format_two_digits(n) {
    return n < 10 ? '0' + n : n;
}

function getExam(courseId) {
    var exam = getData(LOCATION_API + "/api/courses/" + courseId + "/exam-schedule");
    return exam;
}