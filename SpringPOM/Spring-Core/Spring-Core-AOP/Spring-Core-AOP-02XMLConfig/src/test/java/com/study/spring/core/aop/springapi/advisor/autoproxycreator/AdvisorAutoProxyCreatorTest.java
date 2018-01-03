package com.study.spring.core.aop.springapi.advisor.autoproxycreator;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Foo;

public class AdvisorAutoProxyCreatorTest {

    @Test
    public void testBeanNameAutoProxyCreator() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-autoproxycreator-beanname.xml");
        
        context.close();
    }

    
    @Test
    public void testDefaultAdvisorAutoProxyCreator() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-autoproxycreator-default.xml");
        
        Foo foo = (Foo) context.getBean("foo");
        String name = "jack";
        // 织入增强的连接点
        foo.sayHi(name);
        // 未匹配的连接点
        foo.sayGoodBye(name);
        context.close();
    }
}
