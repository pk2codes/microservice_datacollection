package org.thm.datacollector.infrastructure.stream;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.model.onlinevalue.OnlineValueRequest;

import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
@EnableBinding(Source.class)
public class DataProviderRequestService {

    @Autowired
    private MessageChannel output;

    @ServiceActivator(inputChannel = Source.OUTPUT)
    public void requestDataProvider(final UUID uuid){
        output.send(MessageBuilder.withPayload(uuid).build());
    }

    @ServiceActivator(inputChannel = Source.OUTPUT)
    public void requestOnlineValueDataProvider(final OnlineValueRequest request){
        /*
        final Message<OnlineValueRequest> msg = MessageBuilder.withPayload(request).setHeader("content-type", "application/json").build();
        System.out.println("sending msg: "+  msg.toString() + msg.getHeaders().toString());
        output.send(msg);
        */
        Producer<String, OnlineValueRequest> producer = new KafkaProducer<>(getKafkaProps());
        producer.send(new ProducerRecord<String, OnlineValueRequest>("onlinevalue_request", "test", request) );;
        producer.close();
    }

    private Properties getKafkaProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.thm.datacollector.infrastructure.serializer.OnlineValueRequestSerializer");
        return props;
    }
}
