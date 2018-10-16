package test.schemabuilder.schemabuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SchemabuilderApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SchemabuilderApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SchemabuilderApplication.class);
    }
}
