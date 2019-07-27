package com.study.spring.core.ioc.anno_config._jsr250.resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ResourceTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("resource/beans-annotation-jsr250-resource.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    @Test
    public void test(){
        Foo foo = (Foo) context.getBean("foo");
        foo.doService();
    }
}
