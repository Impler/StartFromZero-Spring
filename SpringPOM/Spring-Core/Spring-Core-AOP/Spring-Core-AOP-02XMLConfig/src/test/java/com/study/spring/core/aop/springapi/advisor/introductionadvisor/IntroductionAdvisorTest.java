package com.study.spring.core.aop.springapi.advisor.introductionadvisor;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advisor.Foo;

public class IntroductionAdvisorTest {

    private ClassPathXmlApplicationContext context;
    
    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advisor-introduction.xml");
    }
    @After
    public void afterClass() {
        context.close();
    }
    
    /**
     * 使用一个引介Advice对象创建引介切面
     */
    @Test
    public void testIntroductionAdvisorWithAdvice() {
        Object proxy = context.getBean("fooWithAllInterface");
        Assert.assertTrue(proxy instanceof Foo);
        Assert.assertTrue(proxy instanceof IServiceA);
        Assert.assertTrue(proxy instanceof IServiceB);
    }
    
    /**
     * 使用一个DynamicIntroductionAdvice和指定待增强的接口类型创建引介切面
     */
    @Test
    public void testIntroductionAdvisorWithAdviceAndClass() {
        Object proxy = context.getBean("fooWithOneInterface");
        Assert.assertTrue(proxy instanceof Foo);
        Assert.assertFalse(proxy instanceof IServiceA);
        Assert.assertTrue(proxy instanceof IServiceB);
    }
    /**
     * 使用一个引介Advice和IntroductionInfo创建引介切面
     */
    @Test
    public void testIntroductionAdvisorWithAdviceAndIntroInfo() {
        Object proxy = context.getBean("fooWithIntroductionInfo");
        Assert.assertTrue(proxy instanceof Foo);
        Assert.assertTrue(proxy instanceof IServiceA);
        Assert.assertFalse(proxy instanceof IServiceB);
    }

}
