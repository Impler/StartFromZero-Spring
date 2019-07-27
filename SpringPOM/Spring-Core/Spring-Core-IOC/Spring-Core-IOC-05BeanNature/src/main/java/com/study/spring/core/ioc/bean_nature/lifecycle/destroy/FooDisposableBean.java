package com.study.spring.core.ioc.bean_nature.lifecycle.destroy;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;

public class FooDisposableBean implements DisposableBean{

    @Override
    public void destroy() throws Exception {
        System.out.println("call destroy");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("call @PreDestroy");
    }
    
    public void destroyMethod(){
        System.out.println("call destryo-method");
    }
}
