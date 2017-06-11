package logger;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class Message {

    private String content;
    private LogLevel logLevel;
    private String sinkTypeName;
    private String namespace;

    /**
     * Note: Either sinkTypeName or Loglevel need to set to get default log level
     */

    public Message(String content, LogLevel logLevel, String namespace) {
        this.content = content;
        this.logLevel = logLevel;
        this.namespace = namespace;
    }

    public Message(String content, String sinkTypeName, LogLevel logLevel) {
        this.content = content;
        this.logLevel = logLevel;
        this.sinkTypeName = sinkTypeName;
    }

    public Message(String content, LogLevel logLevel, String namespace, String sinkTypeName) {
        this.content = content;
        this.logLevel = logLevel;
        this.namespace = namespace;
        this.sinkTypeName = sinkTypeName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel LogLevel) {
        this.logLevel = LogLevel;
    }

    public String getSinkTypeName() {
        return sinkTypeName;
    }

    public void setSinkTypeName(String sinkTypeName) {
        this.sinkTypeName = sinkTypeName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }


    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", logLevel=" + logLevel +
                ", sinkTypeName='" + sinkTypeName + '\'' +
                ", namespace='" + namespace + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        if (logLevel != message.logLevel) return false;
        if (sinkTypeName != null ? !sinkTypeName.equals(message.sinkTypeName) : message.sinkTypeName != null) return false;
        return !(namespace != null ? !namespace.equals(message.namespace) : message.namespace != null);

    }

    @Override
    public int hashCode() {
        int result = content != null ? content.hashCode() : 0;
        result = 31 * result + (logLevel != null ? logLevel.hashCode() : 0);
        result = 31 * result + (sinkTypeName != null ? sinkTypeName.hashCode() : 0);
        result = 31 * result + (namespace != null ? namespace.hashCode() : 0);
        return result;
    }


}
