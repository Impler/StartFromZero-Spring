package com.study.spring._01core._01ioc._03bean_instantiation;

public class FooFactory {

	/**
	 * static factory method
	 * @return
	 */
	public static Foo staticCreateFoo(){
		return new Foo();
	}
	
	/**
	 * factory method
	 * @return
	 */
	public Foo createFoo(){
		return new Foo();
	}
}