package test.pack.schemabuilder;

public class SchemaMetaDataInfo {
    private String field;
    private String dataType;
    private static Integer fieldCount;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getFieldCount() {
        return fieldCount;
    }

    public void setFieldCount(Integer fieldCount) {
        SchemaMetaDataInfo.fieldCount = fieldCount;
    }
}
