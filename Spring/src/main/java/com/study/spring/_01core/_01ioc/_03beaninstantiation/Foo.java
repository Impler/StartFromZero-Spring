package com.study.spring._01core._01ioc._03beaninstantiation;

public class Foo {

	private String name;
	
	public Foo() {
		super();
		System.out.println("default constructor method is invoked");
	}

	
	public Foo(String name) {
		this.name = name;
		System.out.printf("constructor method with parameter [%s] is invoked\n", name);
	}

	

	public void test(){
		System.out.println("foo --> test");
	}
	
	@Override
	public String toString() {
		return "Foo [name=" + name + "]";
	}
	
	public static class Bar{

		@Override
		public String toString() {
			return "Bar []";
		}

		public void test() {
			System.out.println("bar --> test");
		}
	}
}
