package org.thm.datacollector.infrastructure.stream;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.ServiceActivator;
import org.thm.datacollector.infrastructure.stream.channel.DataGridVisSink;

/**
 * Created by patrick.welter on 3/22/17.
 * (c) Janitza Electronics
*/
@EnableBinding(DataGridVisSink.class)
public class GridVisReceiver {


    private final Logger log  = Logger.getLogger(GridVisReceiver.class);

    @ServiceActivator(inputChannel = DataGridVisSink.INPUT)
    public void dataReceived(final String msg) {
        log.info("received:" + msg);

    }
}
