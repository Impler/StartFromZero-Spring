package com.study.spring.core.aop.aspectanno._03advice;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterThrowingAdvice {
    @AfterThrowing("execution(* com.study.spring.core.aop.aspectanno._03advice..*.save(..))")
    public void afterThrow() {
        System.out.println("call>>AfterThrowingAdvice");
    }
}
