package com.xzy.mybatis.service.Impl;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Administrator on 2017/6/8.
 * ActiveMq消费者监听类
 */
@Component
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {
    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        final String ms = msg.getText();
        System.out.println("消费者接收到信息:" + ms);
    }
}
