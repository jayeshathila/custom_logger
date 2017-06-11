package logger;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import sink.Sink;
import sink.SinkRegistry;
import utils.ClassLoaderUtil;
import utils.PropertyUtil;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */

/**
 * This keeps registry of log_level vs sink implementation given in config file
 */
public class LoggerRegistry {

    public static final String SINK_TYPE = "sink_type";
    public static final String FILE = "FILE";
    private static final ConcurrentMap<LogLevel, Sink> LOG_LEVEL_VS_SINK = new ConcurrentHashMap<LogLevel, Sink>();

    static {

        try {
            // loading classes to register
            ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(Sink.class.getClassLoader()).getTopLevelClasses("sink.impl");
            for (ClassPath.ClassInfo aClass : classes) {
                Class.forName(aClass.load().getName());
            }
        } catch (IOException | ClassNotFoundException e) {
//            Send Dev Notification
        }

        // Iterating over log_levels to create configs and map sink implementations
        for (LogLevel logLevel : LogLevel.values()) {
            Properties properties = new Properties();
            try {
                properties.load(ClassLoaderUtil.getResourceAsStream(logLevel.getLoggerPropertyPath()));
            } catch (IOException e) {
//                Send Dev Notification
            }
            String value = PropertyUtil.getValueOrDefault(properties, SINK_TYPE, FILE);
            Sink sinkForType = SinkRegistry.getInstance().getSinkForType(value);
            if (sinkForType == null) {
                throw new IllegalArgumentException("Sink with name: " + value + " not registered");
            }

            Sink newSinkInstance = sinkForType.initializeNewObject(properties);
            LOG_LEVEL_VS_SINK.put(logLevel, newSinkInstance);
        }
    }

    /**
     * @param logLevel
     * @return Sink Implementaion for given log level
     */
    public static Sink getSinkForLogLevel(LogLevel logLevel) {
        return LOG_LEVEL_VS_SINK.get(logLevel);
    }
}
