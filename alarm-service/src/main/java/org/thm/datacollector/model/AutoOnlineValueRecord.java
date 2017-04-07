package org.thm.datacollector.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by patrick.welter on 4/6/17.
 * (c) Janitza Electronics
 */
@AllArgsConstructor
@Data
public class AutoOnlineValueRecord {
    private String m_IvalId;
    private float m_Value;
}
