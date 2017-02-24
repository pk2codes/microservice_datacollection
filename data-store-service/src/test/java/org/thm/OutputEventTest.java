package org.thm;

import com.datastax.driver.core.utils.UUIDs;
import com.netflix.discovery.converters.Auto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringRunner;
import org.thm.datacollector.infrastructure.stream.SenderService;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutputEventTest {
    @Autowired
    SenderService senderService;
    @Test
    public void sendMessage() {
        senderService.requestDataProvider(UUIDs.random());
    }
}
 */