package org.thm.datacollector.infrastructure.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.thm.datacollector.model.Application;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
@EnableBinding(Source.class)
public class SenderService {
    final private Logger log = LoggerFactory.getLogger(SenderService.class);

    @Autowired
    private MessageChannel output;

    @ServiceActivator(inputChannel = Source.OUTPUT)
    public void requestDataProvider(final UUID uuid){
        log.info(String.format("request data provider for UUID %s", uuid.toString()));
        output.send(MessageBuilder.withPayload(uuid).build());
    }
}
