package org.thm.datacollector;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.thm.datacollector.infrastructure.rest.RestClient;
import org.thm.datacollector.infrastructure.rest.model.DataLocation;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by patrick.welter on 3/9/17.
 * (c) Janitza Electronics
 */
@RunWith(SpringRunner.class)
@TestPropertySource(properties={"eureka.client.enabled=false",
        "spring.cloud.config.enabled=false"})
@ActiveProfiles("test")
@AutoConfigureStubRunner(
        ids = "org.thm.datacollector:data-location-service:+:stubs:8082",
        workOffline = true)
@DirtiesContext
@SpringBootTest(classes = DataLoadServiceApplication.class)
public class DataLoadServiceApplicationTest {

    @Autowired
    private RestClient.LocationResource locationResource;


    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @Test
    public void shouldGiveMeAValidLocation() throws Exception {

        final String uuidStr = "d957028b-8dd4-48c7-b786-f74248433404";
        // given location uuid
        final UUID uuid = UUID.fromString(uuidStr);
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
