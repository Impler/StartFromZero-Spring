package com.study.spring._01core._05anno_config._03JSR250._02lifecycle_callbacks;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;

public class Initialization implements InitializingBean{

	@PostConstruct
	public void postConstruct(){
		System.out.println("post construct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after properties set");
	}
	
	public void init(){
		System.out.println("init");
	}
	
}
