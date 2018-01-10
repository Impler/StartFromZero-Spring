package com.study.spring.core.aop.aspectanno._03advice;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceA;

public class AfterReturningAdviceTest {

    @Test
    public void testAfterReturning() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/beans-aop-aspectanno-advice-afterreturning.xml");
        
        IServiceA fooService = (IServiceA) context.getBean("fooService");
        // 被织入后置增强的方法
        fooService.save(null);
        fooService.update(null);
        context.close();
    }

}
