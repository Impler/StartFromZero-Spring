package com.study.spring._05aop._01declaring_type._03spring_api._02advisor._04composable;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

import com.study.spring._05aop._01declaring_type._03spring_api._02advisor._03control_flow.WaiterDelegate;


public class GreetingComposablePointcut {
	
	public Pointcut getIntersectionPointcut(){
		ComposablePointcut cp = new ComposablePointcut();
		// 定义一个control flow切点
		Pointcut p1 = new ControlFlowPointcut(WaiterDelegate.class, "service");
		// 定义一个静态方法匹配切点
		NameMatchMethodPointcut p2 = new NameMatchMethodPointcut();
		p2.addMethodName("greetTo");
		// 聚合切点
		return cp.intersection(p1).intersection((MethodMatcher)p2);
	}
}
