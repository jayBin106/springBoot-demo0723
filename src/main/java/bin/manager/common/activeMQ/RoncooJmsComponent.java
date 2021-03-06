//package bin.manager.common.activeMQ;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Component;
//
//import javax.jms.Queue;
//
///**
// * @author wujing
// */
//@Component
//public class RoncooJmsComponent {
//
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Autowired
//    private Queue queue;
//
//    public void send(String msg) {
//        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
//    }
//
//    @JmsListener(destination = "roncoo.queue")
//    public void receiveQueue(String text) {
//        System.out.println("接受到：" + text);
//    }
//
//}
