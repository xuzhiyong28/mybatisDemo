package com.xzy.mybatis.service.Impl;

import com.xzy.mybatis.model.Mail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/6/11.
 */
@Component("mailSerivceImpl")
public class MailSerivceImpl {
    private static Logger logger = Logger.getLogger(MailSerivceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;


    /**
     * 发送邮件
     * @param mail
     */
    public void sendMail(final Mail mail) {

       try {
            simpleMailMessage.setFrom(simpleMailMessage.getFrom());
            simpleMailMessage.setTo(mail.getTo());
            simpleMailMessage.setSubject(mail.getSubject());
            simpleMailMessage.setText(mail.getContent());
            mailSender.send(simpleMailMessage);
        } catch (MailException e) {
            logger.debug(e);
            e.printStackTrace();
        }
    }

}
