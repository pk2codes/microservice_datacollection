package org.thm.datacollector.infrastructure.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.persistence.DataStoreInfoRepository;

import javax.sound.midi.Receiver;
import java.net.UnknownHostException;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/27/17.
 * (c) Janitza Electronics
 */
@EnableBinding(Sink.class)
public class ReceiverService {

    private DataStoreInfoRepository repo =new DataStoreInfoRepository();


    @ServiceActivator(inputChannel=Sink.INPUT)
    public void locationStateChanged(final UUID uuid) {
        System.out.println("deleting: " + uuid.toString());
        repo.removeById(uuid);
    }
}
