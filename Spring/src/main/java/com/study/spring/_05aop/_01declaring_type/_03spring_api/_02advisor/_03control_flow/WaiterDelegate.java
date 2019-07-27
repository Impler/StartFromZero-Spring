package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._03control_flow;

import com.study.spring._05aop._01declaring_type._03spring_api.Seller;
import com.study.spring._05aop._01declaring_type._03spring_api.Waiter;

public class WaiterDelegate {
	private Waiter waiter;
	private Seller seller;

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public void service(String name) {
		waiter.greetTo(name);
		waiter.serveTo(name);
		seller.greetTo(name);
	}
}
