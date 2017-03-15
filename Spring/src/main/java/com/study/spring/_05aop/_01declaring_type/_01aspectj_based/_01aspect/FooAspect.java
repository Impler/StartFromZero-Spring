package com.study.spring._05aop._01declaring_type._01aspectj_based._01aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
 * 使用@Aspect注解可以定义一个切面
 * Spring会自动扫描这些@Aspect标识的类，并用来配置Spring AOP
 * 但是@Aspect并不是Spring Bean，如果需要将该切面作为Spring Bean使用，还需要使用@Component注解
 * 或通过xml显示配置bean
 */
@Aspect
// 将该切面作为Spring Bean使用
@Component
public class FooAspect {

}
