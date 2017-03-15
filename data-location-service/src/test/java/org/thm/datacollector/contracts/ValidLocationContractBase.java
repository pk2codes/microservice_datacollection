package org.thm.datacollector.contracts;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.thm.datacollector.DataLocationServiceApplication;
import org.thm.datacollector.infrastructure.persistence.DataLocationRepository;
import org.thm.datacollector.infrastructure.persistence.PersistenceTestSuite;

import java.util.UUID;


/**
 * Created by patrick.welter on 3/13/17.
 * (c) Janitza Electronics
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataLocationServiceApplication.class)
@TestPropertySource(properties={"eureka.client.enabled=false",
        "spring.cloud.config.enabled=false"})
@ActiveProfiles("test")
public class ValidLocationContractBase {

    final private String cassandraKeySpaceName = "location_service_valid_location_test";
    final private String cassandraTableName = "datalocation";
    private PersistenceTestSuite persistenceTestSuite;

    @Autowired
    private WebApplicationContext context;

    private void setupCassandra() {
        this.persistenceTestSuite =
                new PersistenceTestSuite(cassandraKeySpaceName,cassandraTableName);
        persistenceTestSuite.setupTestDB("id uuid, url varchar, PRIMARY KEY(id)");

    }

    private void insertTestData() {
        final DataLocationRepository repo = new DataLocationRepository(cassandraKeySpaceName, cassandraTableName);
        repo.insertDataLocationWithId(UUID.fromString("d957028b-8dd4-48c7-b786-f74248433404"),
                "http://testurl.test");
        repo.close();
    }

    private void cleanupCassandra() {
        persistenceTestSuite.cleanTestDB();

    }

    @After
    public void cleanUp() throws Exception {
        cleanupCassandra();
    }


    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(context);
        setupCassandra();
        insertTestData();
    }

}
