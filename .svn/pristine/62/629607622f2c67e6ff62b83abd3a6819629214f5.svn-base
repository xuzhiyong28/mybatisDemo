package com.xzy.mybatis.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.xzy.mybatis.model.Mail;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.Logger;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by Administrator on 2017/6/11.
 */
public class MailCustomerSessionAwareMessageListener implements SessionAwareMessageListener<Message> {
    private static Logger logger = Logger.getLogger(MailCustomerSessionAwareMessageListener.class);


    @Resource
    private MailSerivceImpl mailSerivceImpl;

    @Override
    public void onMessage(Message message, Session session) throws JMSException {
        ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
        final String ms = msg.getText();
        Mail mail = JSONObject.parseObject(ms, Mail.class);
        logger.debug("======接收到一条发送邮件的队列:" + ms + "=======");
        try {
            mailSerivceImpl.sendMail(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public MailSerivceImpl getMailSerivceImpl() {
        return mailSerivceImpl;
    }

    public void setMailSerivceImpl(MailSerivceImpl mailSerivceImpl) {
        this.mailSerivceImpl = mailSerivceImpl;
    }
}
