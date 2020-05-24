const LOCATION_API = "http://localhost:8080";
var username = localStorage.getItem("username");
var userId = getData("/api/users/username/" + username).id;

$(document).ready(function () {
    loadCourses();

    //Search
    $("#mySearch").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

function getData(url) {
    var fakeData = [];
    $.ajax({
        method: "GET",
        url: url,
        async: false,
        dataType: "json",
        success: function (response) {
            fakeData = response.data; // gán data
            // console.log(fakeData);
        },
        error: function (xhr) {
            fakeData = JSON.parse(xhr.responseText);
            // alert(fakeData.message);
        }
    });
    return fakeData;
}

function loadCourses() {
    var data = this.getData(LOCATION_API + "/api/users/" + userId +"/courses");
    $('#endRow').text(data.length);
    $('#totalRows').text(data.length);
    // var fields = $('.list-courses th[fieldName]');
    $('.list-courses tbody').empty();
    var path1 = "/student/courses/";
    var path2 = "/examDetail";
    $.each(data, function (index, item) {
        let rowhtml = $('<tr></tr>');
        rowhtml.append('<td class="">' + (index+1) + '</td>');
        rowhtml.append('<td class="">' + item.courseName + '</td>');
        let courseType = getCourseType(item.typeId);
        rowhtml.append('<td class="">' + courseType.name + '</td>');
        rowhtml.append('<td class="">' + courseType.description + '</td>');
        rowhtml.append('<td class="">' + courseType.numberQuestion + '</td>');
        rowhtml.append('<td class="">' + courseType.minScore + '</td>');

        let exam_url = path1.concat(item.id, path2);
        rowhtml.append('<td class=""><a href="'+ exam_url +'">Xem chi tiết</a></td>');
        $('.list-courses tbody').append(rowhtml);
    })

}

function getCourseType(typeId) {
    let courseType = this.getData(LOCATION_API + "/api/course-types/" + typeId);
    return courseType;
}