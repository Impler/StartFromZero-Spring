package com.study.spring.core.ioc.anno_config._autowired.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.study.spring.core.ioc.anno_config._autowired.IService;

public class Foo {
    
    @Autowired
    @Qualifier("_barService")
    private IService service;
    
    public void doService(){
        System.out.println("doService>>");
        service.doService();
    }
    
}
