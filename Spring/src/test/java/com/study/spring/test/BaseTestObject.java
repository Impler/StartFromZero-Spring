package com.study.spring.test;

import org.junit.After;
import org.junit.Before;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public abstract class BaseTestObject {
	private String configFileName;
	private AbstractApplicationContext ctx;
	
	public BaseTestObject() {
		super();
		this.configFileName = getConfigFileName();
	}
	
	protected abstract String getConfigFileName();
	
	@Before
	public void prepare(){
		if(!StringUtils.isEmpty(this.configFileName)){
			if(this.configFileName.startsWith("a")){
				ctx = new AnnotationConfigApplicationContext(configFileName);
			}else{
				ctx = new ClassPathXmlApplicationContext(configFileName);
			}
		}
	}
	@After
	public void close(){
		ctx.close();
	}
	
	public AbstractApplicationContext getContext(){
		return ctx;
	}
	
	public Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
}
