package test.pack.schemabuilder;

import org.springframework.beans.factory.annotation.Autowired;

public class SchemaBuilderService {

    @Autowired
    private SchemaBuilder schemaBuilder;

    public final void build(String data) {
        schemaBuilder.setRawData(data);
        schemaBuilder.buildSchema();
    }
}
