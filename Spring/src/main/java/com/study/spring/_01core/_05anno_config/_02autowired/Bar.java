package com.study.spring._01core._05anno_config._02autowired;

import org.springframework.core.Ordered;

public class Bar implements Ordered{

	private String name;
	// 顺序
	private int order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return this.order;
	}

	@Override
	public String toString() {
		return "Bar [name=" + name + ", order=" + order + "]";
	}
	
}
