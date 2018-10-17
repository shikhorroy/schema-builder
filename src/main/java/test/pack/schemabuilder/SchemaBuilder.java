package test.pack.schemabuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.schemabuilder.models.Data;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

@Component
class SchemaBuilder {
    private String rawData;
    private JSONObject jsonData;
    private String schema;

    private Data data;

    private String getRawData() {
        return rawData;
    }

    void setRawData(String rawData) {
        this.rawData = rawData;
    }

    public JSONObject getJsonData() {
        return jsonData;
    }

    private void setJsonData(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public Data getData() {
        return data;
    }

    private void setData(Data data) {
        this.data = data;
    }

    private void prepareJsonData() {
        // ~ prepare json data:
        try {
            JSONObject jsonObject = new JSONObject(this.getRawData());
            this.setJsonData(jsonObject);
            System.out.println("Submitted raw data: " + jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jsonToJavaObjectConverter() {
        // ~ rawData to Data object:
        try {
            Data dataObj = new ObjectMapper().readValue(this.getRawData(), Data.class);
            this.setData(dataObj);
            System.out.println("*** Submitted Raw Data to Java Object conversion Successful ***");
            System.out.println("=> object: " + dataObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String buildSchema() {
        this.prepareJsonData();
        this.jsonToJavaObjectConverter();
        List<SchemaMetaDataInfo> schemaMetaDataInfos = this.getSchemaMetaData();
        return "";
    }

    @Autowired
    QueryUtils queryUtils;

    private List<SchemaMetaDataInfo> getSchemaMetaData() {
        List<SchemaMetaDataInfo> schemaMetaDataInfos = new ArrayList<>();
        ResultSet rs = queryUtils.query(this.data.getSql());
        try {
            SchemaMetaDataInfo schemaMetaDataInfo = new SchemaMetaDataInfo();

            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            schemaMetaDataInfo.setFieldCount(columnCount);

            System.out.println("*** columns info ***");
            for (int i = 0; i < columnCount; i++) {
                String columnName = md.getColumnName(i + 1);
                String dataType = md.getColumnTypeName(i + 1);

                schemaMetaDataInfo.setField(columnName);
                schemaMetaDataInfo.setDataType(dataType);

                schemaMetaDataInfos.add(schemaMetaDataInfo);
                schemaMetaDataInfo = new SchemaMetaDataInfo();
                System.out.println(columnName + ": " + " (" + dataType + ")");
            }
//            while (rs.next()) {
//                for (int i = 0; i < md.getColumnCount(); i++) {
//                    String columnName = md.getColumnName(i + 1);
//                    System.out.println(columnName + ": " + rs.getString(columnName) + " (" + md.getColumnTypeName(i + 1) + ")");
//                }
//                System.out.println();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return schemaMetaDataInfos;
    }
}
