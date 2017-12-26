package com.study.spring.core.ioc.javaconfig.lookup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class LookupMethodAnnoConfig {

    @Bean
    @Scope("prototype")
    public PrototypeBean createPrototypeBean() {
        System.out.println("create a PrototypeBean");
        return new PrototypeBean();
    }
    
    
    @Bean
    public SingletonBean createSingletonBean() {
        return new SingletonBean() {
            
            @Override
            /**
             * 重写对prototype bean依赖的方法
             */
            public PrototypeBean getPrototypeBean() {
                // 此处并非直接方法调用，而是从IOC容器中获取Bean
                System.out.println("get prototypeBean");
                return createPrototypeBean();
            }
            
        };
    }
}
