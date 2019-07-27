package com.study.spring._05aop._01declaring_type._01aspectj_based._03advice._01advice_parameter;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AdviceParameter {

	/*
	 * 所有的增强方法都可以添加参数。
	 * 一般使用JoinPoint作为增强方法的第一个参数（环绕增强使用ProceedingJoinPoint，JoinPoint的一种实现）
	 * JoinPoint接口包含许多有用的方法包括：
	 * - getArgs() 返回目标方法参数
	 * - getThis() 返回代理对象本身
	 * - getTarget() 返回目标对象
	 * - getSignature() 返回目标方法签名
	 */
	
	/*
	 * 前面讲过，可以向后置增强方法中传入目标方法返回值，以及在异常抛出增强方法中传入异常对象。
	 * 同样的，增强方法也可以传入目标方法中的其他参数，使用args()切点函数
	 */
	@Before(value="execution(* service.*.*(..) &&"
			+ "args(account, ..)", argNames="account")
	public void validateAccount(FooAccount account){
		// do extra work
	}
	
	
	
	
}
