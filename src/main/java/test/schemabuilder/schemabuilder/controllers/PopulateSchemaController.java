package test.schemabuilder.schemabuilder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/PopulateSchema")
public class PopulateSchemaController {

    @RequestMapping(value = "/populate/", method = RequestMethod.GET)
    public String populate() {
        System.out.println("populate method...");
        return "populate";
    }
}
