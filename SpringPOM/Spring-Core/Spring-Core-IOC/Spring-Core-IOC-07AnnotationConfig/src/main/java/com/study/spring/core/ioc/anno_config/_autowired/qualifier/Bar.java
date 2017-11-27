package com.study.spring.core.ioc.anno_config._autowired.qualifier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.study.spring.core.ioc.anno_config._autowired.IService;

public class Bar {

    @Autowired
    @Qualifier("iService")
    private List<IService> services;
    
    public void doServices(){
        System.out.println("doServices>>");
        for(IService service: services){
            service.doService();
        }
    }
}
