package com.study.spring._01core._05anno_config._02autowired;

import org.springframework.beans.factory.annotation.Autowired;

public class Foo {

	@Autowired
	private Bar bar;

	@Override
	public String toString() {
		return "Foo [bar=" + bar + "]";
	}
}
