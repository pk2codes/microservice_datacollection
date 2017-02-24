package org.thm.datacollector.infrastructure.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.domain.model.DataStoreInfo;
import org.thm.datacollector.infrastructure.model.ResponseMessage;
import org.thm.datacollector.infrastructure.persistence.DataStoreInfoRepository;
import org.thm.datacollector.infrastructure.stream.SenderService;
import org.thm.datacollector.schedule.MessageSendJob;

import java.net.UnknownHostException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableEurekaClient
public class RESTController {



    @RequestMapping(method = RequestMethod.POST, path="/store/{uuid}")
    public ResponseMessage setStoring(@PathVariable("uuid") final UUID uuid) {
        ResponseMessage resultMsg = new ResponseMessage("error", "Something went wrong!");
        if(uuid != null ) {
            try {
                final DataStoreInfoRepository repo = new DataStoreInfoRepository();
                repo.setStoring(uuid);
                resultMsg.setMessage("storing item " + uuid.toString());
                resultMsg.setStatus("ok");

            } catch(UnknownHostException uhe) {
                uhe.printStackTrace();
            }
        }
        return resultMsg;
    }

    @RequestMapping(method = RequestMethod.GET, path="/load/all")
    public List<DataStoreInfo> setStoring() {
        try {
            final DataStoreInfoRepository repo = new DataStoreInfoRepository();
            return repo.loadAll();
        } catch(UnknownHostException uhe) {
            uhe.printStackTrace();
        }
        return null;
    }
    @RequestMapping(method=RequestMethod.GET, path="/send/request")
    public void sendDataProviderRequest() {

    }
}
