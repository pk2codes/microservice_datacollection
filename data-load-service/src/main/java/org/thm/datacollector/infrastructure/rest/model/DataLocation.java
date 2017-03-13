package org.thm.datacollector.infrastructure.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/20/17.
 * (c) Janitza Electronics
 */
@Data
@AllArgsConstructor
public class DataLocation {
    private UUID id;
    private String url;
}
