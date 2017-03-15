package org.thm.datacollector.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by patrick.welter on 3/15/17.
 * (c) Janitza Electronics
 */
@Table
@Data
@AllArgsConstructor
public class DataStoreIValInfo {
    @PrimaryKey
    private String ival;
    private boolean isStored;
}
