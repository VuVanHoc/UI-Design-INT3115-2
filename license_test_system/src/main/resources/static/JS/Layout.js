$(document).ready(function () {
    var layout = new Layout();
    var drawChartPie = new DrawChartPie(10,90); // tuyền ti le thi sinh đỗ hay trượi
    var drawChartLine = new DrawChartLine(); // truyên vào số lượng tí sinh có điểm 10 20 30...100
});
class Layout extends Base {
    constructor() {
        super();
        this.InitEvents();
    }

    InitEvents() {
        $(document).on('click', '.notification', this.showNotifi);
        $(document).on('click', '.account', this.showOptionAcc);
        $('.list-users-main').on('click', 'tbody tr', this.openModalViewDetailUser);
        $('.btn-wrap').on('click', '.cancel', this.closeModel);
        $(document).on('click', '.btn-add-user', this.openModalAddUser);
    }

    openModalViewDetailUser() {
        $('.modal-detail-profile').addClass("show");
    }

    openModalAddUser() {
        $('.modal-add-user').addClass("show");
    }

    closeModel() {
        $('.modal').removeClass("show");
    }

    showNotifi() {
        $('.list-notifi').toggleClass("show");
        $('.notifi').remove();
        $(document).click(function () {
            $('.notifi').remove();
        });
    }
    showOptionAcc() {
        $('.dropdownd-user').toggleClass("show");
    }
}
class DrawChartPie {
    constructor(a,b) { //cho thêm tham số vào đây
        var ctx = document.getElementById('chart-pass-fail').getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'pie',

            // The data for our dataset
            data: {
                labels: ["Pass", "Fail"],
                datasets: [{
                    label: "My First dataset",
                    backgroundColor: ['#ccc', 'gray'],
                    borderColor: '#fff',
                    data: [a, b], //đây la sô % đỗ vá trượt
                }]
            },

            // Configuration options go here
            options: {}
        });
    }
}
class DrawChartLine {
    constructor() { // thêm tham số ở đây
        var ctx = document.getElementById('chart-point-exam').getContext('2d');
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'line',

            // The data for our dataset
            data: {
                labels: ["10", "20", "30", "40", "50", "60", "70", "80", "90", "100"],
                datasets: [{
                    label: "Point",
                    backgroundColor: "#ccc",
                    borderColor: '#282828',
                    data: [0, 10, 5, 2, 20, 30, 45, 225, 55, 66], // đây là số thi sinh từ 0 10 20 ..100
                }]
            },

            // Configuration options go here
            options: {}
        });
    }
}
