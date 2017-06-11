package sink;

import logger.Message;

import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */

/**
 * NOTE: Evey Sink impl will have to register itself in registry using static block same as:
      static {
             SinkRegistry.getInstance().registerIfAbsent(new SinkImpl());
          }
 */

public interface Sink {

    /**
     * @param message: this is the bean which contains all the information about the log
     *                 Functionality: Implementation of pushLog is expected to push and format the log in appropriate Sink
     */
    void pushLog(Message message);

    /**
     * This is used to register sink against its name
     */
    String getSinkName();

    /**
     *
     * @param properties: this contains all the consumable properties read from the config files in resource
     *                  Its implementation is expected to Create and Return a new Sink by setting properties in their
     *                  usable fields
     * @return: new SinkImpl()
     */
    Sink initializeNewObject(Properties properties);
}
