package org.thm.datacollector.infrastructure.stream;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.persistence.DataStoreInfoRepository;

import java.net.UnknownHostException;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
@EnableBinding(Source.class)
public class SenderService {

    @Autowired
    private MessageChannel output;

    public void requestDataProvider(final UUID uuid){
        output.send(MessageBuilder.withPayload(uuid).build());
    }
}
