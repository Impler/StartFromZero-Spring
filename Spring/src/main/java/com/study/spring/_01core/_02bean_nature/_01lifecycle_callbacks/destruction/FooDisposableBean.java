package com.study.spring._01core._02bean_nature._01lifecycle_callbacks.destruction;

import org.springframework.beans.factory.DisposableBean;

public class FooDisposableBean implements DisposableBean {

	@Override
	public void destroy() throws Exception {
		System.out.println("call --> destroy()");
	}

}
