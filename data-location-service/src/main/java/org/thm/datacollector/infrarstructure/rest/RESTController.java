package org.thm.datacollector.infrarstructure.rest;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;
import org.thm.datacollector.domain.model.DataLocation;
import org.thm.datacollector.infrarstructure.model.ResponseMessage;
import org.thm.datacollector.infrarstructure.persistence.DataLocationRepository;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {



    @RequestMapping(method = RequestMethod.POST, path = "/datalocation/save")
    public ResponseMessage saveDataLocation(@RequestParam(value="url", defaultValue="") final String url ) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(url == null || url.isEmpty()) {
            resultMsg.setMessage("No valid URL!");
        } else {
            try {
                final DataLocationRepository repo = new DataLocationRepository();
                repo.insertDataLocation(url);
                resultMsg.setStatus("ok");
                resultMsg.setMessage("Url " + url + " saved!");

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
        return resultMsg;
    }

    @RequestMapping("/datalocation/load/{uuid}")
    public DataLocation loadDataLocationById(@PathVariable("uuid")UUID uuid) {
        final DataLocationRepository repo;
        try {
            repo = new DataLocationRepository();
            return repo.getDataLocationById(uuid);

        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/datalocation/load/all")
    public List<DataLocation> loadAllDataLocations() {
        try {
            final DataLocationRepository repo = new DataLocationRepository();
            return repo.getAllDataLocations();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
