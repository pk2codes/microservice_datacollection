package org.thm.datacollector.infrastructure.serializer;

/**
 * Created by patrick.welter on 4/5/17.
 * (c) Janitza Electronics
 */
import java.nio.charset.Charset;
import java.util.Map;

import com.google.gson.Gson;
import org.apache.kafka.common.serialization.Serializer;

public class OnlineValueRequestSerializer<OnlineValueRequest> implements Serializer<OnlineValueRequest> {

    private final Gson gson = new Gson();

    @Override
    public void configure(final Map<String, ?> map, boolean b) {

    }

    @Override
    public byte[] serialize(String topic, OnlineValueRequest onlineValueRequest) {
        return gson.toJson(onlineValueRequest).getBytes(Charset.forName("UTF-8"));
    }

    @Override
    public void close() {

    }
}