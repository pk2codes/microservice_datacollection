package thm.datacollector.infrastructure.rest.model;

/**
 * Created by patrick.welter on 2/20/17.
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
