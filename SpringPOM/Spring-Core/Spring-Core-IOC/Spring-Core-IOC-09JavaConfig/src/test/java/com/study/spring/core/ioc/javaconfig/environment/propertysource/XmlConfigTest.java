package com.study.spring.core.ioc.javaconfig.environment.propertysource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlConfigTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-env-propertysource.xml");
        Bar bar = context.getBean(Bar.class);
        Assert.assertNotNull(bar);
        System.out.println("bar.getAddress()>>" + bar.getAddress());
        Assert.assertNotNull(bar.getAddress());
        context.close();
    }

}
