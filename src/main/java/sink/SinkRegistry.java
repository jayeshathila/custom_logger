package sink;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by jayeshathila
 * on 10/06/17.
 */
public class SinkRegistry {

    // Note: if we are using Spring we could have used autowiring rather than adding to map this way
    private final ConcurrentMap<String, Sink> NAME_VS_SINK = new ConcurrentHashMap<String, Sink>();

    private static SinkRegistry sinkRegistryInstance;

    private SinkRegistry() {
    }

    public static SinkRegistry getInstance() {
        SinkRegistry registry = sinkRegistryInstance;
        if (registry == null) {
            synchronized (SinkRegistry.class) {
                registry = sinkRegistryInstance;
                if (registry == null) {
                    sinkRegistryInstance = new SinkRegistry();
                    registry = sinkRegistryInstance;
                }

            }
        }
        return registry;
    }

    public Sink getSinkForType(String sinkName) {
        return NAME_VS_SINK.get(sinkName);
    }

    public void registerIfAbsent(Sink sink) {
        if (sink == null) {
            return;
        }
        String sinkName = sink.getSinkName();
        Sink presentSink = NAME_VS_SINK.get(sinkName);
        if (presentSink == null) {
            NAME_VS_SINK.put(sinkName, sink);
        }
    }
}
