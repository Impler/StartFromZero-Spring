package com.study.spring.core.aop.springapi.advice.around;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("MyMethodInterceptor>>before");
        Object result = invocation.proceed();
        System.out.println("MyMethodInterceptor>>after");
        return result;
    }

}
