package com.study.spring._01core._04extension_points.bean_factory_post_processor;

public class Foo {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Foo [name=" + name + "]";
	}
	
}
