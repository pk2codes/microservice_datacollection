package org.thm.datacollector.infrastructure.persistence.model;

import lombok.Data;
import org.thm.datacollector.model.OnlineRecording;

import java.util.UUID;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@Data
public class OnlineRecordingDBEntity extends OnlineRecording{
    private UUID id;

    public OnlineRecordingDBEntity(final UUID uuid, final OnlineRecording or) {
        setId(uuid);
        setValue(or.getValue());
        setEndTime(or.getEndTime());
        setStartTime(or.getStartTime());
        setType(or.getType());
    }
}
