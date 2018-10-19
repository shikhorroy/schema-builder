package test.pack.schemabuilder.models;

public class SchemaMetaDataInfo {
    private String fieldName;
    private String fieldLabel;
    private String dataType;
    private static Integer fieldCount;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
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

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }
}
