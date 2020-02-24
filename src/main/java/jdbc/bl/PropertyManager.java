package jdbc.bl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    String getDriver() throws IOException {
        return readProperty("DB_DRIVER");
    }

    String getUrl() throws IOException {
        return readProperty("DB_URL");
    }

    String getUsername() throws IOException {
        return readProperty("DB_USERNAME");
    }

    String getPassword() throws IOException {
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
