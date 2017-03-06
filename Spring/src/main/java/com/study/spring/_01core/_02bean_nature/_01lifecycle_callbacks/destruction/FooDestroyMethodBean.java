package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.destruction;

public class FooDestroyMethodBean {

	public void onDestroy(){
		System.out.println("call --> onDestroy()");
	}
	
}
