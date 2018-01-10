package com.study.spring.core.aop.aspectanno._03advice;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.aspectanno._03advice.objects.Foo;
import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceA;

public class BeforeAdviceTest {

    @Test
    public void testBefore() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "/beans-aop-aspectanno-advice-before.xml");
        IServiceA fooService = (IServiceA) context.getBean("fooService");
        Foo foo = new Foo();
        // 被织入前置增强的方法
        fooService.save(foo);
        // 未被织入增强的方法
        fooService.update(foo);
        context.close();
    }

}
