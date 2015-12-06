package com.study.spring.aop.advisor.composable;

import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

import com.study.spring.aop.advisor.controlflow.WaiterDelegate;

public class GreetingComposablePointcut {
	
	public Pointcut getIntersectionPointcut(){
		ComposablePointcut cp = new ComposablePointcut();
		
		Pointcut p1 = new ControlFlowPointcut(WaiterDelegate.class, "service");
		NameMatchMethodPointcut p2 = new NameMatchMethodPointcut();
		p2.addMethodName("greetTo");
		
		return cp.intersection(p1).intersection((MethodMatcher)p2);
	}
}
