package logger;

import sink.Sink;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class Logger {

    public static void log(Message message) {
        if (message == null) {
            return;
        }

        LogLevel logLevel = message.getLogLevel();

        Sink sink = LoggerRegistry.getSinkForLogLevel(logLevel);
        sink.pushLog(message);
    }


    public static void main(String[] args) {
        Message message = new Message("Voila", LogLevel.DEBUG, "test namespace");
        Logger.log(message);
    }

}
