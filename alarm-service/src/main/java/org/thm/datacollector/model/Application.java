package org.thm.datacollector.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.stream.SenderService;
import org.thm.datacollector.schedule.MessageSendJob;

import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by patrick.welter on 3/1/17.
 * (c) Janitza Electronics
 */
@Component
public class Application implements InitializingBean {
    final private Logger log = LoggerFactory.getLogger(Application.class);
    final private int PERIOD = 1000;
    final private TimeUnit TU = TimeUnit.MILLISECONDS;
    final private int DELAY = 0;
    @Autowired
    SenderService senderService;
    @Override
    public void afterPropertiesSet() throws Exception {
        try {

            final MessageSendJob job = new MessageSendJob(senderService);
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(job, 0, 1000, TimeUnit.MILLISECONDS);
            log.info(String.format("periodically request service for data provider with: %d Period as %s and %d delay",
                    PERIOD, TU.toString(), DELAY));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
