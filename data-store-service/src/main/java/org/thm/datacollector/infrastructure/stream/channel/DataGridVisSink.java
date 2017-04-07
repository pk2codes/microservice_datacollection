package org.thm.datacollector.infrastructure.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Created by patrick.welter on 3/22/17.
 * (c) Janitza Electronics
 */
public interface DataGridVisSink {
    String INPUT = "gridvisIn";

    @Input("gridvisIn")
    SubscribableChannel dataIn();
}
