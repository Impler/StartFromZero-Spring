package com.study.spring.core.ioc.classpath_scan.xml;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.ioc.classpath_scan.component.UserController;

public class XmlConfigTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-classpath_scan.xml");
        UserController userController = context.getBean(UserController.class);
        userController.save();
        context.close();
    }

}
