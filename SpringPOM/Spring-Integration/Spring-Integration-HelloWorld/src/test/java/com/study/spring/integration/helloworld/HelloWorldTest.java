package com.study.spring.integration.helloworld;

import com.study.spring.integration.helloworld.service.HelloService;
import javafx.application.Application;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class HelloWorldTest {


  @Test
  public void testSendMsgByChannel() {
    ApplicationContext app = new ClassPathXmlApplicationContext("appCtx-channel.xml");
    MessageChannel channel = app.getBean("names", MessageChannel.class);
    Message<String> msg = MessageBuilder.withPayload("Tom").build();
    /**
     * 向channel中发送消息，service activator负责接收消息，并调用特定的服务并返回
     */
    boolean result = channel.send(msg);
    System.out.println("result: " + result);
  }

  @Test
  public void testSendMsgByGateWay(){
    ApplicationContext app = new ClassPathXmlApplicationContext("appCtx-gateway.xml");
    HelloService helloService = app.getBean("helloGateway", HelloService.class);

    String result = helloService.getHello("Kitty");
    System.out.println("result: " + result);
  }

}