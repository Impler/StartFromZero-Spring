package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.regexmethod;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Bar;
import com.study.spring.core.aop.springapi.advisor.Foo;

public class RegexMethodMatcherAdvisorTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-regex.xml");
       
        String name = "jack";

        Foo foo = (Foo) context.getBean("foo");
        // 满足正则匹配的方法
        foo.sayHello(name);
        foo.sayHi(name);
        // 不满足正则匹配的方法
        foo.sayGoodBye(name);

        Bar bar = (Bar) context.getBean("bar");
        // 满足正则匹配的方法
        bar.sayHello(name);
        bar.sayHi(name);
        // 不满足正则匹配的方法
        bar.sayGoodBye(name);
        
        context.close();
    }

}
