package thm.datacollector.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.thm.datacollector.model.ExampleData;
import org.thm.datacollector.model.OnlineRecording;
import thm.datacollector.infrastructure.rest.RestClient;
import thm.datacollector.infrastructure.stream.DataSender;

import java.util.UUID;

/**
 * Created by patrick.welter on 2/24/17.
 * (c) Janitza Electronics
 *
 */
@Component
public class DataLoader {
    @Autowired
    private RestClient.LocationResource locationResource;

    @Autowired
    private DataSender dataSender;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public void loadDataById(final UUID uuid) {
        try {
            final String url = locationResource.dataLocation(uuid).getUrl();
            final OnlineRecording data = restTemplate().getForObject(url, OnlineRecording.class);
            dataSender.send(data);
        } catch (Exception e )  {
            e.printStackTrace();
        }
    }
}
