package org.thm.datacollector.infrastructure.rest;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.domain.model.DataStoreInfo;
import org.thm.datacollector.infrastructure.model.ResponseMessage;
import org.thm.datacollector.infrastructure.persistence.DataStoreInfoRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {


    final DataStoreInfoRepository repo = new DataStoreInfoRepository();

    @RequestMapping(method = RequestMethod.POST, path="/datastore/store/{uuid}")
    public ResponseMessage setStoring(@PathVariable("uuid") final UUID uuid) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(uuid != null ) {
                repo.setStoring(uuid);
                resultMsg.setMessage("storing item " + uuid.toString());
                resultMsg.setStatus("ok");
        }
        return resultMsg;
    }

    @RequestMapping(method = RequestMethod.GET, path="/datastore/load/all")
    public List<DataStoreInfo> setStoring() {
            return repo.loadAll();
    }

    @RequestMapping(method = RequestMethod.GET, path="/datastore/remove/{uuid}")
    public void removeDataStoreInfoById(@PathVariable("uuid") final UUID uuid) {
            repo.removeById(uuid);
    }

    @RequestMapping(method=RequestMethod.GET, path="/datastore/send/request")
    public void sendDataProviderRequest() {

    }
}
