package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thm.datacollector.model.Alarm;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
public class AlarmRepository extends AbstractRepository {
    private final Logger LOG = LoggerFactory.getLogger(AlarmRepository.class);

    private void updateAlarm(final UUID uuid, final String ivalStr, final double threshold) {
        ops.update(new Alarm(uuid, ivalStr, threshold));
    }

    public void createAlarm(final String ivalStr, final double threshold) {
        ops.insert(new Alarm(UUIDs.random(), ivalStr, threshold));
    }

    public AlarmRepository() {
        super("alarm_service", "alarm", Alarm.class);
    }

}
