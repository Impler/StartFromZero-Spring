package com.study.spring.core.ioc.anno_config._autowired.simple;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.spring.core.ioc.anno_config._autowired.IService;

public class Foo {

    @Autowired
    private IService service;
    

    public void execute(){
        service.doService();
    }
}
