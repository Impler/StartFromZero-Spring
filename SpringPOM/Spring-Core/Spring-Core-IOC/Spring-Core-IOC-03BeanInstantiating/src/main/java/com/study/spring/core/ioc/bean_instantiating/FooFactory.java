package com.study.spring.core.ioc.bean_instantiating;

public class FooFactory {

    
    public FooFactory() {
        super();
        System.out.println("instantiate common factory bean");
    }

    /**
     * 普通工厂方法
     * @return
     */
    public Foo createFoo() {
        System.out.println("call common factory method createFoo");
        return new Foo();
    }
}
