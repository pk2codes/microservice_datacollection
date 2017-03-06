package org.thm.datacollector.infrastructure.rest;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thm.datacollector.infrastructure.model.ResponseMessage;
import org.thm.datacollector.model.OnlineRecording;

import java.util.UUID;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@EnableFeignClients
@Component
public class Client {
    @FeignClient("data-persistence-service")
    public interface SaveRecording {
        @RequestMapping(method = RequestMethod.GET,  value = "/store/{recording}")
        ResponseMessage dataLocation(@PathVariable("recording") final OnlineRecording recording);
    }
}
