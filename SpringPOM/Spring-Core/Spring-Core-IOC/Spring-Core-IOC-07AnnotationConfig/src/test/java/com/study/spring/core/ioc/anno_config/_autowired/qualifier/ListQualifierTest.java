package com.study.spring.core.ioc.anno_config._autowired.qualifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ListQualifierTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("autowired/beans-annotation-autowired-qualifier-list.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }
    
    @Test
    public void test(){
        Bar bar = (Bar) context.getBean("bar");
        bar.doServices();
    }

}
