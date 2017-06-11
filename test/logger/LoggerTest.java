package logger;

import org.junit.Test;

/**
 * Created by jayeshathila
 * on 11/06/17.
 */
public class LoggerTest {

    @Test
    public void testFileSinkLog() {
        Message message = new Message("Voila to file sink", LogLevel.DEBUG, LoggerTest.class.getSimpleName());
        Logger.log(message);
    }

    @Test
    public void testDatabaseSinkLog() {
        Message message = new Message("Voila to mongo sink", LogLevel.ERROR, LoggerTest.class.getSimpleName());
        Logger.log(message);
    }
}