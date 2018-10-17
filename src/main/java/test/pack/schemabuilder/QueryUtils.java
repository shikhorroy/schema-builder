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
//        this.prepareDataSource(); // ~ WARNING: don't uncomment this line !!!
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
