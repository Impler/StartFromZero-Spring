package com.study.spring.core.ioc.anno_config._autowired.map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("autowired/beans-annotation-autowired-map.xml");
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
        foo.doServices();
    }

}
