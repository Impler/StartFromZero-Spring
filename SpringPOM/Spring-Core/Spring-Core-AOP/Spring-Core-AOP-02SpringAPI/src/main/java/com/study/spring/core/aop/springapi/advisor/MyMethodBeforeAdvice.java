package com.study.spring.core.aop.springapi.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class MyMethodBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("MyMethodBeforeAdvice>>" + target.getClass().getName() + "." + method.getName());
    }

}
