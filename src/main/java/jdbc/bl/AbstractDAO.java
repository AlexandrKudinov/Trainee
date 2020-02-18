package jdbc.bl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class AbstractDAO {
    private static String DB_DRIVER;
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;
    private static PropertyManager propertyManager;
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            propertyManager = new PropertyManager();
            try {
                DB_DRIVER = propertyManager.getDriver();
                DB_URL = propertyManager.getUrl();
                DB_USERNAME = propertyManager.getUsername();
                DB_PASSWORD = propertyManager.getPassword();
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                System.out.println("Connection OK");
            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static class PropertyManager {

        private String getDriver() throws IOException {
            return readProperty("DB_DRIVER");
        }

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
