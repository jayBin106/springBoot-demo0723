package bin.manager;

import bin.manager.common.rabbitMQ.MQSenderTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMQTest {
    @Autowired
    private MQSenderTest mqSenderTest;

    @Test
    public void send() {
        mqSenderTest.send("hello : RabbitMQ");
    }
}
