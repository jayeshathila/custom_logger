package logger;

import sink.Sink;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class Logger {

    //Static method to log to sink based on log level defined in method
    public static void log(Message message) {
        if (message == null) {
            return;
        }

        LogLevel logLevel = message.getLogLevel();

        Sink sink = LoggerRegistry.getSinkForLogLevel(logLevel);
        sink.pushLog(message);
    }

}
