package com.study.spring.core.ioc.anno_config._autowired.map;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.study.spring.core.ioc.anno_config._autowired.IService;

public class Foo {

    @Autowired
    private Map<String, IService> serviceMap;
    
    public void doServices(){
        System.out.println(serviceMap);
        Iterator<String> it = serviceMap.keySet().iterator();
        while(it.hasNext()){
            String name = it.next();
            System.out.println(name + ">>");
            serviceMap.get(name).doService();;
        }
    }
}
