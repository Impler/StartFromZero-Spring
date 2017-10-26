package com.study.spring.core.ioc.bean_instantiating;

public class StaticFooFactory {

    /**
     * 静态工厂方法
     * @return
     */
    public static Foo createFoo() {
        System.out.println("call static factory method createFoo");
        return new Foo();
    }
}
