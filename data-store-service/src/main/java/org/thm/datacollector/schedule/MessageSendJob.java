package org.thm.datacollector.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.persistence.DataStoreResourceRepository;
import org.thm.datacollector.infrastructure.stream.DataProviderRequestService;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
public class MessageSendJob implements Runnable {

    @Autowired
    private DataProviderRequestService dataProviderRequestService;

    @Autowired
    private DataStoreResourceRepository repo;


    @Override
    public void run() {
        /*
        repo.loadAll().forEach((info) -> {
            dataProviderRequestService.requestDataProvider(info.getId());
        });
        */
    }
}
