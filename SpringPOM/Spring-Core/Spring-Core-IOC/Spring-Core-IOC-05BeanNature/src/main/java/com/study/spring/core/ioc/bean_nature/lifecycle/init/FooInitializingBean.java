package com.study.spring.core.ioc.bean_nature.lifecycle.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;

public class FooInitializingBean implements InitializingBean {

    private String name;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("call setter method");
        this.name = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("call afterPropertiesSet");
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("call @PostConstruct");
    }
    
    public void initMethod(){
        System.out.println("call init-method");
    }
}
