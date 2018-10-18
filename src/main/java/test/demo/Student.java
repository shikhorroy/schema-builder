package test.demo;

import test.pack.schemabuilder.schema.Schema;

import java.util.List;

public class Student {

    private String name;
    private int age;
    private List<String> skills;

    private Schema schema;

    // getters setters
    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}