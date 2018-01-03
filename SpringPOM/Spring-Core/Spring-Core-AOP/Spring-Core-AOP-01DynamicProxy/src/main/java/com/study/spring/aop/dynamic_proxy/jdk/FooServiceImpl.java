package com.study.spring.aop.dynamic_proxy.jdk;

public class FooServiceImpl implements FooService{

    @Override
    public void save() {
        System.out.println("call save()");
    }
}
