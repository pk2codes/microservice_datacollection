package org.thm.datacollector.schedule;

import org.springframework.stereotype.Component;
import org.thm.datacollector.infrastructure.persistence.AlarmRepository;
import org.thm.datacollector.infrastructure.stream.SenderService;

import java.net.UnknownHostException;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@Component
public class MessageSendJob implements Runnable {

    private final SenderService senderService;
    private final AlarmRepository repo;

    public MessageSendJob(final SenderService senderService) throws UnknownHostException {
        this.senderService = senderService;
        this.repo = new AlarmRepository();
    }

    @Override
    public void run() {
        repo.loadAll().forEach((alarm) -> {
            senderService.requestDataProvider(alarm.getLocationId());
        });
    }
}
