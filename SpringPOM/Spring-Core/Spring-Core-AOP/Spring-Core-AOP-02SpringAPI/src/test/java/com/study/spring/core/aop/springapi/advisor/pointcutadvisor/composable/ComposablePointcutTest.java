package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.composable;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Foo;
import com.study.spring.core.aop.springapi.advisor.pointcutadvisor.controlflow.FooDelegate;

public class ComposablePointcutTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-composable.xml");

        Foo foo = (Foo) context.getBean("foo");
        FooDelegate delegate = new FooDelegate();
        delegate.setFoo(foo);
        delegate.doService("jack");

        context.close();
    }

}
