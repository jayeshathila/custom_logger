package sink;

import logger.LogLevel;
import logger.Message;

import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public interface Sink {

    void pushLog(Message message);

    String getSinkName();

    LogLevel fetchSetLogLevel();

    Sink initializeNewObject(Properties properties);
}
