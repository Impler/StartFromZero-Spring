package com.study.spring.aop.advisor.dynamic;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import com.study.spring.aop.Waiter;

public class GreetingDynamicPointcut extends DynamicMethodMatcherPointcut{

	/**
	 * 静态切点检查
	 */
	@Override
	public ClassFilter getClassFilter() {
		return new ClassFilter(){

			@Override
			public boolean matches(Class<?> clazz) {
				return Waiter.class.isAssignableFrom(clazz);
			}
		};
	}

	/**
	 * 静态切点检查
	 */
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "greetTo".equals(method.getName());
	}

	/**
	 * 动态切点检查
	 */
	@Override
	public boolean matches(Method method, Class<?> targetClass, Object[] args) {
		String guestName = (String) args[0];
		System.out.println("advice msg: Dynamic parameter check: " + "TOM".equals(guestName)) ;
		return "TOM".equals(guestName);
	}

}
