package org.thm.datacollector.model;

import org.thm.datacollector.infrastructure.persistence.AlarmRepository;

import java.util.List;

/**
 * Created by patrick.welter on 2/28/17.
 * (c) Janitza Electronics
 */
public class AlarmService {
    final private static AlarmRepository repo = new AlarmRepository();

    public static void watch(final OnlineRecording rec) {
       // XXX todo
    }
}
