package org.thm.datacollector.infrastructure.stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.rest.Client;
import org.thm.datacollector.infrastructure.stream.channel.DataSink;
import org.thm.datacollector.model.OnlineRecording;

import java.util.UUID;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@EnableBinding(DataSink.class)
public class DataReceiver {


    private final Logger log  = Logger.getLogger(LocationStateReceiverService.class);

    @Autowired
    private Client.SaveRecording saveRecording;

    @ServiceActivator(inputChannel = DataSink.INPUT)
    public void dataReceived(final OnlineRecording rec) {
        log.info("received:" + rec.toString());
        saveRecording.dataLocation(rec);
    }
}
