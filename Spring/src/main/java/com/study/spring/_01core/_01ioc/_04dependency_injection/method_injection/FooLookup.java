package com.study.spring._01core._01ioc._04dependency_injection.method_injection;

public abstract class FooLookup {

	public abstract PrototypeBeanBar getBar();
	
	public void call(){
		// print current bar reference
		System.out.println(getBar());
	}
}
