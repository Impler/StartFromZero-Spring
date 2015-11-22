package com.study.spring.bean.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.study.spring.base.domain.User;

public class InjectPB2SBBaseOnSpring implements BeanFactoryAware {
	
	private BeanFactory factory;
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = beanFactory;
	}
	
	public User getPrototypeUser(){
		return (User) this.factory.getBean("prototypeBean");
	}

}
