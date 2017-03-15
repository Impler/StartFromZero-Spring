package com.study.spring._05aop._01declaring_type._01aspectj_based._00enable_aspectj;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
// 使用@EnableAspectJAutoProxy注解标识在@Configuration配置类上，开启@aspectJ支持
@EnableAspectJAutoProxy
public class AppConfig {

}
