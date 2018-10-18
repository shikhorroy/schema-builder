package test.pack.schemabuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class QueryUtils {

    private DataSource dataSource;

    public QueryUtils() {
//        this.prepareDataSource(); // ~ WARNING: don't uncomment this line !!! read the comment below:
        /***************************************************************************************************
         * prepareDataSource() method is used to configure database connection. I called this
         * method at the time of instantiating of a QueryUtilitis object creation (at the time
         * of constructor call). Here, the method prepareDataSource() uses some properties values,
         * e.g. 'spring.datasource.url', 'spring.datasource.username' etc. A point need to remember,
         * the properties values are only loaded after the bean creation (object created by Spring,
         * not using new keyword). And for this, the properties values which are used from prepareDataSource()
         * method called from constructor are initially null which creates problem and make the project
         * not to start! one of the solution is to use prepareDataSource() method as @PostConstruct, i.e. the
         * method will call after object creation (constructor call).
         ***************************************************************************************************/
    }

    private DataSource getDataSource() {
        return dataSource;
    }

    private void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @PostConstruct
    private void prepareDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        this.setDataSource(ds);
    }

    ResultSet query(String query) {
        ResultSet rs = null;
        try {
            Connection connection = this.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
