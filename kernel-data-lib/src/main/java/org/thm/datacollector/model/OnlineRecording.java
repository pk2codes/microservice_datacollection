package org.thm.datacollector.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by patrick.welter on 2/28/17.
 * (c) Janitza Electronics
 */
@Data
public class OnlineRecording {
    private String type;
    private Date startTime;
    private Date endTime;
    private double value;
}
