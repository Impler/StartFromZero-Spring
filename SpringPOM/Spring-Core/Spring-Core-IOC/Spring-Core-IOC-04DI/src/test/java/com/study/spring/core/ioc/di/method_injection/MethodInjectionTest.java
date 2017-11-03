package com.study.spring.core.ioc.di.method_injection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.ioc.di.method_injection.application_aware.SingletonBeanOfAppAware;
import com.study.spring.core.ioc.di.method_injection.look_up.SingletonBeanOfLookup;

public class MethodInjectionTest {

    private ClassPathXmlApplicationContext context;

    @Before
    public void before() {
        context = new ClassPathXmlApplicationContext("beans-di-method-injection.xml");
    }

    @After
    public void after() {
        if (null != context) {
            context.close();
        }
    }

    @Test
    public void testByApplicationAware(){
        SingletonBeanOfAppAware sig = (SingletonBeanOfAppAware) context.getBean("sigAware");
        // 每次调用获取新的非单例Bean
        sig.call();
        sig.call();
    }
    
    @Test
    public void testByLookup(){
        SingletonBeanOfLookup sig = (SingletonBeanOfLookup) context.getBean("sigLookup");
        // 从sig的toString()方法观察该对象为CGLIB动态生成的
        System.out.println(sig);
        // 每次调用获取新的非单例Bean
        sig.call();
        sig.call();
    }
}
