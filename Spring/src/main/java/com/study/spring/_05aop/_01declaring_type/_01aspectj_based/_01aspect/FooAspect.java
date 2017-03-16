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

	/**
	 * 切面类像其他类一样，可以拥有自己的属性和方法，此外还可以在其内部声明pointcut（切点）, advice（增强） 和introduction（引介增强）等
	 */
}
