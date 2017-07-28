package com.xzy.mybatis.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.xzy.mybatis.model.Mail;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Administrator on 2017/6/8.
 * activeMq 生产者
 */
@Service("activeMqDemoProducerServiceImpl")
public class ActiveMqDemoProducerServiceImpl {

    private static Logger logger = Logger.getLogger(ActiveMqDemoProducerServiceImpl.class);

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    public void sendMessage(Destination receivedestination, final String message) {
        logger.debug("================生产者创建了一条消息==============");
        jmsTemplate.send(receivedestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("hello acticeMQ:" + message);
            }
        });
    }


    /**
     * 创建一条要发送的邮件到队列中
     * @param receivedestination
     * @param mail
     */
    public void sendMailMessage(Destination receivedestination , final Mail mail){
        logger.debug("================生产者创建了一条需要发送的邮件==============");
        jmsTemplate.send(receivedestination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(JSONObject.toJSONString(mail));
            }
        });
    }
        

}
