package com.study.spring.core.ioc.javaconfig.environment.propertysource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("/spring.env.propertysource.properties")
@ComponentScan("com.study.spring.core.ioc.javaconfig.environment.propertysource")
public class JavaAppConfig {

    @Autowired
    Environment env;
    
    @Bean
    public Foo getFoo() {
        Foo foo = new Foo();
        foo.setName(env.getProperty("env.propertysource.name"));
        return foo;
    }
}
