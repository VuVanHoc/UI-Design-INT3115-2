$(document).ready(function () {
	let layout = new Layout();
	layout.loadTotal();

});

class Layout {
	constructor() {
		this.InitEvents();
	}

	InitEvents() {
		$(document).on('click', '.notification', this.showNotifi);
		$(document).on('click', '.account', this.showOptionAcc);
		$('.list-users-main').on('click', '.update', this.openModalViewDetailUser);
		$('.form-item').on('click', '.cancel', this.closeModel);
		$(document).on('click', '.btn-add-user', this.openModalAddUser);
		$(document).on('click', '#btn-userinfo', this.openModalUpdateUser);
	}

	openModalViewDetailUser() {
		$('.modal-detail-profile').addClass("show");
	}

	openModalAddUser() {
		$('.modal-add-user').addClass("show");
	}

	openModalUpdateUser(){
		$('.modal-detail-profile').addClass("show")
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

	loadTotal(){
		$.ajax({
			method: "GET",
			url: "http://localhost:8080/api/users/total",
			async: true,
			contentType: "json",
			success: function (res) {
				var o = res.data;
				$("#totalAdmin").text(o.totalAdmin);
				$("#totalCourse").text(o.totalCourse);
				$("#totalStudent").text(o.totalStudent);
				$("#totalUser").text(o.totalOnline);
			},
			error: function () {
				alert("Error total");
			}
		})
	}

}

