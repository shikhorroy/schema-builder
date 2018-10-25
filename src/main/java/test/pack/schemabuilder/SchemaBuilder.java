package test.pack.schemabuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import test.pack.schemabuilder.constants.Constants;
import test.pack.schemabuilder.models.Data;
import test.pack.schemabuilder.models.SchemaMetaDataInfo;
import test.pack.schemabuilder.schema.Schema;
import test.pack.schemabuilder.schema.columns.Columns;
import test.pack.schemabuilder.schema.fields.Field;
import test.pack.schemabuilder.schema.fields.Fields;
import test.pack.schemabuilder.schema.fields.Meta;
import test.pack.schemabuilder.schema.joinhints.JoinHints;
import test.pack.schemabuilder.schema.options.Options;
import test.pack.schemabuilder.schema.options.Query;
import test.pack.schemabuilder.schema.order.Order;
import test.pack.schemabuilder.schema.orderhints.OrderHints;
import test.pack.schemabuilder.schema.search.Search;
import test.pack.schemabuilder.schema.validators.Validators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.*;

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

        Data data = this.getData();
        List<SchemaMetaDataInfo> schemaMetaDataInfos = this.getSchemaMetaData();
        String schema = this.build(schemaMetaDataInfos, data);
        System.out.println("*** schema ***");
        System.out.println(schema);

        this.writeSchema(schema, this.data.getSchemaPath());
        return "";
    }

    private void writeSchema(String schema, String schemaPath) {
        File file = new File(String.valueOf(schemaPath));
        try {
            file.getParentFile().mkdir();
            boolean r = file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(schema);
            bw.close();
            System.out.println("*** " + schemaPath + " created ***");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Value("${schema.indentation:2}")
    Integer schemaIndentation;

    private String build(List<SchemaMetaDataInfo> schemaMetaDataInfos, Data data) {
        Schema schema = this.prepareSchema(schemaMetaDataInfos);

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setIndent(schemaIndentation);
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);

        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(schema, Map.class);

        this.overRideFieldsMap(map);
        this.overRideOptionsMap(map);

        for (Map.Entry e : map.entrySet()) {
            if (e.getValue() == null) e.setValue(Constants.CURLY_BRACKET);
        }

        Yaml yaml = new Yaml(dumperOptions);
        String output = yaml.dump(map);
        output = output.replace(Constants.CURLY_BRACKET, Constants.CURLY_BRACKET_REPLACER);

        return output;
    }

    private Schema prepareSchema(List<SchemaMetaDataInfo> schemaMetaDataInfos) {
        Fields fields = this.prepareFields(schemaMetaDataInfos);
        Columns columns = this.prepareColumns();
        JoinHints joinHints = this.prepareJoinHints();
        Order order = this.prepareOrder();
        OrderHints orderHints = this.prepareOrderHints();
        Search search = this.prepareSearch();
        Validators validators = this.prepareValidators();

        Options options = this.prepareOptions(data);

        Schema schema = new Schema();
        schema.setFields(fields)
                .setColumns(columns)
                .setJoinHints(joinHints)
                .setOrder(order)
                .setOrderHints(orderHints)
                .setSearch(search)
                .setValidators(validators)
                .setOptions(options);

        return schema;
    }

    private void overRideOptionsMap(Map<String, Object> map) {
        Map<String, Object> optionsMap = (Map<String, Object>) map.get(Constants.OPTIONS);
        Map<String, Object> queryMap = (Map<String, Object>) optionsMap.get(Constants.QUERY);
        String server = (String) queryMap.get(Constants.SERVER);
        String sql = (String) queryMap.get(Constants.QUERY);
        Map<String, String> queryMapObj = new HashMap<>();
        queryMap.clear();
        queryMap.put(server, sql);
    }

    @Value("${string.enable-title-case:true}")
    boolean titleCaseConversionEnable;

    private void overRideFieldsMap(Map<String, Object> map) {
        Map<String, Object> fieldsMap = (Map<String, Object>) map.get(Constants.FIELDS);
        List<Map<String, Object>> fieldMapList = (List<Map<String, Object>>) fieldsMap.get(Constants.FIELD_LIST);
        Map<String, Object> schemaFields = new LinkedHashMap<>();
        Map<String, Integer> schemaFieldsSeq = new HashMap<>();
        for (Map<String, Object> fieldMap : fieldMapList) {
            String name = (String) fieldMap.get(Constants.NAME);
            String label = (String) fieldMap.get(Constants.LABEL);

            name = StringUtils.toLowerCamelCase(name);

            //~ convert label value into title case based on properties value:
            if (titleCaseConversionEnable) label = StringUtils.toTitleCase(label);

            fieldMap.remove(Constants.LABEL);
            fieldMap.put(Constants.NAME, label);
            String field = data.getModel() + "." + name;

            //~ field name duplication check and automatic serialization:
            if (schemaFields.containsKey(field)) {
                System.err.println("*** " + field + " found more than once!");
                int seq = schemaFieldsSeq.get(field) + 1;
                schemaFieldsSeq.put(field, seq);
                field += seq;
            } else schemaFieldsSeq.put(field, 0);

            schemaFields.put(field, fieldMap);
        }
        fieldsMap.clear();
        map.put(Constants.FIELDS, schemaFields);
    }

    private Options prepareOptions(Data data) {
        Options options = new Options();
        Query query = new Query();
        query.setServer(data.getSelectedSqlLanguage());
        query.setQuery(data.getSql());
        options.setQuery(query);

        return options;
    }

    private Validators prepareValidators() {
        return null;
    }

    private Search prepareSearch() {
        return null;
    }

    private OrderHints prepareOrderHints() {
        return null;
    }

    private Order prepareOrder() {
        return null;
    }

    private JoinHints prepareJoinHints() {
        return null;
    }

    private Columns prepareColumns() {
        return null;
    }

    private Fields prepareFields(List<SchemaMetaDataInfo> schemaMetaDataInfos) {
        Fields fields = new Fields();
        List<Field> fieldList = new ArrayList<>();

        for (SchemaMetaDataInfo info : schemaMetaDataInfos) {
            Field field = new Field();

            Meta meta = new Meta();
            meta.setDataType(info.getDataType());
            field.setMeta(meta);

            field.setDb(true);
            field.setDisplay(true);
            field.setName(info.getFieldName());
            field.setLabel(info.getFieldLabel());

            fieldList.add(field);
        }
        fields.setFieldList(fieldList);
        return fields;
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
                String columnLabel = md.getColumnLabel(i + 1);
                String dataType = md.getColumnTypeName(i + 1);
                String tableName = md.getTableName(i + 1);

                schemaMetaDataInfo.setFieldName(columnName);
                schemaMetaDataInfo.setFieldLabel(columnLabel);
                schemaMetaDataInfo.setDataType(dataType);
                schemaMetaDataInfo.setTableName(tableName);

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
