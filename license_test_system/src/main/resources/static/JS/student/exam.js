const API_URL = "http://localhost:8080";
const IMAGE_URL = "http://localhost:8080/Images/";
const userId = getData("/api/users/username/" + localStorage.username).id;
var caudalam = new Set(); //id của các câu đã làm
var courseId = $('#courseId').val();
var course = getCourse(courseId);
var courseType = getCourseType(course.typeId);
var totalQuestions = courseType.numberQuestion;

var exam = getExam(courseId);
var examId = exam.id;

//Danh sách các câu hỏi
var array_question_id = getData(API_URL + "/api/exams/" + examId + "/questions");
array_question_id.sort();
//Danh sách các loại câu hỏi
var questionTypeCode = ["MC", "SA", "TF", "SO"]; //MC: checkbox, SA: text, SO: radio

var DaNopBai = false;
// var ThoiGianLam;
var SoCauDung = 0, SoCauSai = 0;

$(document).ready(function () {
    //Hiển thị số ô câu hỏi
    showListIconQuestion(totalQuestions);

    //Load dữ liệu câu hỏi
    loadQuestions(totalQuestions);

    //Hiển thị câu hỏi 1 khi bắt đầu
    var current_question = 1;
    $("[data-question=1]").addClass("dang-lam");
    showQuestion(current_question);

    listenClickOtherQuestion(current_question);
    $('.btn-submit').click(function () {
        if(confirm("Bài thi sẽ kết thúc. Bạn chắc chắn chứ?")){
            submit();
            DaNopBai = true;
        }
    });

    // Image to Lightbox Overlay
    $('img').on('click', function() {
        $('#overlay')
            .css({backgroundImage: `url(${this.src})`})
            .addClass('open')
            .one('click', function() { $(this).removeClass('open'); });
    });
});

// when press arrow keys, go to other questions
$(document).keydown(function(e) {
    let current_question = Number($('.dang-lam').attr('data-question'));

    if ((e.keyCode >= 48 && e.keyCode <= 57)) {
        $('#question_' + current_question + ' [index-answer="' + (e.keyCode - 49) + '"]').click();
    }

    if ((e.keyCode >= 96 && e.keyCode <= 105)) {
        $('#question_' + current_question + ' [index-answer="' + (e.keyCode - 97) + '"]').click();
    }

    switch (e.keyCode) {
        case 37: // left arrow key
            if(current_question > 1){
                $('[data-question="' + (current_question - 1) +'"]').click();
            }else{
                $('[data-question="' + (current_question - 1 + totalQuestions) +'"]').click();
            }
            break;
        case 38: // up arrow key
            if(current_question - 5 > 0){ // item in a row: 5
                $('[data-question="' + (current_question - 5) +'"]').click();
            }else{
                $('[data-question="' + (totalQuestions + current_question - 5) +'"]').click();
            }
            break;
        case 39: // right arrow key
            if(current_question < totalQuestions){
                $('[data-question="' + (current_question + 1) +'"]').click();
            }else{
                current_question = 0;
                $('[data-question="' + (current_question + 1) +'"]').click();
            }
            break;
        case 40: // down arrow key
            if(current_question + 5 < totalQuestions){ // item in a row: 5
                $('[data-question="' + (current_question + 5) +'"]').click();
            }else{
                $('[data-question="' + (current_question + 5 - totalQuestions) +'"]').click();
            }
            break;
        default:
            // console.log(e.keyCode);
    }
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
            alert(fakeData.message);
            // window.history.back();

        }
    });
    return fakeData;
}

function postData(url, jsonData) {
    var data = [];
    $.ajax({
        url: url,
        method: "POST",
        data: jsonData,
        dataType: "json",
        characterData: "utf-8",
        contentType: "application/json; charset=UTF-8",
        success : function () {
            alert("Đã nộp bài!");
        }
    });
    return data;
}

