package com.study.spring.core.aop.aspectanno._03advice;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceA;
import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceB;

public class IntroductionAdviceTest {

    @Test
    public void test() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-aspectanno-advice-introduction.xml");
        IServiceA fooService = (IServiceA) context.getBean("fooService");
        Assert.assertTrue(fooService instanceof IServiceB);
        IServiceB serviceB = (IServiceB) fooService;
        serviceB.doServiceB();
        context.close();
    }

}
