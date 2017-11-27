package com.study.spring.core.ioc.anno_config._autowired.simple;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.spring.core.ioc.anno_config._autowired.BarService;

public class Bar {

    @Autowired
    private BarService service;
    
    public void execute(){
        service.doService();
    }
}