function listenClickOtherQuestion(current_question) {
    //Hiển thị nội dung câu hỏi khi chuyển câu hỏi
    $("[data-question]").click(function () {
        if (this.getAttribute("data-question") != current_question) {
            danh_dau_da_lam(current_question);
            $("[data-question=" + current_question +"]").removeClass("dang-lam");
            if(da_lam(current_question)){
                $("[data-question=" + current_question +"]").addClass("da-lam");
            }else{
                $("[data-question=" + current_question +"]").removeClass("da-lam");
            }
            hiddenQuestion(current_question);
            current_question = this.getAttribute("data-question");
            $("[data-question=" + current_question +"]").removeClass("da-lam");
            $("[data-question=" + current_question +"]").addClass("dang-lam");
            showQuestion(current_question);
        }
    });
}

function danh_dau_da_lam(questionNumber) {
    if($('#question_' + questionNumber).attr('question-type') == "SA"){
        $('#question_' + questionNumber + ' :input').each(function (index, element){
            if($(element).val()){
                caudalam.add(Number(questionNumber));
            }else{
                caudalam.delete(Number(questionNumber));
            }
        });
    }else{
        var numberChecked = 0;
        $('#question_' + questionNumber + ' :input').each(function (index, element) {
            if($(element).prop('checked')){
                numberChecked = numberChecked + 1;
            }
        });
        if(numberChecked > 0){
            caudalam.add(Number(questionNumber));
        }else{
            caudalam.delete(Number(questionNumber));
        }
    }
}

function da_lam(current_question) {
    return caudalam.has(Number(current_question));
}

function showListIconQuestion(totalQuestions) {
    var questionsPerRow = 5;
    var numberOfRows = Math.ceil(totalQuestions / questionsPerRow);
    var i, j;
    for (i = 0; i < numberOfRows; i++) {
        var row = $('<div class="row"></div>');
        for (j = 0; j < questionsPerRow; j++) {
            var question = (i * questionsPerRow + j + 1);
            row.append('<a href="#" data-question="' + question + '">' + question + '</a>');
        }
        $('.list-icon-question').append(row);
    }

}

function loadQuestions(totalQuestions) {
    // console.log(array_question_id);
    let i;
    for (i = 0; i < totalQuestions; i++) {
        let data_question = getData(API_URL + "/api/questions/".concat(array_question_id[i]));
        $('.list-question').append('<div id="question_' + (i + 1) + '" class="question" style="display: none;" question-type="' + data_question.questionTypeCode + '"><p class="question-title">Câu ' + (i + 1) + ':</p></div>')

        //Nội dung câu hỏi
        $('#question_' + (i + 1)).append("<div class='question-content'></div>")
        $('#question_' + (i + 1) + ' .question-content').append("<p>" + data_question.content + "</p>");
        if(data_question.imageUrl != null){
            $('#question_' + (i + 1) + ' .question-content').append("<img class='question-image' src='" + IMAGE_URL.concat(data_question.imageUrl) + "'>");
        }

        //Phần câu trả lời
        $('#question_' + (i + 1)).append("<div class='answer-content'></div>");
        let j;
        switch(data_question.questionTypeCode) {
            case questionTypeCode[0]: //MC
                for(j = 0; j < data_question.answers.length; j++){
                    $('#question_' + (i + 1) + ' .answer-content').append("<label><input type='checkbox' index-answer='" + j + "'>" + data_question.answers[j] + "</label>");
                }
                break;
            case questionTypeCode[1]: //SA
                for(j = 0; j < data_question.answers.length; j++){
                    $('#question_' + (i + 1) + ' .answer-content').append("<input type='text' index-answer='" + j + "' placeholder='Nhập câu trả lời'/>");
                }
                break;
            case questionTypeCode[2]: //TF
                for(j = 0; j < data_question.answers.length; j++){
                    $('#question_' + (i + 1) + ' .answer-content').append("<label><input type='radio' name='quiz-radio-" + i + "' index-answer='" + j + "'/>" + data_question.answers[j] + "</label>");
                }
                break;
            default:
                $('#question_' + (i + 1) + ' .answer-content').append("<p>Không thể load dạng câu trả lời</p>");
        }
    }
}

