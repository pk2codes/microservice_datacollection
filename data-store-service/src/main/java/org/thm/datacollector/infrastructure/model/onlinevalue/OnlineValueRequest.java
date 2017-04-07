package org.thm.datacollector.infrastructure.model.onlinevalue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by patrick.welter on 4/4/17.
 * (c) Janitza Electronics
 */
@Data
@AllArgsConstructor
public class OnlineValueRequest {
    private List<String> ivalueIds;
}
