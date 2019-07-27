package com.study.spring.core.ioc.di.method_injection.application_aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.study.spring.core.ioc.di.method_injection.PrototypeBean;

public class SingletonBeanOfAppAware implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void call(){
		// return a prototype bean at every invoke call() method
		PrototypeBean pro = this.applicationContext.getBean(PrototypeBean.class);
		
		// do other business...
		
		// print current bar reference
		System.out.println(pro);
	}
}
