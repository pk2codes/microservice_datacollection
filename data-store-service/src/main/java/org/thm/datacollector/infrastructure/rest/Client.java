package org.thm.datacollector.infrastructure.rest;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.thm.datacollector.infrastructure.model.ResponseMessage;
import org.thm.datacollector.model.OnlineRecording;

import java.util.UUID;

/**
 * Created by patrick.welter on 3/6/17.
 * (c) Janitza Electronics
 */
@EnableFeignClients
@Component
@EnableEurekaClient
public class Client {
    @FeignClient("data-persistence-service")
    public interface SaveRecording {
        @RequestMapping(method = RequestMethod.POST,  path = "/store/data")
        String dataLocation(@RequestBody final OnlineRecording recording);
    }
}
