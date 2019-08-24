package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.dynamicpointcut;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Foo;

public class DynamicMethodMatcherPointcutTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-dynamicpointcut.xml");
        
        Foo foo = (Foo) context.getBean("foo");
        // 第一次调用方法对其进行静态检查和动态检查
        foo.sayHi("tom");
        System.out.println("------------------------------");
        // 第二次调用仅做动态检查
        foo.sayHi("tom");
        System.out.println("------------------------------");
        // 符合动态匹配，执行增强逻辑
        foo.sayHi("jack");
        context.close();
    }

}
