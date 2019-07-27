package com.study.spring.core.ioc.anno_config._autowired.simple;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowiredTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("autowired/beans-annotation-autowired-simple.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }
    
    
    @Test
    public void testSimpleAutowired(){
        Foo foo = (Foo) context.getBean("foo");
        foo.execute();
    }

    @Test(expected=UnsatisfiedDependencyException.class)
    public void testSimpleAutowiredNoCandidate(){
        context.getBean("bar");
    }
}
