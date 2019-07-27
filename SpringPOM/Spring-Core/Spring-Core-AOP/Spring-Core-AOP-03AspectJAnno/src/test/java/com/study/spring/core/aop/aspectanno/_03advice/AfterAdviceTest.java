package com.study.spring.core.aop.aspectanno._03advice;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceA;

public class AfterAdviceTest {

    @Test(expected=Exception.class)
    public void testAfter() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-aspectanno-advice-after.xml");
        IServiceA fooService = (IServiceA) context.getBean("fooService");
        // 仅触发finally增强
        fooService.update(null);
        // 触发异常抛出增强和finally增强
        fooService.save(null);
        context.close();
    }

}
