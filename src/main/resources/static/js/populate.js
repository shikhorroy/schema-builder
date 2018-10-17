function populateSchema() {
    var sql = document.getElementById("sql-textarea").getElementsByTagName("textarea")[0].value;

    var sqlLangs = document.getElementById("sql-lang-options").getElementsByTagName("select")[0];
    var selectedSqlLang = sqlLangs.options[sqlLangs.selectedIndex].value;

    var schemaPath = document.getElementById("schema-path-input").getElementsByTagName("input")["schema-path"].value;
    var model = document.getElementById("model-input").getElementsByTagName("input")["model"].value;

    console.log(sql);
    console.log(selectedSqlLang);
    console.log(schemaPath);
    console.log(model);
}

function query() {
    alert('quering...');
}
