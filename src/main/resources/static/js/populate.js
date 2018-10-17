$(document).ready(function () {
    $("input[name = 'populate-schema']").click(populateSchema);
});

var populateSchema = function () {
    var data = prepareData();

    $.ajax({
        type: "POST",
        url: "http://localhost:8001/PopulateSchema/populate/",
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
    var model = $("input[name = 'models']").val();

    return {
        "sql": sql,
        "selectedSqlLanguage": selectedSqlLang,
        "schemaPath": schemaPath,
        "model": model
    };
};

function query() {
    alert('quering...');
}
