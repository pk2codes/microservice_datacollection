package org.thm.datacollector.infrastructure.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import org.thm.datacollector.domain.model.DataLocation;
import org.thm.datacollector.infrastructure.model.ResponseMessage;
import org.thm.datacollector.infrastructure.persistence.DataLocationRepository;
import org.thm.datacollector.infrastructure.stream.SenderService;

import java.util.List;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {

    final DataLocationRepository repo =new DataLocationRepository();
    final Logger log = Logger.getLogger(DataLocationRepository.class);

    @Autowired
    SenderService senderService;

    @RequestMapping(method = RequestMethod.POST, path = "/datalocation/save")
    public ResponseMessage saveDataLocation(@RequestParam(value="url", defaultValue="") final String url ) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(url == null || url.isEmpty()) {
            log.warn("invalid parameter url: " + url);
            resultMsg.setMessage("No valid URL!");
        } else {

                repo.insertDataLocation(url);
                resultMsg.setStatus("ok");
                resultMsg.setMessage("Url " + url + " saved!");

        }
        return resultMsg;
    }

    @RequestMapping("/datalocation/load/{uuid}")
    public DataLocation loadDataLocationById(@PathVariable("uuid")UUID uuid) {


        final DataLocation dataLocationById = repo.getDataLocationById(uuid);
        log.info("load: " + dataLocationById.toString());
        return dataLocationById;
    }

    @RequestMapping("/datalocation/remove/{uuid}")
    public void removeDataLocationById(@PathVariable("uuid")UUID uuid) {
        repo.removeById(uuid);
        senderService.setLocationDeletedStatus(uuid);
    }

    @RequestMapping("/datalocation/load/all")
    public List<DataLocation> loadAllDataLocations() {
            return repo.loadAll();
    }
}
