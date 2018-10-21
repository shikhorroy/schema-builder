package test.schemabuilder.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import test.pack.schemabuilder.QueryUtils;
import test.pack.schemabuilder.models.Data;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.ResultSet;

@Controller
@RequestMapping(value = "/QueryExecutor")
public class QueryExecutor {

    @Autowired
    QueryUtils queryUtils;

    @ResponseBody
    @RequestMapping(value = "/query/", method = RequestMethod.POST)
    public String populate(HttpServletRequest request) {
        String query = "";
        Data dataObj = null;
        try {
            String data = request.getParameter("data");
            dataObj = new ObjectMapper().readValue(data, Data.class);
            query = dataObj.getSql();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResultSet rs = queryUtils.query(query);
        return "Query Execution Successful!";
    }
}
