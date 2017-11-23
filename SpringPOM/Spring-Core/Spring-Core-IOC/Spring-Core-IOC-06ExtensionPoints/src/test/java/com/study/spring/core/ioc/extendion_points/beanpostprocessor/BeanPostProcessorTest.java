package com.study.spring.core.ioc.extendion_points.beanpostprocessor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanPostProcessorTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("classpath:beans-extension-points-beanpostprocessor.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    @Test
    public void test() {
        this.context.getBean("initLifecycle");
    }
}
