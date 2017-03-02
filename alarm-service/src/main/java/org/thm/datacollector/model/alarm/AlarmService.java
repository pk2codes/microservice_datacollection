package org.thm.datacollector.model.alarm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thm.datacollector.infrastructure.persistence.AlarmRepository;
import org.thm.datacollector.model.OnlineRecording;

import java.util.List;

/**
 * Created by patrick.welter on 2/28/17.
 * (c) Janitza Electronics
 */
public class AlarmService {
    final private static AlarmRepository repo = new AlarmRepository();
    final static private Logger log = LoggerFactory.getLogger(AlarmService.class);

    public static void watch(final OnlineRecording rec) {
        final List<Alarm> alarmList = repo.loadAll();
        alarmList.stream().filter( (a) -> {
            return a.getThreshold() < rec.getValue();
        }).forEach( (a) -> {
            log.info("Alarm for value %s value %d > %d", a.getIvalId(), rec.getValue(), a.getThreshold());
        });
    }
}
