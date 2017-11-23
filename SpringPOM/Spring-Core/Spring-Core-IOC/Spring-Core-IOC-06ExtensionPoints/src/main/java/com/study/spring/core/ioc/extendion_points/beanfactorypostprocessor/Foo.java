package com.study.spring.core.ioc.extendion_points.beanfactorypostprocessor;

public class Foo {

	private String name;

	public Foo() {
		super();
		System.out.println("call constructor method");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("call setter method-->" + name);
	}

	@Override
	public String toString() {
		return "Foo [name=" + name + "]";
	}

}
