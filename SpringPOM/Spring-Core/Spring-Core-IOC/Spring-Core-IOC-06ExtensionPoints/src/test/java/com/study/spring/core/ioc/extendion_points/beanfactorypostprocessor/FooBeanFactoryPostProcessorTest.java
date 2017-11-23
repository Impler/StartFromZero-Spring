package com.study.spring.core.ioc.extendion_points.beanfactorypostprocessor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FooBeanFactoryPostProcessorTest {

	private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("classpath:beans-extension-points-beanfactorypostprocessor.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    @Test
    public void test() {
    	Foo foo = (Foo) context.getBean("foo");
    	System.out.println(foo);
    	Assert.assertEquals("Tom", foo.getName());
    }
}
