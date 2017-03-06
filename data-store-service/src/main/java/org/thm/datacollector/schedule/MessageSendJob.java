package org.thm.datacollector.schedule;

import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.persistence.DataStoreInfoRepository;
import org.thm.datacollector.infrastructure.stream.DataProviderRequestService;

import java.net.UnknownHostException;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
public class MessageSendJob implements Runnable {

    private final DataProviderRequestService dataProviderRequestService;
    private final DataStoreInfoRepository repo;

    public MessageSendJob(final DataProviderRequestService dataProviderRequestService) throws UnknownHostException {
        this.dataProviderRequestService = dataProviderRequestService;
        this.repo = new DataStoreInfoRepository();
    }

    @Override
    public void run() {
        repo.loadAll().forEach((info) -> {
            dataProviderRequestService.requestDataProvider(info.getId());
        });
    }
}
