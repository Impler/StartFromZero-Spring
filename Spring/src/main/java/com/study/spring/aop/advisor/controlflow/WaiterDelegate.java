package com.study.spring.aop.advisor.controlflow;

import com.study.spring.aop.Waiter;

public class WaiterDelegate {
	private Waiter waiter;

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	
	public void service(String name){
		waiter.greetTo(name);
		waiter.serveTo(name);
	}
}
