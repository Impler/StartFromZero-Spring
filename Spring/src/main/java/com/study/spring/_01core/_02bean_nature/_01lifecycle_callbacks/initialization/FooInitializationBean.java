package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.initialization;

import org.springframework.beans.factory.InitializingBean;

public class FooInitializationBean implements InitializingBean {

	private String name;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("call --> afterPropertiesSet");
	}
	
	public void setName(String name) {
		System.out.println("set properties");
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
