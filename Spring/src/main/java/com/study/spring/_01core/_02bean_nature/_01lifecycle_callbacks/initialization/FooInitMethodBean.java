package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.initialization;


public class FooInitMethodBean{

	private String name;
	
	public void init() throws Exception {
		System.out.println("call --> init-method");
	}
	
	public void setName(String name) {
		System.out.println("set properties");
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
