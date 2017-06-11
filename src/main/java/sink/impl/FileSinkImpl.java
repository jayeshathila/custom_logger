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
public class FileSinkImpl extends AbstractSink {


    public static final String FILE_SINK = "FILE";

    private String fileLocation;

    static {
        SinkRegistry.getInstance().registerIfAbsent(new FileSinkImpl());
    }

    private FileSinkImpl() {
    }

    @Override
    public void pushLog(Message message) {
        String content = message.getContent();
        LogLevel logLevel = message.getLogLevel();
        FileWriter fw = null; //the true will append the new data
        try {
            File file = new File(fileLocation);
            file.createNewFile(); // will create new file only if it doesn't exist
            fw = new FileWriter(fileLocation, true);
            SimpleDateFormat dateFormat = SimpleDateFormatPatternCache.getOrCreateSimpleDateFormat(getTimeFormat());
            String time = dateFormat.format(new Date());
            //appends the string to the file
            fw.write("\n [" + time + "],\t [ Thread: " + Thread.currentThread().getName() + "_" + Thread.currentThread().getId() + "],\t [Level:  " + logLevel.getLevelName() + " , " + message.getNamespace() + " ] \n "
                    + content + "\n");
            fw.close();
        } catch (IOException e) {
//          Send Mail
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

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }
}
