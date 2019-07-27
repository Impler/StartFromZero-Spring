package com.study.spring.core.aop.springapi.advice.before;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class KnownExceptionMethodBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("KnownExceptionMethodBeforeAdvice>>" + target);
        throw new KnownException();
    }

}
