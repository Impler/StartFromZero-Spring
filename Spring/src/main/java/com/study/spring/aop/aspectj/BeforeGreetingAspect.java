package com.study.spring.aop.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

//标注切面类
@Aspect
public class BeforeGreetingAspect {
	
	//织入增强
	@Before("execution(* greetTo(..))")
	public void beforeGreeting(){
		System.out.println("How are you");
	}
}
