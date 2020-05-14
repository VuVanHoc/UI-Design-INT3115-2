class Base {
    constructor() {
        this.loadAllData();
    }
    getAllData(url) {
        var fakeData = [];
        $.ajax({
            method: "GET",
            url: url,
            async: false,
            dataType: "json",
            success: function (data) {
                fakeData = data["Customers"]; // gán data
            },
            error: function () {
                alert('Hệ thống sẽ cố gắng không hiện thông báo này');
            }
        });
        return fakeData;
    }
    loadAllData() {
        var data = this.getAllData("/Content/Data/data.json");
        var fields = $('.list-users th[fieldName]');
        $('.list-users tbody').empty();
        $.each(data, function (index, item) {
            var rowhtml = $('<tr></tr>');
            $.each(fields, function (fieldindex, fielditem) {
                var fieldname = fielditem.getAttribute('fieldName');
                var value = item[fieldname];
                if (fieldname) {
                    rowhtml.append('<td class="' + fieldname + '">' + value + '</td>');
                }            
            });
            $('.list-users tbody').append(rowhtml);
        })
    }
    InsertData(url) {
        $.ajax({
            method: "POST",
            url: url,
            async: false,
            dataType: "json",
            success: function (data) {
                fakeData = data["Customers"]; // gán data
            },
            error: function () {

            }
        });
    }
    DeleteData(url) {
        $.ajax({
            method: "DELETE",
            url: url,
            async: false,
            dataType: "json",
            success: function (data) {

            },
            error: function () {
                alert('Hệ thống sẽ cố gắng không hiện thông báo này');
            }
        });
    }
    UpdateData() {
        $.ajax({
            method: "PUT",
            url: url,
            async: false,
            dataType: "json",
            success: function (data) {
                
            },
            error: function () {
                alert('Hệ thống sẽ cố gắng không hiện thông báo này');
            }
        });
    }
}