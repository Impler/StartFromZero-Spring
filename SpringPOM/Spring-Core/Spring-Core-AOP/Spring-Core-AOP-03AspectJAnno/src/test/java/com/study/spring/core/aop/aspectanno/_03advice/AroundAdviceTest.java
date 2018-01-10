package com.study.spring.core.aop.aspectanno._03advice;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceA;

public class AroundAdviceTest {

    @Test
    public void testAround() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-aspectanno-advice-around.xml");
        IServiceA fooService = (IServiceA) context.getBean("fooService");
        // 被织入环绕增强的方法
        fooService.save(null);
        // 未被织入环绕增强的方法
        fooService.update(null);
        context.close();
    }

}
