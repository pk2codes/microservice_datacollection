package org.thm.datacollector.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.domain.model.DataStoreIValInfo;
import org.thm.datacollector.domain.model.DataStoreResourceInfo;
import org.thm.datacollector.infrastructure.model.ResponseMessage;
import org.thm.datacollector.infrastructure.persistence.DataStoreIValRepository;
import org.thm.datacollector.infrastructure.persistence.DataStoreResourceRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {

    @Autowired
    private DataStoreResourceRepository resourceRepo;

    @Autowired
    private DataStoreIValRepository ivalRepo;

    @RequestMapping(method = RequestMethod.POST, path="/datastore/resource/store/{uuid}")
    public ResponseMessage storeResource(@PathVariable("uuid") final UUID uuid) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(uuid != null ) {
                resourceRepo.setStoring(uuid);
                resultMsg.setMessage("storing item " + uuid.toString());
                resultMsg.setStatus("ok");
        }
        return resultMsg;
    }

    @RequestMapping(method = RequestMethod.GET, path="/datastore/resource/load/all")
    public List<DataStoreResourceInfo> loadAllResources() {
            return resourceRepo.loadAll();
    }

    @RequestMapping(method = RequestMethod.GET, path="/datastore/resource/remove/{uuid}")
    public void removeResource(@PathVariable("uuid") final UUID uuid) {
            resourceRepo.removeById(uuid);
    }

    @RequestMapping(method = RequestMethod.POST, path="/datastore/ival/store/{uuid}")
    public ResponseMessage storeIval(@PathVariable("ival") final String ival) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(ival != null ) {
            ivalRepo.setStoring(ival);
            resultMsg.setMessage("storing item " + ival.toString());
            resultMsg.setStatus("ok");
        }
        return resultMsg;
    }

    @RequestMapping(method = RequestMethod.GET, path="/datastore/ival/load/all")
    public List<DataStoreIValInfo> loadAllIVal() {
        return ivalRepo.loadAll();
    }

    @RequestMapping(method = RequestMethod.GET, path="/datastore/ival/remove/{uuid}")
    public void removeIvalByIVal(@PathVariable("ival") final String ival) {
        ivalRepo.removeBy(ival);
    }

}
