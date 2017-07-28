package com.xzy.mybatis.controller;

import com.xzy.mybatis.service.Impl.ActiveMqDemoProducerServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/6/8.
 */
@Controller
@RequestMapping("/activeMqDemo")
public class ActiveMqDemoController {

    @Resource
    private ActiveMqDemoProducerServiceImpl activeMqDemoProducerService;

    @RequestMapping("/demo1")
    public String demo1(HttpServletRequest request, HttpServletResponse response){
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring-mybatis.xml", "spring-redis.xml","spring-activemq.xml"});
        Destination queueDestination = (Destination)context.getBean("queueDestination");
        activeMqDemoProducerService.sendMessage(queueDestination,"生产者发送了一条消息");
        return "index";
    }

    public ActiveMqDemoProducerServiceImpl getActiveMqDemoProducerService() {
        return activeMqDemoProducerService;
    }

    public void setActiveMqDemoProducerService(ActiveMqDemoProducerServiceImpl activeMqDemoProducerService) {
        this.activeMqDemoProducerService = activeMqDemoProducerService;
    }
}
