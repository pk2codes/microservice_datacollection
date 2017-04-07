package org.thm.datacollector.schedule;

import com.netflix.discovery.converters.Auto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.RangeAssignor;
import org.springframework.beans.factory.annotation.Autowired;
import org.thm.datacollector.infrastructure.stream.ReceiverService;
import org.thm.datacollector.model.AutoOnlineValueRecord;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Created by patrick.welter on 4/6/17.
 * (c) Janitza Electronics
 */
public class MessageReceiveJob  implements Runnable{

    private final KafkaConsumer<String, AutoOnlineValueRecord> m_Consumer;
    final String m_Topic = "onlinevalues";

    public MessageReceiveJob() {
        m_Consumer = new KafkaConsumer<>(getProperties());
    }

    @Override
    public void run() {
        m_Consumer.subscribe(Arrays.asList(m_Topic));
        final ConsumerRecords<String, AutoOnlineValueRecord> records = m_Consumer.poll(5000);
            final Iterator<ConsumerRecord<String, AutoOnlineValueRecord>> iterator =
                    records.iterator();
            while (iterator.hasNext()) {
                final ConsumerRecord<String, AutoOnlineValueRecord> next =
                        iterator.next();
                try {
                    System.out.println(next.value().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            };
    }

    private Properties getProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.deserializer",
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
                " org.thm.datacollector.infrastructure.serializer.AutoOnlineValueDeserializer");
        props.put("fetch.min.bytes", 1);
        props.put("heartbeat.interval.ms", 3000);
        props.put("max.partition.fetch.bytes", 1048576);
        props.put("session.timeout.ms", 10000);
        props.put("group.id", "auto-online-value-record-consumer");
        props.put("partition.assignment.strategy", "org.apache.kafka.clients.consumer.RoundRobinAssignor");
        return props;
    }
}
