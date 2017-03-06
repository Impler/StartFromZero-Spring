package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.initialization;

import org.springframework.beans.factory.InitializingBean;

public class AllIn implements InitializingBean {
	
	private String name;

	public void init() throws Exception {
		System.out.println("all in call --> init-method");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("all in call --> afterPropertiesSet");
	}

	public void setName(String name) {
		System.out.println("all in set properties");
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
