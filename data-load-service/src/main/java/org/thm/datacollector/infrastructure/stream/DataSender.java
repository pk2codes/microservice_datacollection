package org.thm.datacollector.infrastructure.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.thm.datacollector.model.ExampleData;
import org.thm.datacollector.model.OnlineRecording;

/**
 * Created by patrick.welter on 2/24/17.
 * (c) Janitza Electronics
 */
@Component
@EnableBinding(Source.class)
public class DataSender {
    @Autowired
    private MessageChannel output;

    @ServiceActivator(inputChannel= Source.OUTPUT)
    public void send(final OnlineRecording data) {
        output.send(MessageBuilder.withPayload(data).build());
    }

}
