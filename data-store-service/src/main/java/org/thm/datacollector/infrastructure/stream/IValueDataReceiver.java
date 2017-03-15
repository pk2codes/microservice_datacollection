package org.thm.datacollector.infrastructure.stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.thm.datacollector.infrastructure.rest.Client;
import org.thm.datacollector.infrastructure.stream.channel.DataIValueSink;
import org.thm.datacollector.infrastructure.stream.channel.DataResourceSink;
import org.thm.datacollector.model.OnlineRecording;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@EnableBinding(DataIValueSink.class)
public class IValueDataReceiver {


    private final Logger log  = Logger.getLogger(LocationStateReceiverService.class);

    @Autowired
    private Client.SaveRecording saveRecording;

    @ServiceActivator(inputChannel = DataIValueSink.INPUT)
    public void dataReceived(final OnlineRecording rec) {
        log.info("received:" + rec.toString());
        //saveRecording.dataLocation(rec);
    }
}
