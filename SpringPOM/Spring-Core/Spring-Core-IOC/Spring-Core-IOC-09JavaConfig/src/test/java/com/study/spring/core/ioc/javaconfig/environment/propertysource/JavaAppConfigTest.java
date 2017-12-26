package com.study.spring.core.ioc.javaconfig.environment.propertysource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaAppConfigTest {

    @Test
    public void testPropertySource() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaAppConfig.class);
        Foo foo = context.getBean(Foo.class);
        Assert.assertNotNull(foo);
        System.out.println("foo.getName()>>" + foo.getName());
        Assert.assertNotNull(foo.getName());
        context.close();
    }

    @Test
    public void testPropertySourceWithAtValue() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaAppConfig.class);
        Bar bar = context.getBean(Bar.class);
        Assert.assertNotNull(bar);
        System.out.println("bar.getAddress()>>" + bar.getAddress());
        Assert.assertNotNull(bar.getAddress());
        context.close();
    }
}
