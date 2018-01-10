package com.study.spring.core.aop.aspectanno._03advice;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterReturningAdvice {
    
    /**
     * 将目标方法的返回值作为增强的入参
     * @param returnVal
     */
    @AfterReturning(value="execution(* com.study.spring.core.aop.aspectanno._03advice..*.*(..))", returning="returnVal")
    public void afterReturning(Object returnVal) {
        System.out.println("call>>AfterReturningAdvice, return value: " + returnVal);
    }
}
