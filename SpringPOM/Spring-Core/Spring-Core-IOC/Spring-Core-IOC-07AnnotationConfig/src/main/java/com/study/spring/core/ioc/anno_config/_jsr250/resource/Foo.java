package com.study.spring.core.ioc.anno_config._jsr250.resource;

import javax.annotation.Resource;


public class Foo {

    @Resource
    private IService service;
    
    public void doService(){
        service.doService();
    }
}
