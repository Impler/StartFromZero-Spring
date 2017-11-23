package com.study.spring.core.ioc.bean_nature.aware;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextAwareTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("beans-nature-aware.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    
    @Test
    public void test(){
        FooApplicationContextAware aware = (FooApplicationContextAware) this.context.getBean("aware");
        Assert.assertEquals(aware.getContext(), this.context);
    }

}
