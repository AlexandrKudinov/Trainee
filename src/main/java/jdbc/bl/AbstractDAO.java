package jdbc.bl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

public abstract class AbstractDAO {
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    private static PropertyManager propertyManager = new PropertyManager();
    private static Connection connection;
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try {
            DB_URL = propertyManager.getUrl();
            DB_USERNAME = propertyManager.getUsername();
            DB_PASSWORD = propertyManager.getPassword();
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    private static class PropertyManager {
        private String getUrl() throws IOException {
            return readProperty("DB_URL");
        }

        private String getUsername() throws IOException {
            return readProperty("DB_USERNAME");
        }

        private String getPassword() throws IOException {
            return readProperty("DB_PASSWORD");
        }

        private String readProperty(String name) throws IOException {
            Properties properties = new Properties();
            InputStream inputStream =
                    this.getClass().getClassLoader().getResourceAsStream("local.properties");
            properties.load(inputStream);
            return properties.getProperty(name);
        }
    }
}
