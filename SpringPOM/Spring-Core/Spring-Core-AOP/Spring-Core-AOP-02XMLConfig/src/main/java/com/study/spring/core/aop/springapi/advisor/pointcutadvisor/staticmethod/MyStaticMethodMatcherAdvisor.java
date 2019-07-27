package com.study.spring.core.aop.springapi.advisor.pointcutadvisor.staticmethod;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.study.spring.core.aop.springapi.advisor.Foo;

public class MyStaticMethodMatcherAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private static final long serialVersionUID = -6354782135955831064L;

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("sayHi");
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return Foo.class.equals(clazz);
            }
            
        };
    }

    
}
