package org.thm.datacollector.infrastructure.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/27/17.
 * (c) Janitza Electronics
 */
@Component
@EnableBinding(Source.class)
public class SenderService {
    @Autowired
    private MessageChannel output;

    @ServiceActivator(inputChannel= Source.OUTPUT)
    public void setLocationDeletedStatus(final UUID uuid) {
        output.send(MessageBuilder.withPayload(uuid).build());
    }
}
