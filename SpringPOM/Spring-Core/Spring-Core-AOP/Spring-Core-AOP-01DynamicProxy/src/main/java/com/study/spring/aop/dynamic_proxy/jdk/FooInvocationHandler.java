package com.study.spring.aop.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class FooInvocationHandler implements InvocationHandler{

    private FooService foo;
    
    public FooInvocationHandler(FooService foo) {
        super();
        this.foo = foo;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke...");
        Object result = method.invoke(foo, args);
        System.out.println("after invoke...");
        return result;
    }

}
