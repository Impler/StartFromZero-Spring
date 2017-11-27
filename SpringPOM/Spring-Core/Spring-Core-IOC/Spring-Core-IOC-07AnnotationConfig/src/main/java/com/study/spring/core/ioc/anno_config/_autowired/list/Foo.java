package com.study.spring.core.ioc.anno_config._autowired.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.spring.core.ioc.anno_config._autowired.IService;

public class Foo {

    @Autowired
    private List<IService> services;
    
    public void doServices(){
        for(IService service : services){
            service.doService();
        }
    }
}
