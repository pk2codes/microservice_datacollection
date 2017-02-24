package thm.datacollector.infrastructure.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.ServiceActivator;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@EnableBinding(Sink.class)
public class Receiver {
    private final Logger logger = Logger.getLogger(Receiver.class.getName());
    @ServiceActivator(inputChannel=Sink.INPUT)
    public void receive(final UUID uuid) {
        System.out.println(uuid.toString());
    }

}
