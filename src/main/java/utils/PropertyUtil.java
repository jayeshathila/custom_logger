package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class PropertyUtil {

    public static Properties loadPropertyResource(String resourceName) {
        InputStream resourceAsStream = PropertyUtil.class.getClassLoader().getResourceAsStream(resourceName);
        try {
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties;
        } catch (IOException e) {
//          SendMail()
        }
        return null;
    }


    public static String getValueOrDefault(Properties properties, String key, String defaultValue) {
        if (properties == null) {
            return defaultValue;
        }

        return properties.get(key) == null ? defaultValue : String.valueOf(properties.get(key));
    }

}
