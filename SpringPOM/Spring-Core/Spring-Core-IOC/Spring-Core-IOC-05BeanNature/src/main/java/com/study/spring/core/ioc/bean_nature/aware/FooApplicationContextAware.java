package com.study.spring.core.ioc.bean_nature.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FooApplicationContextAware implements ApplicationContextAware {

    private ApplicationContext context;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public ApplicationContext getContext() {
        return context;
    }

}
