package org.thm.datacollector.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
@Data
@AllArgsConstructor
public class ResponseMessage {
    private String status;
    private String message;
}
