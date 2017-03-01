package org.thm.datacollector.infrastructure.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.thm.datacollector.model.alarm.AlarmService;
import org.thm.datacollector.model.OnlineRecording;

/**
 * Created by patrick.welter on 2/27/17.
 * (c) Janitza Electronics
 */
@EnableBinding(Sink.class)
public class ReceiverService {


    @ServiceActivator(inputChannel=Sink.INPUT)
    public void dataIncoming(final OnlineRecording rec) {
        AlarmService.watch(rec);

    }
}
