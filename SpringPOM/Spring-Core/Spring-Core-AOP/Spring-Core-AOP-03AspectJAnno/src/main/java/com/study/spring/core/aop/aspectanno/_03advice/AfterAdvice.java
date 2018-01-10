package com.study.spring.core.aop.aspectanno._03advice;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterAdvice {

    @After("execution(* com.study.spring.core.aop.aspectanno._03advice..*(..))")
    public void after() {
        System.out.println("call>>AfterAdvice");
    }
}
