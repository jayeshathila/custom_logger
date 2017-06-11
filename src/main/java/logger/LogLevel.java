package logger;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public enum LogLevel {

    DEBUG(0, "debug", "debug.logger.properties"),
    INFO(1, "info", "info.logger.properties"),
    WARN(2, "warn", "warn.logger.properties"),
    ERROR(3, "error", "error.logger.properties"),
    FATAL(4, "fatal", "fatal.logger.properties");

    private final int levelNumber;
    private final String levelName;
    private final String loggerPropertyPath;

    LogLevel(int levelNumber, String name, String loggerPropertyPath) {
        this.levelNumber = levelNumber;
        this.levelName = name;
        this.loggerPropertyPath = loggerPropertyPath;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public String getLevelName() {
        return levelName;
    }

    public String getLoggerPropertyPath() {
        return loggerPropertyPath;
    }
}
