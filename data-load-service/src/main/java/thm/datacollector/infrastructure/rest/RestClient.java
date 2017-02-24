package thm.datacollector.infrastructure.rest;

import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import thm.datacollector.infrastructure.rest.model.DataLocation;
import thm.datacollector.infrastructure.rest.model.ExampleData;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/20/17.
 * (c) Janitza Electronics
 */
@RestController
@EnableFeignClients
@EnableEurekaClient
public class RestClient {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    LocationResource locationResource;

    @FeignClient("data-location-service")
    interface LocationResource {
        @RequestMapping(method = RequestMethod.GET, value = "/datalocation/load/{uuid}")
        DataLocation dataLocation(@PathVariable("uuid") final UUID uuid);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/show/{uuid}")
    public ExampleData loadByUuid(@PathVariable("uuid") final UUID uuid) {
        try {
            final String url = locationResource.dataLocation(uuid).getUrl();
            return restTemplate().getForObject(url, ExampleData.class);
        } catch (Exception e )  {
            e.printStackTrace();
            return null;
        }

    }
}
