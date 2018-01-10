package com.study.spring.core.aop.aspectanno._03advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BeforeAdvice {
    
    @Before("execution(* com.study.spring.core.aop.aspectanno._03advice..*.save(..))")
    public void before() {
        System.out.println("call before advice");
    }
}
