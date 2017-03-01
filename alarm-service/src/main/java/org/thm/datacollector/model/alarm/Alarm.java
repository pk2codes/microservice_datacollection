package org.thm.datacollector.model.alarm;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */

@Table
@Data
@AllArgsConstructor
public class Alarm {
    @PrimaryKey
    private UUID id;
    private String ivalId;
    private double threshold;
    private UUID locationId;

}
