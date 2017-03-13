package org.thm.datacollector;

import lombok.Data;

import java.util.Date;

/**
 * Created by patrick.welter on 2/20/17.
 * (c) Janitza Electronics
 */
@Data
class ExampleData {
    public double value;
    public Date time = new Date();
    public String type;
}
