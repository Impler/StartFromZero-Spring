package com.study.spring._01core._05anno_config._02autowired._01qualifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.study.spring._01core._05anno_config._02autowired.Bar;

public class Foo {

	@Autowired
	@Qualifier("barA")
	private Bar bar;

	@Override
	public String toString() {
		return "Foo [bar=" + bar + "]";
	}
}
