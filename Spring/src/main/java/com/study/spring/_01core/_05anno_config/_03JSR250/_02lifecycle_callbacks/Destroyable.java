package com.study.spring._01core._05anno_config._03JSR250._02lifecycle_callbacks;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;

public class Destroyable implements DisposableBean{

	@Override
	public void destroy() throws Exception {
		System.out.println("disposable");
	}
	
	public void destroyMethod(){
		System.out.println("destroy-method");
	}

	@PreDestroy
	public void preDestroy(){
		System.out.println("pre destroy");
	}
}
