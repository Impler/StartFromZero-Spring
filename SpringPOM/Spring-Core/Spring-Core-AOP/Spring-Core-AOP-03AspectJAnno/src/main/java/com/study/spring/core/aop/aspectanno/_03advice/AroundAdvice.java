package com.study.spring.core.aop.aspectanno._03advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AroundAdvice {

    @Around("execution(* com.study.spring.core.aop.aspectanno._03advice..*.save(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("call AroundAdvice, before");
        //do before logic
        Object result = pjp.proceed();
        System.out.println("call AroundAdvice, after, target method result: " + result);
        //do after logic
        return result;
    }
}
