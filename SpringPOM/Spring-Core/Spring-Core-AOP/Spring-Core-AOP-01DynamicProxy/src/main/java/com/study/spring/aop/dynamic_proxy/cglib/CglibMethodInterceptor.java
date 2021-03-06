package com.study.spring.aop.dynamic_proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibMethodInterceptor implements MethodInterceptor{

    Enhancer enhancer = new Enhancer();

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before service");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("after service");
        return result;
    }
}
