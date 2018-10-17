package test.pack.schemabuilder;

public class SchemaMetaDataInfo {
    private String field;
    private String dataType;
    private static Integer fieldCount;

    public String getField() {
        return field;
    }

    void setField(String field) {
        this.field = field;
    }

    public String getDataType() {
        return dataType;
    }

    void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getFieldCount() {
        return fieldCount;
    }

    void setFieldCount(Integer fieldCount) {
        SchemaMetaDataInfo.fieldCount = fieldCount;
    }
}
