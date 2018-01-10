package com.study.spring.core.aop.aspectanno._03advice.objects;

public class BarServiceBImpl implements IServiceB{

    @Override
    public void doServiceB() {
        System.out.println("call>>BarServiceBImpl.doServiceB()");
    }

}
