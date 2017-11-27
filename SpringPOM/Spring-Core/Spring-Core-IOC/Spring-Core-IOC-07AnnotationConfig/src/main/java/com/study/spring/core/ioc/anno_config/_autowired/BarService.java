package com.study.spring.core.ioc.anno_config._autowired;

import org.springframework.core.Ordered;

public class BarService implements IService, Ordered {

    @Override
    public void doService() {
        System.out.println("do bar service");
    }

    @Override
    public int getOrder() {
        
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
