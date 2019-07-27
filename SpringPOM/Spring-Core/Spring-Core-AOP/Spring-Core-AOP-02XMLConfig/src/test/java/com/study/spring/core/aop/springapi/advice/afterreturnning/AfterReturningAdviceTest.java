package com.study.spring.core.aop.springapi.advice.afterreturnning;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.core.aop.springapi.advice.afterreturnning.BarService;
import com.study.spring.core.aop.springapi.advice.afterreturnning.FooService;
import com.study.spring.core.aop.springapi.advice.afterreturnning.MyAfterReturningAdvice;

public class AfterReturningAdviceTest {

    @Test
    public void testAfterReturningAdvice() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MyAfterReturningAdvice());
        proxyFactory.setTarget(new FooService());
        FooService proxy = (FooService) proxyFactory.getProxy();
        proxy.save();
    }
    
    @Test(expected=Exception.class)
    public void testAfterReturningAdviceWithException() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MyAfterReturningAdvice());
        proxyFactory.setTarget(new BarService());
        BarService proxy = (BarService) proxyFactory.getProxy();
        // 目标方法中有异常抛出时，后置增强不执行
        proxy.save();
    }

}
