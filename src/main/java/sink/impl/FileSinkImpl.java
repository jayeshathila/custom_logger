package sink.impl;


import logger.LogLevel;
import logger.Message;
import sink.AbstractSink;
import sink.Sink;
import sink.SinkRegistry;
import utils.PropertyUtil;
import utils.SimpleDateFormatPatternCache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
@SuppressWarnings("ALL")
public class FileSinkImpl extends AbstractSink {


    public static final String FILE_SINK = "FILE";

    private String fileLocation;
    private transient FileWriter fileWriter;

    // Registering implementation to sink registry.
    static {
        SinkRegistry.getInstance().registerIfAbsent(new FileSinkImpl());
    }

    private FileSinkImpl() {
    }

    /**
     * @param message: this is the bean which contains all the information about the log
     *                 This impl created nested direcotries and files if not exists and appends log to the file
     */
    @Override
    public void pushLog(Message message) {
        String content = message.getContent();
        LogLevel logLevel = message.getLogLevel();
        try {

            if (fileWriter == null) {
                File file = new File(fileLocation);
                file.getParentFile().mkdirs();
                file.createNewFile(); // will create new file only if it doesn't exist
                fileWriter = new FileWriter(fileLocation, true);
            }

            SimpleDateFormat dateFormat = SimpleDateFormatPatternCache.getOrCreateSimpleDateFormat(getTimeFormat());
            String time = dateFormat.format(new Date());

            //appends the string to the file
            fileWriter.write("\n [" + time + "],\t [ Thread: " + Thread.currentThread().getName() + "_" + Thread.currentThread().getId() + "],\t [Level:  " + logLevel.getLevelName() + " , " + message.getNamespace() + " ] \n "
                    + content + "\n");

            // Not closing and only flushing so as to keep open the connection as logs are continuous stream
            fileWriter.flush();
        } catch (IOException e) {
//          Send Dev Notification
        }
    }


    @Override
    public String getSinkName() {
        return FILE_SINK;
    }

    @Override
    public Sink initializeNewObject(Properties properties) {
        FileSinkImpl fileSink = new FileSinkImpl();
        fileSink.setSuperProperties(properties);
        fileSink.setFileLocation(PropertyUtil.getValueOrDefault(properties, "file_location", "/var/log/app/temp.log"));
        return fileSink;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
