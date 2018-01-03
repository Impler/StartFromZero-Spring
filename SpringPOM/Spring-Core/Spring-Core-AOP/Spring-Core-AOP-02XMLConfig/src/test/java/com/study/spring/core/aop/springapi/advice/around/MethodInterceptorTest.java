package com.study.spring.core.aop.springapi.advice.around;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.core.aop.springapi.advice.around.FooService;
import com.study.spring.core.aop.springapi.advice.around.MyMethodInterceptor;

public class MethodInterceptorTest {

    @Test
    public void test() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvice(new MyMethodInterceptor());
        proxyFactory.setTarget(new FooService());
        FooService fooService = (FooService) proxyFactory.getProxy();
        fooService.save();
    }

}
