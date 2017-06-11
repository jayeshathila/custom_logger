package utils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class SimpleDateFormatPatternCache {

    private static Map<String, SimpleDateFormat> PATTERN_VS_SIMPLE_DATE_FORMAT = new HashMap<String, SimpleDateFormat>();

    public static SimpleDateFormat getOrCreateSimpleDateFormat(String pattern) {
        if (PATTERN_VS_SIMPLE_DATE_FORMAT.get(pattern) != null) {
            return PATTERN_VS_SIMPLE_DATE_FORMAT.get(pattern);
        }

        SimpleDateFormat format = new SimpleDateFormat(pattern);
        PATTERN_VS_SIMPLE_DATE_FORMAT.put(pattern, format);
        return format;
    }
}
