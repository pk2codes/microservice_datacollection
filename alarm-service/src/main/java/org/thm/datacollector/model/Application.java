package org.thm.datacollector.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.stream.ReceiverService;
import org.thm.datacollector.infrastructure.stream.SenderService;
import org.thm.datacollector.schedule.MessageReceiveJob;
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


    @Override
    public void afterPropertiesSet() throws Exception {
        try {
          //  setupRequestScheduler();
            setupResponseScheduler();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    private void setupRequestScheduler() throws UnknownHostException{
        final MessageSendJob job = new MessageSendJob();
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(job, 0, 1000, TimeUnit.MILLISECONDS);
        log.info(String.format("periodically request service for data provider with: %d Period as %s and %d delay",
                PERIOD, TU.toString(), DELAY));
    }

    private void setupResponseScheduler() throws UnknownHostException{
        final MessageReceiveJob job = new MessageReceiveJob();
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(job, 0, 1000, TimeUnit.MILLISECONDS);
        log.info(String.format("periodically receive service for data provider with: %d Period as %s and %d delay",
                PERIOD, TU.toString(), DELAY));
    }
}
