package com.study.spring.core.ioc.anno_config._autowired;

import org.springframework.core.Ordered;

public class FooService implements IService, Ordered {

    @Override
    public void doService() {
        System.out.println("do foo service");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
