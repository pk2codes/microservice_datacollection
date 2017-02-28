package thm.datacollector.infrastructure.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;
import thm.datacollector.model.DataLoader;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@EnableBinding(Sink.class)
public class RequestReceiver {

    @Autowired
    DataLoader dataLoader;
    private final Logger logger = Logger.getLogger(RequestReceiver.class.getName());

    @ServiceActivator(inputChannel=Sink.INPUT)
    public void receive(final UUID uuid) {
        dataLoader.loadDataById(uuid);
    }

}
