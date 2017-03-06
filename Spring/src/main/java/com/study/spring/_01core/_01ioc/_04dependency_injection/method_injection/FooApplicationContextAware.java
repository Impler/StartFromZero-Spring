package com.study.spring._01core._01ioc._04dependency_injection.method_injection;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FooApplicationContextAware implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void call(){
		// return a prototype bean at every invoke call() method
		PrototypeBeanBar bar = this.applicationContext.getBean(PrototypeBeanBar.class);
		
		// do other business...
		
		// print current bar reference
		System.out.println(bar);
	}
}
