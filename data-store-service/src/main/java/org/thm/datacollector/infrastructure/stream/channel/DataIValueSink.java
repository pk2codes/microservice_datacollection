package org.thm.datacollector.infrastructure.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by patrick.welter on 3/15/17.
 * (c) Janitza Electronics
 */
public interface DataIValueSink {
    String INPUT = "dataIValue";

    @Input("dataIValue")
    SubscribableChannel dataIn();
}
