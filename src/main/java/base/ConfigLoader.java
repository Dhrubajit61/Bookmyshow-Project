package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static Properties prop = null;

    static {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config/config.properties");
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load config.properties", e);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
