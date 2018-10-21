$(document).ready(function () {
    onFocusCheck();
    $("input[name = 'populate-schema']").click(populateSchema);
    $("input[name = 'qeury']").click(query);
});

function onFocusCheck() {
    $('#sql-textarea textarea').focus(function () {
        $(this).css("border-color", "");
    });
    $("input[name = 'schema-path']").focus(function () {
        $(this).css("border-color", "");
    });
    $("input[name = 'model']").focus(function () {
        $(this).css("border-color", "");
    });
}

var query = function () {
    var flag = checkRequired();
    if (flag == false) return;

    var data = prepareData();
    var serviceUrl = "/QueryExecutor/query/";

    $.ajax({
        type: "POST",
        url: baseUrl() + serviceUrl,
        data: {
            "data": JSON.stringify(data)
        },
        cache: false,
        success: function (data) {
            alert(data);
        },
        error: function (error) {
            alert(error);
        }
    });
};

var populateSchema = function () {
    if (checkRequired() == false) return;

    var data = prepareData();
    var serviceUrl = "/PopulateSchema/populate/";

    $.ajax({
        type: "POST",
        url: baseUrl() + serviceUrl,
        data: {
            "data": JSON.stringify(data)
        },
        cache: false,
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            alert(error);
        }
    });
};

var prepareData = function () {
    var sql = $('#sql-textarea textarea')[0].value;
    var selectedSqlLang = $("#sql-lang-options option:selected")[0].value;
    var schemaPath = $("input[name = 'schema-path']").val();
    var model = $("input[name = 'model']").val();

    return {
        "sql": sql,
        "selectedSqlLanguage": selectedSqlLang,
        "schemaPath": schemaPath,
        "model": model
    };
};

function checkRequired() {
    var sql = $('#sql-textarea textarea')[0].value;
    var schemaPath = $("input[name = 'schema-path']").val();
    var model = $("input[name = 'model']").val();

    var flag = true;
    if (sql.length == 0) {
        flag = false;
        $('#sql-textarea textarea').css('border-color', 'red');
    }
    if (schemaPath.length == 0) {
        flag = false;
        $("input[name = 'schema-path']").css('border-color', 'red');
    }
    if (model.length == 0) {
        flag = false;
        $("input[name = 'model']").css('border-color', 'red');
    }
    return flag;
}

var baseUrl = function () {
    var protocol = window.location.protocol;
    var host = window.location.hostname;
    var port = window.location.port;

    var baseUrl = protocol + "//" + host + (port ? ':' + port : '');

    console.log(baseUrl);
    return baseUrl;
};
