package com.study.spring.core.aop.springapi.advice.introduction;

public class FooService implements IServiceFoo {

    @Override
    public void doFoo() {
        System.out.println("call doFoo()");
    }

}
