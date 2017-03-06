package org.thm.datacollector.domain;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.stream.DataProviderRequestService;
import org.thm.datacollector.schedule.MessageSendJob;

import java.net.UnknownHostException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
public class Application implements InitializingBean{


    @Autowired
    DataProviderRequestService dataProviderRequestService;
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            final MessageSendJob job = new MessageSendJob(dataProviderRequestService);
            final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.scheduleAtFixedRate(job, 0, 1000, TimeUnit.MILLISECONDS);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
