package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.dynamicpointcut;

import java.lang.reflect.Method;

import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class MyDynamicMethodMatcherPointcut extends DynamicMethodMatcherPointcut {

    
    /**
     * 静态方法检查
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        System.out.println("MyDynamicMethodMatcherPointcut>>" + targetClass.getName() + "." + method.getName());
        return method.getName().equals("sayHi");
    }

    /**
     * 动态方法检查，校验方法入参
     */
    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        System.out.println("MyDynamicMethodMatcherPointcut>>" + targetClass.getName() + "." + method.getName() + ", args=" + args);
        String name = (String) args[0];
        return "jack".equals(name);
    }

}
