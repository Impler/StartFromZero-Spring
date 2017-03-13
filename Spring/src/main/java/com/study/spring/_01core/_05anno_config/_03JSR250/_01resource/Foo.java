package com.study.spring._01core._05anno_config._03JSR250._01resource;

import javax.annotation.Resource;

public class Foo {

	@Resource
	private Bar ba;

	public Bar getBa() {
		return ba;
	}

	public void setBa(Bar ba) {
		this.ba = ba;
	}

	@Override
	public String toString() {
		return "Foo [ba=" + ba + "]";
	}
	
}
