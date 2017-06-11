package utils;

import java.io.InputStream;

/**
 * Created by jayeshathila
 * on 11/06/17.
 */
public class ClassLoaderUtil {

    public static InputStream getResourceAsStream(String resourceName) {

        InputStream resourceAsStream = null;

        try {
            resourceAsStream = ClassLoaderUtil.class.getClassLoader().getResourceAsStream(resourceName);
        } catch (Exception e) {

        }
        if (resourceAsStream == null) {
            resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
        }
        if (resourceAsStream == null) {
            resourceAsStream = ClassLoader.getSystemResourceAsStream(resourceName);
        }

        if (resourceAsStream == null) {
            throw new IllegalArgumentException("Cannot find resource '" + resourceName + "'");
        }

        return resourceAsStream;
    }
}
