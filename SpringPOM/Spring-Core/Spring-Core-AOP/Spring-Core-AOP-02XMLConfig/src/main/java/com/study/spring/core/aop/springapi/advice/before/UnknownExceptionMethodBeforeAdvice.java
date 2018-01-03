package com.study.spring.core.aop.springapi.advice.before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class UnknownExceptionMethodBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("UnknownExceptionMethodBeforeAdvice>>" + target);
        throw new NullPointerException();
    }

}
