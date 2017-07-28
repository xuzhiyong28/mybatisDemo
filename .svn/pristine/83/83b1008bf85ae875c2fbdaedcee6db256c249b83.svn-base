import com.xzy.mybatis.model.Mail;
import com.xzy.mybatis.service.Impl.ActiveMqDemoConsumerServiceImpl;
import com.xzy.mybatis.service.Impl.ActiveMqDemoProducerServiceImpl;
import com.xzy.mybatis.service.Impl.MailSerivceImpl;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * Created by Administrator on 2017/6/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml","classpath:spring-redis.xml" ,"classpath:spring-activemq.xml" })
public class ActiveMqTest {
    private static Logger logger = Logger.getLogger(ActiveMqTest.class);
    @Resource
    private ActiveMqDemoProducerServiceImpl activeMqDemoProducerService;

    @Resource
    private ActiveMqDemoConsumerServiceImpl activeMqDemoConsumerService;

    @Resource
    private MailSerivceImpl mailSerivceImpl;

    @Resource(name="queueDestination")
    private Destination receiveQueue;


    @Resource(name="mailDestination")
    private Destination mailDestination;


    private JmsTemplate jmsTemplate;

    @Test
    public void producerTest(){
        for(int i = 0 ; i < 10 ; i++)
            activeMqDemoProducerService.sendMessage(receiveQueue,"my name is chhliu!");
    }

    @Resource(name="responseQueue")
    private Destination replyQueue; //回复队列，这个可以让发送消息的队列获取到
    @Test
    public void consumerTest(){
        activeMqDemoConsumerService.receiveMessage(receiveQueue, replyQueue);
    }

    @Test
    public void jmsTemplateTest(){
    }

    @Test
    public void mailJmsTest(){
        Mail mail = new Mail ();
        mail.setTo("m18054809167@163.com");//邮件接收方
        mail.setSubject("hello");//邮件标题
        mail.setContent("hello,this is first email");//邮件内容
        mailSerivceImpl.sendMail(mail);
    }


    /**
     * 当把邮件放到mq队列时，监听器会自动检测然后去发送
     */
    @Test
    public void mailActiveMqTest(){
        Mail mail = new Mail ();
        mail.setTo("m18054809167@163.com");//邮件接收方
        mail.setSubject("hello");//邮件标题s
        mail.setContent("hello,this is first email");//邮件内容
        activeMqDemoProducerService.sendMailMessage(mailDestination,mail);
    }



}
