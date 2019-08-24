package com.study.spring.core.aop.springapi.advice.introduction;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class MyIntroductionAdvice extends DelegatingIntroductionInterceptor implements IServiceBar{

    private static final long serialVersionUID = -1824408105183169417L;

    @Override
    public void doBar() {
        System.out.println("call doBar()");
        
    }
}
