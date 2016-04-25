package com.study.spring.aop.advice.introduction;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;
/**
 * Spring 定义了引介增强接口，IntroductionInterceptor，该接口没有定义任何方法。
 * Spring为该接口提供了DelegatingIntroductionInterceptor实现类，一般情况下继承该扩展类
 * @author Impler
 * @date 2016年4月25日
 */
public class GreetingIntroAdvice extends DelegatingIntroductionInterceptor implements IDoOthers {

	private static final long serialVersionUID = -2214509952121779745L;

	@Override
	public void settleAccounts() {
		System.out.println("advice msg: waiter settle account for guests");
	}

}
