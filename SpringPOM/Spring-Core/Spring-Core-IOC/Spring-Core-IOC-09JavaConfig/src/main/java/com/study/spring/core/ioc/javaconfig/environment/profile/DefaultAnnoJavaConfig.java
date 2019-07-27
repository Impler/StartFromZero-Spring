package com.study.spring.core.ioc.javaconfig.environment.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultAnnoJavaConfig {
    
    @Bean
    public DataSource getEmbedDataSource() {
        return new EmbedDataSource();
    }
}
