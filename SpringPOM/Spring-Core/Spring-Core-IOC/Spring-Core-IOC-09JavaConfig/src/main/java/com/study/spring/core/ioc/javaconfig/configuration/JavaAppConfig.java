package com.study.spring.core.ioc.javaconfig.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaAppConfig {

    @Bean
    public Bar createBar() {
        System.out.println("call createBar()");
        return new Bar();
    }

    @Bean
    public Foo createFoo() {
        System.out.println("call createFoo()");
        // 此处并非直接方法调用，而是在已注册的Bean中查找Bar
        Foo foo = new Foo(createBar());
        System.out.println("after create Foo");
        return foo;
    }

}
