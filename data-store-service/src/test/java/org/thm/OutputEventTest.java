package org.thm;

/**
 * Created by patrick.welter on 2/23/17.
 * (c) Janitza Electronics

@RunWith(SpringRunner.class)
@SpringBootTest
public class OutputEventTest {
    @Autowired
    SenderService senderService;
    @Test
    public void sendMessage() {
        senderService.requestDataProvider(UUIDs.random());
    }
}
 */