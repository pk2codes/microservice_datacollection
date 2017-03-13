package org.thm.datacollector.contracts;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import org.thm.datacollector.DataLocationServiceApplication;


/**
 * Created by patrick.welter on 3/13/17.
 * (c) Janitza Electronics
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ValidLocationContractBase.LocationTest.class)
@ActiveProfiles("test")
public class ValidLocationContractBase {
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(context);

    }
    @SpringBootApplication
    public static class LocationTest {

    }
}
