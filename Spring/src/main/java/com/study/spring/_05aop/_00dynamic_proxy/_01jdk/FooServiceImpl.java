package com.study.spring._05aop._00dynamic_proxy._01jdk;

public class FooServiceImpl implements FooService {

	@Override
	public void doService() {
		System.out.println("do foo service");
	}

}
