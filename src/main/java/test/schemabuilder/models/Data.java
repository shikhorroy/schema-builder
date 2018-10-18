package test.schemabuilder.models;

public class Data {
    private String sql;
    private String selectedSqlLanguage;
    private String schemaPath;
    private String model;

    public Data() {
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSelectedSqlLanguage() {
        return selectedSqlLanguage;
    }

    public void setSelectedSqlLanguage(String selectedSqlLanguage) {
        this.selectedSqlLanguage = selectedSqlLanguage;
    }

    public String getSchemaPath() {
        return schemaPath;
    }

    public void setSchemaPath(String schemaPath) {
        this.schemaPath = schemaPath;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "\n\tData{" +
                "\n\t\tsql='" + sql + '\'' +
                ",\n\t\tselectedSqlLanguage='" + selectedSqlLanguage + '\'' +
                ",\n\t\tschemaPath='" + schemaPath + '\'' +
                ",\n\t\tmodel='" + model + '\'' +
                "\n\t}";
    }
}
