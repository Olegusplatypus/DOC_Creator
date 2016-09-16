
function onClickGetMethods() {
    var projectId = $('#projectSelector').val();
    var clazz = $('#classes').val();
    var queryParams = "?projectId=" + projectId + "&class=" + clazz;
    $.ajax({
        url: "/doc/getmethods" + queryParams,
        type: "GET",
        success: function (data) {
            $("#printer").val(data);
        },
        error: function () {
            $("#printer").val('Something wrong!\n Check if your class has methods ')
        }
    });
}


function getClassesList() {

    var projectId = $('#projectSelector').val();
    var queryParams = "?projectId=" + projectId;
    $.ajax({
        url: "/doc/classes" + queryParams,
        type: "GET",
        success: function (data) {

            select = document.getElementById("classes");
            while (select.options.length) {
                select.options[0] = null;
            }
            for (var i = 0; i < data.length; i++) {
                var opt = document.createElement('option');
                opt.value = data[i];
                opt.innerHTML = data[i];
                select.appendChild(opt);
            }

        },
        error: function () {
            alert('Wrong path! Check correctness of project pathes')
        }
    });
}
function onClickGenerateJSON() {
    var projectId = $('#projectSelector').val();
    var clazz = $('#classes').val();
    var queryParams = "?projectId=" + projectId + "&class=" + clazz;
    $.ajax({
        url: "/doc/getdoc" + queryParams,
        type: "GET",
        success: function (data) {
            $("#printer").val(data);
        },
        error: function () {
            $("#printer").val('Something wrong!\n Check if your class is compatible to JSON ')
        }
    });
}