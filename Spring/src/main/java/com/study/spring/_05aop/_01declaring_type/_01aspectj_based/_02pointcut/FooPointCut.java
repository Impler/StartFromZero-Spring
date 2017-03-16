package com.study.spring._05aop._01declaring_type._01aspectj_based._02pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class FooPointCut {

	/*
	 * 切点的定义由两部分组成：
	 * 1 切点方法签名
	 * 	任意参数的普通方法，但返回值必须为void
	 * 2 切点表达式
	 * 	用于筛选特定的连接点
	 * spring中的切点只会匹配public方法
	 */
	
	/*
	 * 切点表达式
	 * 切点表达式由关键字和操作参数组成，如execution(* service(..))，execution是关键字，* service(..)为操作参数。
	 * 
	 * Spring支持9个@AspectJ切点表达式函数，他们用不同的方式描述目标类的连接点，根据描述对象的不同，可以分为4类：
	 * 方法切点函数：通过描述目标类方法信息定义连接点 execution(), @annotation()
	 * 方法入参切点函数：通过描述目标类方法入参的信息定义连接点 args(), @args()
	 * 目标类切点函数：通过描述目标类类型信息定义连接点 within(), target(), @within(), @target()
	 * 代理类切点函数：通过描述目标类的代理类的信息定义连接点 this()	
	 * 
	 * 类别		函数				入参				解释
	 * 方法切点函数
	 * 			execution()		方法匹配模式串			满足某一匹配模式的所有目标类方法连接点
	 * 			@annotation()	方法注解类名			标注了特定注解的目标方法连接点
	 * 方法入参切点函数	
	 * 			args()			类名				通过判别目标类方法运行时入参对象的类型定义指定连接点
	 * 			@args()			类型注解类名			通过判别目标方法运行时入参对象的类是否标注特定注解来指定连接点
	 * 目标类切点函数
	 * 			within()		类名匹配串			特定范围内的所有连接点
	 * 			target()		类名				假如目标类按类型匹配于指定类，则目标类的所有连接点匹配这个切点
	 * 			@within()		类型注解类名			假如目标类按类型匹配于某个类A，且类A标注了特定的注解，则目标类的所有连接点匹配这个切点
	 * 			@target()		类型注解类名			目标类标注了特定注解，则目标类所有连接点匹配该切点
	 * 代理类切点函数	this()			类名				代理类按类型匹配于指定类，则被代理的目标类所有连接点匹配切点。
	 */
	
	/*
	 * execution()函数格式为：
	 * execution(<修饰符模式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?)
	 */
	
	/*
	 * 切点函数可以使用通配符：*、.、+
	 * *：匹配任意字符，但只能匹配一个元素
	 * .：匹配任意字符，可以匹配多个元素。表示类时，不许和*联合使用；表示入参时则单独使用
	 * +：表示按类型匹配指定类的子类或实现类，包括类本身，必须跟在类名后面
	 * 
	 * 通配符的支持程度：
	 * 支持所有通配符：execution()、within()
	 * 仅支持+通配符：args()、this()、target()
	 * 不支持通配符：@args()、@within、@target()、@annotation()
	 * 
	 * 切点表达式之间还可以进行逻辑原酸，组成符合切点,spring支持3中逻辑运算符:
	 * && 与
	 * || 或
	 * ! 非
	 * 为了兼容xml的配置，spring提供等效的操作符：and、or、not
	 * 
	 * 示例：
	 * execution(public * *(..))：匹配所有public方法
	 * execution(* set*(..))：匹配所有以set开头的方法
	 * execution(* com.xyz.service.AccountService.*(..))：匹配AccountService类中的所有方法
	 * execution(* com.xyz.service.*.*(..))：匹配service包下所有类中的所有方法
	 * execution(* com.xyz.service..*.*(..))：匹配service包及其子包下所有类中的所有方法
	 * 
	 */
	@Pointcut("execution(* service.*.*(..))")	// 切点表达式
	public void writeLog(){}				// 切点方法签名，只做签名，不做其他使用，不进行调用
}
