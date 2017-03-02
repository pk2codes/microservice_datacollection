package org.thm.datacollector.infrastructure.stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.thm.datacollector.model.Application;
import org.thm.datacollector.model.alarm.AlarmService;
import org.thm.datacollector.model.OnlineRecording;

/**
 * Created by patrick.welter on 2/27/17.
 * (c) Janitza Electronics
 */
@EnableBinding(Sink.class)
public class ReceiverService {

    final private Logger log = LoggerFactory.getLogger(ReceiverService.class);

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void dataIncoming(final OnlineRecording rec) {
        log.info(String.format("received online recording %s", rec.toString()));
        AlarmService.watch(rec);

    }
}
