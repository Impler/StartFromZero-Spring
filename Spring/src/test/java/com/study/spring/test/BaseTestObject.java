package com.study.spring.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTestObject {
	private ClassPathXmlApplicationContext ctx;
	@Before
	public void prepare(){
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	@After
	public void close(){
		ctx.close();
	}
	
	public ClassPathXmlApplicationContext getContext(){
		return ctx;
	}
	
	public Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
	
}
