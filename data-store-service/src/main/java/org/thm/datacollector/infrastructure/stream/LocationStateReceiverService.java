package org.thm.datacollector.infrastructure.stream;

import org.apache.log4j.Logger;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.thm.datacollector.infrastructure.persistence.DataStoreInfoRepository;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/27/17.
 * (c) Janitza Electronics
 */
@EnableBinding(Sink.class)
public class LocationStateReceiverService {

    private final Logger log  = Logger.getLogger(LocationStateReceiverService.class);
    private DataStoreInfoRepository repo =new DataStoreInfoRepository();



    @ServiceActivator(inputChannel=Sink.INPUT)
    public void locationStateChanged(final UUID uuid) {
        log.info("deleting: " + uuid.toString());
        repo.removeById(uuid);
    }
}
