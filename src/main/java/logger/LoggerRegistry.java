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
public class LoggerRegistry {

    public static final String SINK_TYPE = "sink_type";
    public static final String FILE = "FILE";
    private static final ConcurrentMap<LogLevel, Sink> LOG_LEVEL_VS_SINK = new ConcurrentHashMap<LogLevel, Sink>();

    static {

        try {
            // loading class to register
            ImmutableSet<ClassPath.ClassInfo> classes = ClassPath.from(Sink.class.getClassLoader()).getTopLevelClasses("sink.impl");
            for (ClassPath.ClassInfo aClass : classes) {
                Class.forName(aClass.load().getName());
            }
        } catch (IOException e) {
//            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
        }

        for (LogLevel logLevel : LogLevel.values()) {
            Properties properties = new Properties();
            try {
                properties.load(ClassLoaderUtil.getResourceAsStream(logLevel.getLoggerPropertyPath()));
            } catch (IOException e) {
//                Send Mail
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

    public static Sink getSinkForLogLevel(LogLevel logLevel) {
        return LOG_LEVEL_VS_SINK.get(logLevel);
    }
}
