package com.xzy.mybatis.service.Impl;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by Administrator on 2017/6/8.
 */
@Service("activeMqDemoConsumerServiceImpl")
public class ActiveMqDemoConsumerServiceImpl {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jTemplate;


    /**
     * attention:
     * Details：接收消息，同时回复消息
     * 创建时间：2016-7-28 下午2:39:45
     *
     * @return
     */
    public String receiveMessage(Destination destination, Destination replyDestination) {
        Message message = jTemplate.receive(destination); //接收消息队列中的消息
        try {
            //此处为了更好的容错性，可以使用instanceof来判断下消息类型
            if (message instanceof TextMessage) {
                String receiveMessage = ((TextMessage) message).getText();
                System.out.println("收到生产者的消息:" + receiveMessage);
                jTemplate.send(replyDestination, new MessageCreator() {
                    @Override
                    public Message createMessage(Session session) throws JMSException {
                        return session.createTextMessage("消费者已经收到生产者的消息了，这是一条确认报文!");
                    }
                });
                return receiveMessage;
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
        return "";
    }
}
