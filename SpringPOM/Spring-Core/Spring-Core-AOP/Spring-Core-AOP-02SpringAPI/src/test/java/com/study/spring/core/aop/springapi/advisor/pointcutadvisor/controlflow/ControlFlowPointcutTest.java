package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.controlflow;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Foo;

public class ControlFlowPointcutTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-controlflow.xml");
        
        Foo foo = (Foo) context.getBean("foo");
        FooDelegate delegate = new FooDelegate();
        delegate.setFoo(foo);
        delegate.doService("jack");

        context.close();
    }

}
