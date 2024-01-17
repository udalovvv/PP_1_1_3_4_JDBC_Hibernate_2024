package jm.task.core.jdbc.util;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@NoArgsConstructor
public class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (var inputStream =  PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
