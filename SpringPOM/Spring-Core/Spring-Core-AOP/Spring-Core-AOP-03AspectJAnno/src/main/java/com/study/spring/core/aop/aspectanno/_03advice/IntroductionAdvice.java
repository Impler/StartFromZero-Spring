package com.study.spring.core.aop.aspectanno._03advice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

import com.study.spring.core.aop.aspectanno._03advice.objects.BarServiceBImpl;
import com.study.spring.core.aop.aspectanno._03advice.objects.IServiceB;

@Aspect
public class IntroductionAdvice {

    @DeclareParents(value = "com.study.spring.core.aop.aspectanno._03advice.objects.Foo1ServiceAImpl+", defaultImpl = BarServiceBImpl.class)
    private IServiceB serviceB;
}
