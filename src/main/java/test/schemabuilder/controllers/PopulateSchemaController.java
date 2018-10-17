package test.schemabuilder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import test.schemabuilder.services.PopulateSchemaService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/PopulateSchema")
public class PopulateSchemaController {

    @RequestMapping(value = "/populate/", method = RequestMethod.GET)
    public String populate() {
        System.out.println("populate method...");
        return "populate";
    }

    @Autowired
    PopulateSchemaService populateSchemaService;

    @ResponseBody
    @RequestMapping(value = "/populate/", method = RequestMethod.POST)
    public Object populateSchema(HttpServletRequest request) {
        populateSchemaService.build(request.getParameter("data"));
        return "success !";
    }
}
