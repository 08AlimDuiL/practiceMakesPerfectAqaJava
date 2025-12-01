package restfulbooker.utils;

import java.io.InputStream;
import java.util.Properties;

public class TestConfig {

    private static final Properties props = loadProperties();

    private static Properties loadProperties() {
        try (InputStream input = TestConfig.class.getClassLoader()
                .getResourceAsStream("local.properties")) {

            if (input == null) {
                throw new RuntimeException("Файл local.properties не найден в classpath!");
            }

            Properties properties = new Properties();
            properties.load(input);

            return properties;

        } catch (Exception e) {
            throw new RuntimeException("Ошибка загрузки local.properties: " + e.getMessage(), e);
        }
    }

    public static String getBaseUrl() {

        return props.getProperty("api.base.url");
    }

    public static String getUsername() {

        return props.getProperty("api.username");
    }

    public static String getPassword() {

        return props.getProperty("api.password");
    }
}