package com.study.spring.core.aop.aspectanno._02pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MyPointcut {

    @Pointcut("execution(* ..*.*service(..))")
    private void showCurrentTime() {}
    
    @Pointcut("execution(* ..*.*Dao.*()")
    public void transaction() {}
    
    @Pointcut("showCurrentTime()")
    public void showlog() {}
}
