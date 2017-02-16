package org.thm.datacollector.domain.model;

import com.datastax.driver.core.utils.UUIDs;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
@Table
@Data
@AllArgsConstructor
public class DataLocation {
    @PrimaryKey
    private UUID id;
    private String url;
}