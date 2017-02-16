package org.thm.datacollector.infrarstructure.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.domain.model.DataLocation;
import org.thm.datacollector.infrarstructure.model.ResponseMessage;
import org.thm.datacollector.infrarstructure.persistence.DataLocationRepository;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by patrick.welter on 2/16/17.
 * (c) Janitza Electronics
 */
@RestController
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
