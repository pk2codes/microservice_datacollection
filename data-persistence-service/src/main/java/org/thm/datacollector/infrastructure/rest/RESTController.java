package org.thm.datacollector.infrastructure.rest;

import org.apache.log4j.Logger;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.thm.datacollector.infrastructure.persistence.model.OnlineRecordingDBEntity;
import org.thm.datacollector.model.OnlineRecording;
import org.thm.datacollector.infrastructure.persistence.OnlineRecordingRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {
    final OnlineRecordingRepository repo = new OnlineRecordingRepository();
    final Logger log = Logger.getLogger(RestController.class);

    @RequestMapping(path = "/store/data", method = RequestMethod.POST)
    public String store(@RequestBody OnlineRecording rec){
        log.info("saving:" + rec.toString());
        repo.insertDataStoreInfo(rec);
        return "OK";
    }
    @RequestMapping(path = "/load/all", method = RequestMethod.GET)
    public List<OnlineRecordingDBEntity> loadAll() {
        return repo.loadAll();
    }

    @RequestMapping(path = "/load/{n}", method = RequestMethod.GET)
    public List<OnlineRecordingDBEntity> loadN(@PathVariable("n") final int n) {
        return repo.loadLastN(n);
    }
}
