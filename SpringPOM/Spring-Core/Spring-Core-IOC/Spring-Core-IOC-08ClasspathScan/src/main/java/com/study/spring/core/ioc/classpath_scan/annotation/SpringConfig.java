package com.study.spring.core.ioc.classpath_scan.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/**
 * 扫描并注册com.study.spring.core.ioc.classpath_scan及其子包中的组件类
 * 多个之间使用逗号、空格、分号等间隔
 */
@ComponentScan("com.study.spring.core.ioc.classpath_scan")
public class SpringConfig {

}
