package com.study.spring._05aop._01declaring_type._03spring_api;

public class SellerA implements Seller {

	@Override
	public void greetTo(String name) {
		System.out.println("Seller greet to " + name);
	}

}
