package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._01static_method._01static_method_name;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.study.spring.aop.Waiter;

public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

	private static final long serialVersionUID = -2056422808628779837L;

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		//匹配greetTo方法
		return "greetTo".equals(method.getName());
	}

	@Override
	public ClassFilter getClassFilter() {
		super.setClassFilter(new ClassFilter(){
			@Override
			public boolean matches(Class<?> clazz) {
				//匹配waiter类或其子类
				return Waiter.class.isAssignableFrom(clazz);
			}
			
		});
		return super.getClassFilter();
	}



	
	
	

}
