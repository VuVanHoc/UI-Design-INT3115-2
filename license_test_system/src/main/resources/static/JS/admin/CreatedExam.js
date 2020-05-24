const courseId = Number(pathArray = window.location.pathname.split('/')[3]);
var responseCreateExam;
var examId;
var existedExam;
var course = getData("/api/courses/" + courseId);
var courseType = getData("/api/course-types/" + course.typeId);

$(document).ready(function () {
    loadDataToUI();
});



function loadDataToUI(){
    loadQuestionsBank();
    addQuestionToExam(); //Khi chọn câu hỏi trong ngân hàng, câu hỏi thêm vào bảng câu hỏi đã chọn (action cột 2)
    removeQuestionFromExam();
    //Nếu đề thi đã được tạo => Load đề thi đã được tạo
    existedExam = getSchedule("/api/courses/" + courseId + "/exam-schedule");

    console.log(courseId);
    if(existedExam){
        loadExistedExam(existedExam);
        submitWithAction("UPDATE");
    }else{
        submitWithAction("CREATE");
    }

    $('.btn-back').on("click", function () {
       window.history.back();
    });
}

//Khi chọn 1 câu hỏi ở cột 2 => Thêm câu hỏi vào cột 3
function addQuestionToExam() {
    $('.random-question').click(function () {
        let cb = new Set();
        let min = Math.ceil(1);
        let max = Math.floor(50);
        while(cb.size < courseType.numberQuestion){
                let randomValue = Math.floor(Math.random() * (max - min + 1)) + min;
                cb.add(randomValue);
        }

        for (let item of cb) {
            $('#table1 input[name="cb_' + item + '"]').click();
        }
    });

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

function loadQuestionsBank() {
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
    data = getData("/api/questions?pageNo=0&pageSize=100");
    data.forEach(function (item, index) {
        row = $('<tr></tr>');
        row.append('<td>' + (index+1) + '</td>');
        if(item.content.length > 30){
            item.content = item.content.substr(0, 29) + "...";
        }
        row.append('<td><a href="/admin/question/detail?id=' + item.id+ '" target="_blank">' + (item.content) + '</a></td>');
        row.append('<td>' + (item.questionTypeCode) + '</td>');
        row.append('<td><input type="checkbox" name="cb_' + (index+1) + '">' +
            '<input type="hidden" class="questionIdClass" value="' + item.id +'"></td>');
        $('#table1 tbody').append(row);
    })
}

function loadMore(pageNo) {
    data = getData("/api/questions?pageNo=" + pageNo + "&pageSize=10");
    if(data === "Không có câu hỏi nào"){
        return data;
    }
    data.forEach(function (item, index) {
        row = $('<tr></tr>');
        row.append('<td>' + (pageNo*10 + index+1) + '</td>');
        if(item.content.length > 30){
            item.content = item.content.substr(0, 29) + "...";
        }
        row.append('<td>' + (item.content) + '</td>');
        row.append('<td>' + (item.questionTypeCode) + '</td>');
        row.append('<td><input type="checkbox" name="cb_' + (index+1) + '">' +
            '<input type="hidden" class="questionIdClass" value="' + item.id +'"></td>');
        $('#table1 tbody').append(row);
    })
}

function loadExistedExam(existedExam) {
    examId = existedExam.id;
    $('#startTime').val(convertDateTime(existedExam.startTime).toJSON().slice(0,19));
    $('#endTime').val(convertDateTime(existedExam.endTime).toJSON().slice(0,19));

    let array_question_id = getData("/api/exams/" + examId + "/questions");
        for (let i = 0; i < array_question_id.length; i++) {
            $('#table1 input[value="' + array_question_id[i] + '"]').prev().click();
        }
}

function submitWithAction(action) {
    $('.btn-save').click(function () {
        startTime = $('#startTime').val();
        endTime = $('#endTime').val();
        note = $('#note').val();
        dataSchedule = {
            endTime: new Date(endTime).getTime()/1000,
            note: note,
            startTime: new Date(startTime).getTime()/1000
        };

        let questionsArray = new Array();
        $('#tb2 .questionIdClass').each(function (index, item) {
            questionsArray.push(Number($(item).val()));
        });
        let questionsIdsData = {
            questionIds: questionsArray
        };

        if(questionsArray.length !== courseType.numberQuestion) {
            alert("Số lượng câu hỏi cho đề thi phải bằng " + courseType.numberQuestion);
        }else{
            if(action === "UPDATE"){
                responseCreateExam = putData("/api/courses/" + courseId +"/exam-schedule", JSON.stringify(dataSchedule));
                setTimeout(function(){
                    examId = getData("/api/courses/" + courseId + "/exam-schedule").id;
                    let resultExam = putData("/api/exams/" + examId +"/questions", JSON.stringify(questionsIdsData));
                    alert("Đã ghi nhận!");
                }, 500);
            }else if(action === "CREATE"){
                responseCreateExam = postData("/api/courses/" + courseId +"/exam-schedule", JSON.stringify(dataSchedule));

                setTimeout(function(){
                    examId = getData("/api/courses/" + courseId + "/exam-schedule").id;
                    let resultExam = postData("/api/exams/" + examId +"/questions", JSON.stringify(questionsIdsData));
                    alert("Đã ghi nhận!");
                }, 500);
            }
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