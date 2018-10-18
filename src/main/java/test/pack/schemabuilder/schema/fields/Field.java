package test.pack.schemabuilder.schema.fields;

public class Field {
    private String name;
    private boolean db;
    private boolean display;
    private Meta meta;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDb() {
        return db;
    }

    public void setDb(boolean db) {
        this.db = db;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
