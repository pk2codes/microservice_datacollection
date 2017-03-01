package org.thm.datacollector.infrastructure.persistence;

import com.datastax.driver.core.utils.UUIDs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thm.datacollector.model.alarm.Alarm;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
public class AlarmRepository extends AbstractRepository<Alarm> {
    private final Logger LOG = LoggerFactory.getLogger(AlarmRepository.class);

    public void createAlarm(final String ivalStr, final double threshold, final UUID locationId) {
        ops.insert(new Alarm(UUIDs.random(), ivalStr, threshold, locationId));
    }

    public AlarmRepository() {
        super("alarm_service", "alarm", Alarm.class);
    }

}
