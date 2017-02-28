package org.thm.datacollector.model;

/**
 * Created by patrick.welter on 2/28/17.
 * (c) Janitza Electronics
 */
import lombok.Data;

import java.util.Date;

@Data
public class ExampleData {
    public String type;
    public double value;
    public Date Time;
}
