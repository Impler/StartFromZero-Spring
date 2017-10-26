package com.study.spring.core.ioc.bean_instantiating;

public class Foo {

    private String name;

    public Foo() {
        super();
        System.out.println("call Foo default constructor method");
    }

    public Foo(String name) {
        this.name = name;
        System.out.println("call Foo constructor with parameter method");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Bar {

        public Bar() {
            super();
            System.out.println("call Bar constructor method");
        }

    }
}
