package com.study.spring.core.ioc.bean_nature.destroy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DisposableBeanTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("beans-nature-lifecycle-destroy.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    @Test
    public void test() {
        context.destroy();
    }

}
