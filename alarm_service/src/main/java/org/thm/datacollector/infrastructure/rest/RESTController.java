package org.thm.datacollector.infrastructure.rest;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.infrastructure.persistence.AlarmRepository;
import org.thm.datacollector.infrastructure.persistence.model.ResponseMessage;
import org.thm.datacollector.model.alarm.Alarm;

import java.util.List;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/28/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {
    final AlarmRepository repo = new AlarmRepository();


    @RequestMapping(method = RequestMethod.POST, path="/alarm/store/{ivalue}/{alarmId}/excessive/{threshold}")
    public ResponseMessage setStoring(@PathVariable("ivalue") final String ivalueStr,
                                      @PathVariable("alarmId") final UUID alarmId,
                                      @PathVariable("threshold") final double threshold
                                      ) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(ivalueStr != null ) {
            repo.createAlarm(ivalueStr, threshold, alarmId);
            resultMsg.setMessage("storing alarm for" + ivalueStr + " with threshold: " + threshold);
            resultMsg.setStatus("ok");
        }
        return resultMsg;
    }

    @RequestMapping(method = RequestMethod.GET, path="/alarm/load/all")
    public List<Alarm> setStoring() {
        return repo.loadAll();
    }
}
