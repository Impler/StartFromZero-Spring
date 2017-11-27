package com.study.spring.core.ioc.anno_config._required;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RequiredTest {


    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("classpath:required/beans-annotation-required.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    @Test
    public void testRequired(){
        context.getBean("foo");
    }
    
    @Test(expected=BeanCreationException.class)
    public void testRequiredIfNot(){
        context.getBean("fooNotRequired");
    }
}
