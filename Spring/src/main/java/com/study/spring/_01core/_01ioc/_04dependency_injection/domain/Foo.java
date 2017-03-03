package com.study.spring._01core._01ioc._04dependency_injection.domain;

public class Foo {

	private Bar bar;
	private Baz baz;

	public Foo() {
		super();
	}

	public Foo(Bar bar, Baz baz) {
		super();
		this.bar = bar;
		this.baz = baz;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public void setBaz(Baz baz) {
		this.baz = baz;
	}

	public Bar getBar() {
		return bar;
	}

	public Baz getBaz() {
		return baz;
	}

}
