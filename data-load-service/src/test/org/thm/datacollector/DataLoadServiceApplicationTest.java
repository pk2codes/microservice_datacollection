package org.thm.datacollector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.thm.datacollector.infrastructure.rest.RestClient;
import org.thm.datacollector.infrastructure.rest.model.DataLocation;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by patrick.welter on 3/9/17.
 * (c) Janitza Electronics
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        properties = {
                "eureka.client.enabled=false",
                "spring.cloud.config.enabled=false"})

public class DataLoadServiceApplicationTest {

/*
    @Test
    public void shouldSendDataWhenRequested() throws Exception {

    }
*/

    @Autowired
    private RestClient.LocationResource locationResource;

    @Test
    public void shouldGiveMeAValidLocation() throws Exception {
        // given location uuid
        final UUID uuid = UUID.fromString("d957028b-8dd4-48c7-b786-f74248433404");

        // when
        final DataLocation location = locationResource.dataLocation(uuid);

        // then
        assertThat(location.getId()).isEqualTo(uuid);
        assertThat(location.getUrl()).isNotNull();
    }

/*
    @Test
    public void shouldGiveMeANotFound() throws Exception {

    }
*/
}
