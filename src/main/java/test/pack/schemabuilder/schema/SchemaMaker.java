package test.pack.schemabuilder.schema;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import test.pack.schemabuilder.schema.fields.Field;
import test.pack.schemabuilder.schema.fields.Fields;
import test.pack.schemabuilder.schema.fields.Meta;
import test.pack.schemabuilder.schema.options.Options;
import test.pack.schemabuilder.schema.options.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SchemaMaker {

    private static void scheme() {
        Schema schema = new Schema();

        Fields fields = new Fields();
        List<Field> fields1 = new ArrayList<>();

        Field field = new Field();
        Meta meta = new Meta();
        meta.setDataType("VARCHAR");
        field.setDb(true);
        field.setDisplay(true);
        field.setName("testName");
        field.setMeta(meta);
        fields1.add(field);

        field = new Field();
        meta = new Meta();
        meta.setDataType("INTEGER");
        field.setDb(true);
        field.setDisplay(true);
        field.setName("testName 2");
        field.setMeta(meta);
        fields1.add(field);

        fields.setFieldList(fields1);
        schema.setFields(fields);

//        schema.setColumns(new Columns());
//        schema.setJoinHints(new JoinHints());
//        schema.setOrder(new Order());
//        schema.setOrderHints(new OrderHints());
//        schema.setSearch(new Search());
//        schema.setValidators(new Validators());

        Options options = new Options();
        Query query = new Query();
        query.setServer("mssql");
        options.setQuery(query);
        schema.setOptions(options);

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setPrettyFlow(true);
//
        ObjectMapper oMapper = new ObjectMapper();
        Map<String, Object> map = oMapper.convertValue(schema, Map.class);

        for (Map.Entry e : map.entrySet()) {
            if (e.getValue() == null) e.setValue("CURLY_BRACKET");
        }

        System.out.println(map);
//
        Yaml yaml = new Yaml(dumperOptions);
        String output = yaml.dump(map);
//        output = output.replace("fieldList:", "");
        output = output.replace("CURLY_BRACKET", "{}");

        System.out.println(output);

//        String regex = "null";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(output);
//
//        while (matcher.find()) {
//            System.out.println(matcher.start() + " " + matcher.end());
//        }
    }
}
