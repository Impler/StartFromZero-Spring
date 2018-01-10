package com.study.spring.core.aop.aspectanno._03advice.parameter;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.study.spring.core.aop.aspectanno._03advice.objects.Foo;

@Aspect
public class ParameterBindingAspect {
    
    /**
     * 绑定连接点方法入参
     * @param name
     * @param age
     */
    @Before("target(com.foo.Foo) && args(name, age)")
    public void bindingJoinpointParams(String name, int age) {
        // 增强逻辑
    }
    
    /**
     * 绑定代理对象
     */
    @Before("this(foo)")
    public void bindProxyObject(Foo foo) {
        // 增强逻辑
    } 
    
    /**
     * 绑定类注解对象
     * @param controller
     */
    @Before("@within(controller)")
    public void bindAnnotation(org.springframework.stereotype.Controller controller) {
        // 增强逻辑
    }
    
    /**
     * 绑定目标方法返回值
     * @param returnVal
     */
    @AfterReturning(value="execution(com.foo.Foo)", returning="returnVal")
    public void bindReturnValue(Object returnVal) {
        // 逻辑增强
    }
    
    /**
     * 绑定异常对象
     * @param exp
     */
    @AfterThrowing(value="execution(com.foo.Foo)", throwing="exp")
    public void bindThrowException(Throwable exp) {
        // 增强逻辑
    }
}
