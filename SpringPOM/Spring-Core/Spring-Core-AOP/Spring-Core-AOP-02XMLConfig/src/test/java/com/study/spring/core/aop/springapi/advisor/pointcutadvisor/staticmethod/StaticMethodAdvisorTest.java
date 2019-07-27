package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.staticmethod;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Bar;
import com.study.spring.core.aop.springapi.advisor.Foo;

public class StaticMethodAdvisorTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-staticmethod.xml");
        
        Foo foo = (Foo) context.getBean("foo");
        String name = "jack";
        // 目标类中的静态匹配方法，织入增强
        foo.sayHi(name);
        // 目标类中的非匹配方法，未织入增强
        foo.sayHello(name);
        
        // 非目标类，未织入增强
        Bar bar = (Bar) context.getBean("bar");
        bar.sayHi(name);
        bar.sayHello(name);
        context.close();
    }

}
