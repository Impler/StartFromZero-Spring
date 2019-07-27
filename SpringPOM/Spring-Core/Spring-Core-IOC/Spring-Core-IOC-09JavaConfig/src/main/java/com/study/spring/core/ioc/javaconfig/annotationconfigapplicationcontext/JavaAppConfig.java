package com.study.spring.core.ioc.javaconfig.annotationconfigapplicationcontext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaAppConfig {
    
    @Bean
    public Foo createFoo() {
        return new Foo();
    }
    
    @Bean
    public BarService createBarService() {
        return new BarService();
    }
}
