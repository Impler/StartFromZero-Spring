package com.study.spring.core.ioc.javaconfig.configuration;

public class Foo {

    private Bar bar;

    public Foo(Bar bar) {
        super();
        this.bar = bar;
    }

    public void foo() {
        this.bar.bar();
    }
}
