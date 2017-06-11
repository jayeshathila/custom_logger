package sink;

import utils.PropertyUtil;

import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */

/**
 * All the common reusable code of Sink implementations will come here.
 * For example: we will have to set timeFormat in every sink impl so that also comes here.
 */
public abstract class AbstractSink implements Sink {

    private String timeFormat;

    public String getTimeFormat() {
        return timeFormat;
    }

    protected void setSuperProperties(Properties properties) {
        timeFormat = PropertyUtil.getValueOrDefault(properties, "ts_format", "dd:mm:yyyy hh:mm:ss");
    }
}
