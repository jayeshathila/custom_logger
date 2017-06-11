package sink;

import utils.PropertyUtil;

import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
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
