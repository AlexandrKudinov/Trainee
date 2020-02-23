package jdbc.bl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

public class Config {
    private String DB_DRIVER;
    private String DB_URL;
    private String DB_USERNAME;
    private String DB_PASSWORD;
    private HikariDataSource ds;

    public Config() {
        PropertyManager propertyManager = new PropertyManager();
        HikariConfig config = new HikariConfig();
        try {
            DB_DRIVER = propertyManager.getDriver();
            DB_URL = propertyManager.getUrl();
            DB_USERNAME = propertyManager.getUsername();
            DB_PASSWORD = propertyManager.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setDriverClassName(DB_DRIVER);
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        ds = new HikariDataSource(config);
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection =   ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
