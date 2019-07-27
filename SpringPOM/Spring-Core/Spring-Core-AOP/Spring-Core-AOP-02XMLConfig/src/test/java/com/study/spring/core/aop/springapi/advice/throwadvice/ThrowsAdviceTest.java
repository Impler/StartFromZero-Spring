package com.study.spring.core.aop.springapi.advice.throwadvice;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.core.aop.springapi.advice.throwadvice.FooService;
import com.study.spring.core.aop.springapi.advice.throwadvice.MyThrowsAdvice;

public class ThrowsAdviceTest {

    @Test(expected=SQLException.class)
    public void test() throws Throwable {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MyThrowsAdvice());
        proxyFactory.setTarget(new FooService());
        FooService proxy = (FooService) proxyFactory.getProxy();
        proxy.save();
    }

}
