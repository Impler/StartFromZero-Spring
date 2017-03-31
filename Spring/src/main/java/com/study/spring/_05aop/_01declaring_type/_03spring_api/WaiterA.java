package com.study.spring._05aop._01declaring_type._03spring_api;

public class WaiterA implements Waiter {

	@Override
	public void greetTo(String name) {
		System.out.println("greet to " + name);
	}

	@Override
	public void serveTo(String name) {
		System.out.println("serve to " + name);
	}

	@Override
	public void cleanTable() throws Exception{
		System.out.println("waiter clean table...");
		throw new Exception("a disk falls to the ground ");
	}
}
