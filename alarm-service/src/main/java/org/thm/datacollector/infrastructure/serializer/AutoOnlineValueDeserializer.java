package org.thm.datacollector.infrastructure.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.kafka.common.serialization.Deserializer;
import org.thm.datacollector.model.AutoOnlineValueRecord;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * Created by patrick.welter on 4/6/17.
 * (c) Janitza Electronics
 */
public class AutoOnlineValueDeserializer implements Deserializer<AutoOnlineValueRecord>{
    private final Gson m_Gson = new GsonBuilder().serializeSpecialFloatingPointValues()
            .create();

    private Class<AutoOnlineValueRecord> m_DeserializedClass = AutoOnlineValueRecord.class;

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public AutoOnlineValueRecord deserialize(String s, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return m_Gson.fromJson(new String(bytes, StandardCharsets.UTF_8),
                m_DeserializedClass);
    }

    @Override
    public void close() {

    }
}
