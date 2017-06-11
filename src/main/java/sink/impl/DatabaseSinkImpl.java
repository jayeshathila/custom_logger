package sink.impl;

import com.mongodb.MongoClient;
import logger.LogLevel;
import logger.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import sink.AbstractSink;
import sink.Sink;
import sink.SinkRegistry;
import utils.PropertyUtil;

import java.util.Date;
import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class DatabaseSinkImpl extends AbstractSink {


    public static final String DB_SINK = "DB";
    public static final String DEMO_LOG_COLLECTION = "demo_log";
    public static final int DEFAULT_PORT = 27017;
    public static final String DEFAULT_HOST = "127.0.0.1";
    public static final String DB_HOST_KEY = "db-host";
    public static final String DB_PORT_KEY = "db-port";

    private String hostIp;
    private int port;

    private MongoTemplate mongoTemplate = null;

    static {
        SinkRegistry.getInstance().registerIfAbsent(new DatabaseSinkImpl());
    }

    @Override
    public void pushLog(Message message) {
        mongoTemplate.save(new MongoLogBean(message));
    }

    @Override
    public String getSinkName() {
        return DB_SINK;
    }

    @Override
    public Sink initializeNewObject(Properties properties) {
        DatabaseSinkImpl databaseSink = new DatabaseSinkImpl();
        databaseSink.setSuperProperties(properties);
        databaseSink.setHostIp(PropertyUtil.getValueOrDefault(properties, DB_HOST_KEY, DEFAULT_HOST));
        String value = PropertyUtil.getValueOrDefault(properties, DB_PORT_KEY, String.valueOf(DEFAULT_PORT));
        if (StringUtils.isNumeric(value)) {
            databaseSink.setPort(Integer.valueOf(value));
        } else {
            databaseSink.setPort(DEFAULT_PORT);
        }
        databaseSink.setMongoTemplate();
        return databaseSink;
    }


    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private void setMongoTemplate() {
        MongoClient client = new MongoClient(this.hostIp, this.port);
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(client, DEMO_LOG_COLLECTION);
        this.mongoTemplate = new MongoTemplate(simpleMongoDbFactory);
    }

}

class MongoLogBean {

    LogLevel logLevel;
    String content;
    String namespace;
    Long createdTime;// epoch

    public MongoLogBean(Message message) {
        this.logLevel = message.getLogLevel();
        this.content = message.getContent();
        this.namespace = message.getNamespace();
        this.createdTime = new Date().getTime();
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}