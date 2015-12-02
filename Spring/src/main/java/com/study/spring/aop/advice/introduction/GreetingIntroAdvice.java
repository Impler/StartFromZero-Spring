package com.study.spring.aop.advice.introduction;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.IntroductionInterceptor;

public class GreetingIntroAdvice implements IntroductionInterceptor, IDoOthers {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		return null;
	}

	@Override
	public boolean implementsInterface(Class<?> intf) {
		return false;
	}

	@Override
	public void settleAccounts() {
		System.out.println("waiter settle account for guests");
	}

}