function showQuestion(questionid) {
    var selector = '#question_'.concat(questionid);
    $(selector).css("display", "");
}

function hiddenQuestion(questionid) {
    var selector = '#question_'.concat(questionid);
    $(selector).css("display", "none");
}

function getCourseType(id) {
    var courseType = this.getData(API_URL + "/api/course-types/" + id);
    return courseType;
}

function getCourse(id) {
    var course = this.getData(API_URL + "/api/courses/" + id);
    return course;
}

function getExam(courseId) {
    var exam = getData(API_URL + "/api/courses/" + courseId + "/exam-schedule");
    return exam;
}

function submit() {
    $('#time').countdown(0);
    $(":input").prop('disabled', true);
    $('.topbar').css('display','none');
    let i;
    for (i = 0; i < totalQuestions; i++) {
        let questionNumber = i + 1;
        let answer = getData(API_URL + "/api/questions/" + array_question_id[i] + "/answers");
        let corrects = answer.correctIndex;

        if($('#question_' + questionNumber).attr('question-type') == "SA"){
            $('#question_' + questionNumber + ' :input').each(function (index, element){
                if($(element).val() === answer.answers[corrects[0]]){
                    $("[data-question=" + questionNumber +"]").addClass("cau-dung");
                    SoCauDung += 1;
                }else{
                    $("[data-question=" + questionNumber +"]").addClass("cau-sai");
                    SoCauSai += 1;
                }
            });
        }else{
            let checked_answer = new Array();
            $('#question_' + questionNumber + ' :input').each(function (index, element) {
                if($('#question_' + questionNumber +' input[index-answer="' + index + '"]').is(":checked")){
                    checked_answer.push(index);
                }
            });
            if(compareArrays(corrects, checked_answer)){
                $("[data-question=" + questionNumber +"]").addClass("cau-dung");
                SoCauDung += 1;
            }else{
                $("[data-question=" + questionNumber +"]").addClass("cau-sai");
                SoCauSai += 1;
            }
        }
    }

    $('.so-cau-dung').text(SoCauDung);
    $('.so-cau-sai').text(SoCauSai);
    if(SoCauDung >= courseType.minScore){
        $('.ket-qua').text("Đạt").addClass('dat');
    }else {
        $('.ket-qua').text("Không Đạt").addClass('khong-dat');
    }
    $('.result').removeAttr('style');

    let dataResult = {
        courseId: courseId,
        score: SoCauDung ,
        status: (SoCauDung >= courseType.minScore),
        userId:  userId
    };
    let result = postData(API_URL + "/api/exam-result", JSON.stringify(dataResult));
}

function compareArrays(arr1, arr2) {
    return $(arr1).not(arr2).length == 0 && $(arr2).not(arr1).length == 0
};

(function($){
    $.fn.countdown = function(milliseconds, callback) {
        var $el = this;
        var buffer = 200;
        var end, timer;

        // Defaults
        milliseconds = milliseconds || 20 * 60 * 1000; // 20 minutes
        end = new Date(Date.now() + milliseconds + buffer);

        // Start the counter
        tick();

        function formatTime(time){
            minutes = format_two_digits(time.getMinutes());
            seconds = format_two_digits(time.getSeconds());
            return minutes + ":" + seconds;
        }

        function format_two_digits(n) {
            return n < 10 ? '0' + n : n;
        }

        function tick() {
            var remaining = new Date(end - Date.now());

            if (remaining > 0 && DaNopBai == false) {
                $el.html(formatTime(remaining));
                timer = setTimeout(tick, 1000);
            } else {
                $el.addClass('finish');
                submit();
                // ThoiGianLam = milliseconds - remaining;
                clearInterval(timer);
                if (callback) callback.apply($el);
            }
        };
    };

    let totalTime;

    totalTime = Date.parse(exam.endTime) - Date.parse(exam.startTime);

    $('#time').countdown(totalTime);
})(jQuery);
