package com.study.spring.aop.advisor;

public class SellerA implements Seller {

	@Override
	public void greetTo(String name) {
		System.out.println("Seller greet to " + name);
	}

}
