package com.study.spring._05aop._01declaring_type._01aspectj_based._03advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class FooAdvice {
	
	/*
	 * 增强是在切点上执行的额外的程序逻辑，并且包含了连接点的方位信息
	 * Spring支持的增强类型包括：
	 * 前置增强 @Before
	 * 后置增强 @AfterReturning
	 * 环绕曾倩 @Around
	 * Final增强 @After
	 * 异常抛出增强 @AfterThrowing
	 * 引介增强 @DeclareParents
	 */

	/*
	 *	增强的配置由两部分组成：
	 *	增强类型
	 *	切点表达式: 既可以使切点方法签名，也可以是直接的表达式	
	 */
	
	// 前置增强 s
	// 切点方法签名
	@Before("com.xyz.myapp.SystemArchitecture.dataAccessOperation()")
    public void doBeforeAccessCheck() {
        // do extra work
    }
	
	// 切点表达式
	@Before("execution(* service.*.*(..))")
	public void doBeforeBussinessCheck(){
		//do extra work
	}
	
	// 前置增强 e
	
	// 后置增强 s
	@AfterReturning(pointcut="execution(* service.*.*(..))", 
			returning="retuVal") 	// 可以将切点方法返回值带到增强方法中，参数名和参数类型必须一致
	public void doAfterRetuAccessCheck(Object retuVal){
		//do extra work
	}
	// 后置增强 e
	
	// 异常抛出时增强 s
	@AfterThrowing(pointcut="execution(* service.*.*(..))", 
			throwing="ex")			// 将切点方法抛出的异常带入到增强方法中
	public void doAfterThrowAccessCheck(Exception ex){
		//do extra work
	}
	// 异常抛出时增强 e
	
	// Final增强 s
	@After("execution(* service.*.*(..))")
	public void doAfterAccessCheck(){
		//do extra work
	}
	// Final增强 e
	
	// 环绕增强 s
	@Around("execution(* service.*.*(..))")
	// 增强方法第一个参数必须为ProceedingJoinPoint，并且在方法内部需要执行pjp.proceed()方法执行目标逻辑。
	public Object doAroundAccessCheck(ProceedingJoinPoint pjp) throws Throwable{
		//do before extra work
		Object returnVal = pjp.proceed();
		// do after extra work
		return returnVal;
	}
	// 环绕增强 e
	
	// TODO advice 传参
	
	/*
	 * 如果一个连接点匹配多个增强，那么多个增强的执行顺序依靠切面类实现Ordered接口或标注@Order注解实现
	 * order值越小，越先执行
	 */
	
}
