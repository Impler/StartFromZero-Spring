package com.study.spring.core.ioc.anno_config._jsr250.resource;

public class FooService implements IService{

    @Override
    public void doService() {
        System.out.println("do foo service");
    }
}
