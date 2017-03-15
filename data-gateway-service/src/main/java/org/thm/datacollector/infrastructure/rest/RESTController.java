package org.thm.datacollector.infrastructure.rest;

import org.apache.kafka.clients.producer.internals.Sender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thm.datacollector.infrastructure.stream.IValueSender;
import org.thm.datacollector.model.OnlineRecording;

import java.util.Date;

/**
 * Created by patrick.welter on 3/15/17.
 * (c) Janitza Electronics
 */
@RestController
public class RESTController {

    private final Logger log = Logger.getLogger(RestController.class);

    @Autowired
    private IValueSender sender;

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public void saveIVal(@RequestParam("ival") final String ival,
                         @RequestParam("startTime") final long startTime,
                         @RequestParam("endTime") final long endTime,
                         @RequestParam("value") final double value) {

        // todo: if(Validation.checkParams(ival, startTime,endTime, value)) {...}
        final OnlineRecording onlineRecording = new OnlineRecording();
        onlineRecording.setStartTime(new Date(startTime));
        onlineRecording.setEndTime(new Date(endTime));
        onlineRecording.setValue(value);
        onlineRecording.setType(ival);
        log.info("incoming: " + onlineRecording.toString());
        sender.send(onlineRecording);
    }
}
