package com.study.spring.core.aop.aspectanno._03advice;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceA;

public class AfterThrowingAdviceTest {

    @Test(expected=Exception.class)
    public void testAfterThrow() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-aspectanno-advice-afterthrow.xml");
        IServiceA fooService1 = (IServiceA) context.getBean("fooService1");
        IServiceA fooService2 = (IServiceA) context.getBean("fooService2");
        // no exception throw
        fooService1.save(null);
        
        // 抛出异常，并触发异常抛出增强
        fooService2.save(null);
        context.close();
    }

}
