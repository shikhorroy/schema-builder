$(document).ready(function () {
    $("input[name = 'populate-schema']").click(populateSchema);
});

var populateSchema = function () {
    var sql = $('#sql-textarea textarea')[0].value;
    var selectedSqlLang = $("#sql-lang-options option:selected")[0].value;

    var schemaPath = $("input[name = 'schema-path']").val();
    var model = $("input[name = 'model']").val();

    console.log(sql);
    console.log(selectedSqlLang);
    console.log(schemaPath);
    console.log(model);
};

function query() {
    alert('quering...');
}
