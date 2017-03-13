package org.thm.datacollector.infrastructure.rest;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.thm.datacollector.infrastructure.rest.model.DataLocation;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/20/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableFeignClients
@EnableEurekaClient
public class RestClient {

    @FeignClient("data-location-service")
    public interface LocationResource {
        @RequestMapping(method = RequestMethod.GET,  value = "/datalocation/load/{uuid}")
        DataLocation dataLocation(@PathVariable("uuid") final UUID uuid);
    }

}
