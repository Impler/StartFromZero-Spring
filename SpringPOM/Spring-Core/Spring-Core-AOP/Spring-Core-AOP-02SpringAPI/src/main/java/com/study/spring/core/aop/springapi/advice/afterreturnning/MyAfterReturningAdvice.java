package com.study.spring.core.aop.springapi.advice.afterreturnning;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class MyAfterReturningAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("MyAfterReturningAdvice>>target:" + target + ",method:" + method.getName() + ",returnVal:" + returnValue);
    }

}
