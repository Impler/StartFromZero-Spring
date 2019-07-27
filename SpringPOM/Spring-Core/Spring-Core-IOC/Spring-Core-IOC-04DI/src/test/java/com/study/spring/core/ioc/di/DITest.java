package com.study.spring.core.ioc.di;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.ioc.di.domain.Foo;
import com.study.spring.core.ioc.di.domain.User;

public class DITest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("beans-di.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    // constructor-based - different constructor argument types
    @Test
    public void testConstructorByDifferentArgTypes() {
        Foo foo = (Foo) context.getBean("foo");
        System.out.println("testConstructorByDifferentArgTypes bar-->" + foo.getBar());
        System.out.println("testConstructorByDifferentArgTypes baz-->" + foo.getBaz());
        Assert.assertNotNull(foo.getBar());
        Assert.assertNotNull(foo.getBaz());
    }

    // constructor-based - different simple types of constructor argument types
    @Test
    public void testConstructorBySimpleType() {
        User user = (User) context.getBean("user");
        System.out.println("testConstructorBySimpleType-->" + user);
        Assert.assertNotNull(user);
    }

    // constructor-based - more complex case, use 'index' attribute
    @Test
    public void testConstructorByIndexAttribute() {
        User user = (User) context.getBean("userComplex");
        System.out.println("testConstructorByIndexAttribute-->" + user);
        Assert.assertNotNull(user);
    }

    // Setter-based
    @Test
    public void testSetter() {
        Foo foo = (Foo) context.getBean("fooSetter");
        Assert.assertNotNull(foo.getBar());
        Assert.assertNotNull(foo.getBaz());
        System.out.println("testSetter-->" + foo.getBar());
        System.out.println("testSetter-->" + foo.getBaz());
    }
}
