package bin.manager.common.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wujing
 */
@Component
public class RoncooRabbitComponent {
    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receiveQueue(String text) {
        System.out.println("111接受到：" + text);
    }

    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message) {
        System.out.println("222接受到：" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        System.out.println("333接受到：" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        System.out.println("接受到：" + message);
    }

    @RabbitListener(queues = MQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        System.out.println("接受到：" + new String(message));
    }

}
