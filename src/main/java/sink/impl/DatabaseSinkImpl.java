package sink.impl;

import logger.Message;
import sink.AbstractSink;
import sink.Sink;
import sink.SinkRegistry;
import utils.PropertyUtil;

import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class DatabaseSinkImpl extends AbstractSink {


    public static final String DB_SINK = "DB";

    private String hostIp;
    private String port;

    static {
        SinkRegistry.getInstance().registerIfAbsent(new DatabaseSinkImpl());
    }

    @Override
    public void pushLog(Message message) {
        // For demonstration purpose , I have implemented FileSinkImpl
    }

    @Override
    public String getSinkName() {
        return DB_SINK;
    }

    @Override
    public Sink initializeNewObject(Properties properties) {
        DatabaseSinkImpl databaseSink = new DatabaseSinkImpl();
        databaseSink.setSuperProperties(properties);
        databaseSink.setHostIp(PropertyUtil.getValueOrDefault(properties, "db-host", "127.0.0.1"));
        databaseSink.setPort(PropertyUtil.getValueOrDefault(properties, "db-port", "27017"));
        return databaseSink;
    }


    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }


}
