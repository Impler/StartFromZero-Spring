package com.study.spring.aop;

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
		throw new Exception("a disk fells to the ground ");
	}
}
