$(document).ready(function () {
	$(document).on('change', '.question-type', function () {
		var questionsType = $('.question-type').val();
		switch (questionsType) {
			case "multiple-choice":
				$('.answer').css("display", "none");
				$('.answer-choice').css("display", "block");
				var answers = $('.one-answer');
				$.each(answers, function (index, answer) {
					if (index != 0) {
						$(answer).remove();
					}
				});
				break;
			case "text-answer":
				$('.answer').css("display", "none");
				$('.answer-text').css("display", "block");
				break;
			case "yes-no":
				$('.answer').css("display", "none");
				$('.answer-yes-no').css("display", "block");
				break;
			default:
				break;
		}
	});
	$(document).on('click', '.addNewQuestion', function () {
		var oneAnswer = $('<div class="one-answer"></div>');
		var textarea = $('<input type="checkbox" class="checkbox" title="Đáp án đúng">   <textarea placeholder="Nhập đáp án..."></textarea>');
		var buttonDelete = ('<button class="deleteQuestion btn btn-danger">Xóa</button>');
		oneAnswer.append(textarea).append(buttonDelete);
		$(this).parent().children('.answer-wrap').append(oneAnswer);

	});
	$(document).on('click', '.btn-danger', function () {
		$(this).parent().remove();
	});

	$('.btn-cancel').click(function () {
		window.location.href = "http://localhost:8080/admin/questions"
	});

	$('.btn-submit').click(function () {
		let questionType = $('.question-type').val();
		let question = $('#question-content').val();
		let image = $('#image-question').val().replace(/C:\\fakepath\\/i, '');


		let data = {
			questionType: getQuestionType(questionType),
			question: question,
			image: image,
			answerDTOList: getAnswerFromClient(questionType)
		};
		// console.log(questionType + ' ' + question + ' ' + image);
		console.log(data);

		if(question !== ''){
			postData(data);

		} else {
			alert("Bạn cần phải nhập câu hỏi!");
		}
		// switch (questionType) {
		//     case "multiple-choice" :
		//     	console.log(questionType)
		// }
	})
});

function postData(data) {
	$.ajax({
		url : "/api/questions/create-new-question",
		method : "POST",
		data: JSON.stringify(data),
		characterData: "utf-8",
		contentType: "application/json; charset=UTF-8",
		dataType: "json",
		success : function () {
			alert("Tạo câu hỏi thành công!");
			window.location.href = "http://localhost:8080/admin/questions";
		},
		error : function () {
			alert("Xin lỗi, đã có lỗi xảy ra!");
			window.location.href = "http://localhost:8080/admin/questions";

		}
	})
}
function getQuestionType(questionType) {
	if (questionType === "multiple-choice") return "MC";
	if (questionType === "text-answer") return "SA";
	if (questionType === "yes-no") return "TF";
}

function getAnswerFromClient(questionType) {
	let answerArr = [];
	let status;
	let content;
	switch (questionType) {

		case "multiple-choice" :

			$('.one-answer').each(function (index, element) {
				status = $(element).children('input[type="checkbox"]').is(':checked');
				content = $(element).children('textarea').val();
				let answer = {
					status: status,
					content: content
				};
				answerArr.push(answer);
				console.log(status + " " + content);
			});
			answerArr.splice(-1,1);
			break;
		case "text-answer" :
			status = true;
			content = $('#text-answer').val();
			let answer = {
				status: status,
				content: content
			};
			answerArr.push(answer);
			break;
		case "yes-no" :
			$('.yesno').each(function (index, element) {
				status = $(element).children('input[type="radio"]').is(':checked');
				content = $(element).children('input[type="text"]').val();
				let answer = {
					status: status,
					content: content
				};
				answerArr.push(answer);
				console.log(status + " " + content);
			});
			break;


	}

	return answerArr;
}