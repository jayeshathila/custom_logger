package logger;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public enum LogLevel {

    DEBUG("debug", "debug.logger.properties"),
    INFO("info", "info.logger.properties"),
    WARN("warn", "warn.logger.properties"),
    ERROR("error", "error.logger.properties"),
    FATAL("fatal", "fatal.logger.properties");

    private final String levelName;
    private final String loggerPropertyPath;

    LogLevel(String name, String loggerPropertyPath) {
        this.levelName = name;
        this.loggerPropertyPath = loggerPropertyPath;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getLoggerPropertyPath() {
        return loggerPropertyPath;
    }
}
