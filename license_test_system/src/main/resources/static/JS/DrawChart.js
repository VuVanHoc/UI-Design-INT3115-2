$(document).ready(function () {
    a = getData("/api/exam-result/totalRecords"); //Tổng ĐỖ+TRƯỢT
    b = getData("/api/exam-result/totalPass"); //Tổng ĐỖ
	dataPoint = getData("/api/exam-result/count-point");
	var drawChartPie = new DrawChartPie(b,a-b); // tuyền ti le thi sinh đỗ hay trượi
	var drawChartLine = new DrawChartLine(dataPoint); // truyên vào số lượng tí sinh có điểm 10 20 30...100



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

class DrawChartPie {
	constructor(a,b) { //cho thêm tham số vào đây
		var ctx = document.getElementById('chart-pass-fail').getContext('2d');
		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: 'pie',

			// The data for our dataset
			data: {
				labels: ["Đạt", "Không đạt"],
				datasets: [{
					label: "My First dataset",
					backgroundColor: ['#19B133', '#fa2c2c'],
					borderColor: '#e8e5e5',
					data: [a, b], //đây la sô % đỗ vá trượt
				}]
			},

			// Configuration options go here
			options: {}
		});
	}
}
class DrawChartLine {
	constructor(data) { // thêm tham số ở đây
		var ctx = document.getElementById('chart-point-exam').getContext('2d');
		var chart = new Chart(ctx, {
			// The type of chart we want to create
			type: 'bar',

			// The data for our dataset
			data: {
				labels: ["15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"],
				datasets: [{
					label: "Point",
					backgroundColor: "#0285bb",
					borderColor: '#4852cc',
					// data: [0, 10, 20]
					data: data, // đây là số thi sinh từ 0 10 20 ..100
				}]
			},

			// Configuration options go here
			options: {

				yAxes: [{
					ticks: {
						beginAtZero: true,
						callback: function (value) { if (Number.isInteger(value)) { return value; } },
						stepSize: 1
					},
					scaleLabel: {
						display: true,
						labelString : 'Số thí sinh'
					},
				}],
				tooltip : {
					enable : true
				}
			}
		});
	}
}