package com.study.spring.core.aop.springapi.advisor.introductionadvisor;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class MyIntroductionAdvice extends DelegatingIntroductionInterceptor implements IServiceA, IServiceB{

    private static final long serialVersionUID = -1824408105183169417L;

    @Override
    public void doWorkA() {
        System.out.println("MyIntroductionAdvice>>doWorkA");
        
    }

    @Override
    public void doWorkB() {
        System.out.println("MyIntroductionAdvice>>doWorkB");
    }
}