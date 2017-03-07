package org.thm.datacollector.infrastructure.persistence.model;

import com.datastax.driver.core.utils.UUIDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.thm.datacollector.model.OnlineRecording;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@Data
@NoArgsConstructor
public class OnlineRecordingDBEntity {
    private UUID id;

    public LocalDate startTime;
    public LocalDate endTime;
    public double value;
    public String type;

    private Date convertUtilDateToSqlDate(final java.util.Date d) {
        return new Date(d.getTime());
    }

    public OnlineRecordingDBEntity(final OnlineRecording or) {
        setId(UUIDs.random());
        setValue(or.getValue());
        setStartTime(convertUtilDateToSqlDate(or.getStartTime()).toLocalDate());
        setEndTime(convertUtilDateToSqlDate(or.getEndTime()).toLocalDate());
        setType(or.getType());
    }
}
