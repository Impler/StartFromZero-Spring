package com.study.spring.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTestObject {
	private ClassPathXmlApplicationContext ctx;
	private AnnotationConfigApplicationContext aCtx;
	@Before
	public void prepare(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		aCtx = new AnnotationConfigApplicationContext("anoAppCtx.xml");
	}
	@After
	public void close(){
		ctx.close();
		aCtx.close();
	}
	
	public ClassPathXmlApplicationContext getContext(){
		return ctx;
	}
	
	public Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
	
	public AnnotationConfigApplicationContext getAnotationContext(){
		return aCtx;
	}
	
	public Object getAnoBean(String beanName){
		return aCtx.getBean(beanName);
	}
	
}
