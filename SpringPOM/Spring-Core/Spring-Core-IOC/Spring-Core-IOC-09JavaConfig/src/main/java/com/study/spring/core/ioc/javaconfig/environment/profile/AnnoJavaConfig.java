package com.study.spring.core.ioc.javaconfig.environment.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Import(DefaultAnnoJavaConfig.class)
public class AnnoJavaConfig {
    
    @Bean
    @Profile("dev")
    public DataSource getDevDataSource() {
        return new DevDataSource();
    }
    
    @Bean
    @Profile("prd")
    public DataSource getProductionDataSource() {
        return new PrdDataSource();
    }
    
}
