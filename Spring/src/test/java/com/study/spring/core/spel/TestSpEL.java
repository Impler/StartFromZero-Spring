package com.study.spring.core.spel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;


public class TestSpEL {
	
	/**
	 * Spring Expression Language (SpEL)支持在运行时查询和操作对象，其语法类似于EL表达式，但又提供了额外的功能
	 * SpEL提供以下表达式
	 * 1. 文本表达式(Literal expressions)
	 * 2. 布尔类和关系操作(Boolean and relational operators)
	 * 3. 正则表达式(Regular expressions)
	 * 4. 类表达式(Class expressions)
	 * 5. 访问属性、数组、集合和map(Accessing properties, arrays, lists, maps)
	 * 6. 方法调用(Method invocation)
	 * 7. 关系操作(Relational operators)
	 * 8. 赋值操作(Assignment)
	 * 9. 调用构造函数(Calling constructors)
	 * 10. 引用bean(Bean references)
	 * 11. 数组构造(Array construction)
	 * 12. 行内集合(Inline lists)
	 * 13. 行内map(Inline maps)
	 * 14. 三元操作(Ternary operator)
	 * 15. 变量(Variables)
	 * 16. 自定义方法(User defined functions)
	 * 17. 集合映射(Collection projection)
	 * 18. 集合选择(Collection selection)
	 * 19. 模板表达式(Templated expressions)
	 */
	
	@Before
	public void before(){
		System.out.println("****test spel start****");
	}
	@After
	public void after(){
		System.out.println("****test spel end****");
	}
	
	@Test
	/**
	 * 字面量表达式
	 * 字面量表达式支持字符串，数字（整型，实数，十六进制等），布尔型和null，字符串用单引号包裹
	 */
	public void testLiteralEp(){
		//step1 create Expression Parser
		ExpressionParser parser = new SpelExpressionParser();
		//step2 parse expression
		Expression strExp = parser.parseExpression("'Hello World'");
		Expression numExp = parser.parseExpression("0x321abce3");
		Expression boolExp = parser.parseExpression("TrUe");
		Expression nullExp = parser.parseExpression("NulL");
		//step3 get value
		String strVal = (String) strExp.getValue();
		int numVal = (Integer) numExp.getValue();
		boolean boolVal = (Boolean) boolExp.getValue();
		Object nullVal = nullExp.getValue();
		System.out.println("strVal: " + strVal);
		System.out.println("numVal: " + numVal);
		System.out.println("boolVal: " + boolVal);
		System.out.println("nullVal: " + nullVal);
	}
	
	@Test
	/**
	 * 算数运算表达式，支持加(+)、减(-)、乘(*)、除(/)、求余(%)和幂运算(^)
	 */
	public void testArithmeticEp(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression addExp = parser.parseExpression("1 + 2");
		Expression subExp = parser.parseExpression("3 - 4");
		Expression mulExp = parser.parseExpression("5 * 6");
		Expression divExp = parser.parseExpression("8 / 3");
		Expression modExp = parser.parseExpression("9 % 4");
		Expression powExp = parser.parseExpression("2 ^ 3");
		System.out.println("1 + 2 = " + addExp.getValue(Integer.class));
		System.out.println("3 - 4 = " + subExp.getValue(Integer.class));
		System.out.println("5 * 6 = " + mulExp.getValue(Integer.class));
		System.out.println("8 / 3 = " + divExp.getValue(Integer.class));
		System.out.println("8 / 3 = " + divExp.getValue(Float.class));
		System.out.println("9 % 4 = " + modExp.getValue(Integer.class));
		System.out.println("2 ^ 3 = " + powExp.getValue(Integer.class));
	}
	
	@Test
	/**
	 * 关系运算表达式，等于（==/eq）、不等于(!=/ne)、大于(>/gt)、大于等于(>=/ge)、小于(</lt)、小于等于(<=/le)，区间（between）运算
	 */
	public void testRelationEp(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression eqExp = parser.parseExpression("1 == 2");
		Expression neExp = parser.parseExpression("1 != 2");
		Expression gtExp = parser.parseExpression("1 gt 2");
		Expression geExp = parser.parseExpression("1 ge 2");
		Expression ltExp = parser.parseExpression("1 < 2");
		Expression leExp = parser.parseExpression("1 <= 2");
		Expression betweenExp = parser.parseExpression("1 between {1, 2}");
		System.out.println("1 == 2 ? " + eqExp.getValue(Boolean.class));
		System.out.println("1 != 2 ? " + neExp.getValue(Boolean.class));
		System.out.println("1 gt 2 ? " + gtExp.getValue(Boolean.class));
		System.out.println("1 ge 2 ? " + geExp.getValue(Boolean.class));
		System.out.println("1 < 2 ? " + ltExp.getValue(Boolean.class));
		System.out.println("1 <= 2 ? " + leExp.getValue(Boolean.class));
		System.out.println("1 between {1, 2} ? " + betweenExp.getValue(Boolean.class));
	}
	
	
	@Test
	/**
	 * 逻辑表达式，且（and）、或(or)、非(!或NOT)， 不支持java中的&&和||
	 */
	public void testLogicEp(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression andExp = parser.parseExpression("1 == 2 and 1 < 2");
		Expression orExp = parser.parseExpression("1 == 2 or 1 < 2");
		Expression notExp = parser.parseExpression("!(1 == 2)");
		System.out.println("1 == 2 and 1 < 2 ? " + andExp.getValue(Boolean.class));
		System.out.println("1 == 2 or 1 < 2 ? " + orExp.getValue(Boolean.class));
		System.out.println("!(1 == 2) ? " + notExp.getValue(Boolean.class));
	}
	
	@Test
	/**
	 * 三目运算表达式：表达式1?表达式2：表达式3
	 * 简化的三目表达式1?:表达式2，当表达式1为非null时，返回表达式1，否则返回表达式2
	 */
	public void testTernaryEp(){
		ExpressionParser parser = new SpelExpressionParser();
		EvaluationContext context = new StandardEvaluationContext();
		context.setVariable("ep1", null);
		context.setVariable("ep2", "ep2");
		Expression ter1Exp = parser.parseExpression("1 == 2? true : false");
		Expression ter2Exp = parser.parseExpression("#ep1?: null");
		Expression ter3Exp = parser.parseExpression("#ep2?: null");
		System.out.println("1 == 2? false : true = " + ter1Exp.getValue(Boolean.class));
		System.out.println("#ep1?: null = " + ter2Exp.getValue(context, String.class));
		System.out.println("#ep2?: null = " + ter3Exp.getValue(context, String.class));
	}
	
	@Test
	/**
	 * 正则表达式，格式为"str matches regex"
	 */
	public void testRegexEp(){
		ExpressionParser parser = new SpelExpressionParser();
		Expression regexExp = parser.parseExpression("'123' matches '\\d*'");
		System.out.println("'123' matches '\\d*' ? " + regexExp.getValue(Boolean.class));
	}
	
	@Test//(expected = SpelEvaluationException.class)
	/**
	 * 类相关表达式
	 */
	public void testClassEp() throws NoSuchMethodException, SecurityException{
		ExpressionParser parser = new SpelExpressionParser();
		/*
		 * 类类型表达式
		 * 使用"T(Type)"来表示java.lang.Class实例，"Type"必须是类全限定名，"java.lang"包除外；使用类类型表达式还可以进行访问类静态方法及类静态字段
		 */
		String id1 = parser.parseExpression("T(com.study.spring.core.spel.Student).IDENTITY").getValue(String.class);
		String id2 = parser.parseExpression("T(com.study.spring.core.spel.Student).getIdentity()").getValue(String.class);
		System.out.println("T(com.study.spring.core.spel.Student).IDENTITY = " + id1);
		System.out.println("T(com.study.spring.core.spel.Student).getIdentity() = " + id2);
		
		/*
		 * 类实例化
		 * 使用new关键字
		 */
		Student stu1 = (Student) parser.parseExpression("new com.study.spring.core.spel.Student(1, '张三', 'M')").getValue();
		System.out.println("new com.study.spring.core.spel.Student = " + stu1);
		
		/*
		 * instanceof表达式
		 * obj instanceof type 
		 */
		boolean bInstanceof = parser.parseExpression("'abc' instanceof T(String)").getValue(Boolean.class);
		System.out.println("'abc' instanceof T(String) = " + bInstanceof);
		
		/*
		 * 变量定义及引用
		 * 变量定义通过EvaluationContext接口的setVariable(variableName, value)方法定义；
		 * 在表达式中使用"#variableName"引用；对于当前上下文对象的属性或方法，可以直接使用属性或方法名访问
		 * 使用"#root"引用根对象，使用"#this"引用当前上下文对象
		 */
		StandardEvaluationContext context = new StandardEvaluationContext(stu1);
		context.setVariable("v1", "v1-value");
		String v1 = parser.parseExpression("#v1").getValue(context, String.class);
		Student root = (Student) parser.parseExpression("#root").getValue(context);
		// 此处#this = #root
		Student oThis = (Student) parser.parseExpression("#this").getValue(context);
		System.out.println("v1 = " + v1);
		System.out.println("#root = " + root);
		System.out.println("#this = " + oThis);
		
		/*
		 * 自定义函数
		 * 目前只支持类静态方法注册为自定义函数
		 */
		//静态方法
		Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
		//成员方法
		Method getName = stu1.getClass().getDeclaredMethod("getName");
		context.registerFunction("parseInt", parseInt);
		//添加成员方法
		context.registerFunction("getName", getName);
		//setVariable也同样可以实现自定义函数
		context.setVariable("parseInt2", parseInt);
		
		System.out.println("#parseInt('3') = " + parser.parseExpression("#parseInt('3')").getValue(context, Integer.class));
		System.out.println("#parseInt2('3') = " + parser.parseExpression("#parseInt2('3')").getValue(context, Integer.class));
		//将会抛出异常SpelEvaluationException: EL1039E:(pos 0): Only static methods can be called via function references
		//parser.parseExpression("#getName()").getValue(context);
		
		/*
		 * 赋值表达式
		 */
		parser.parseExpression("#this.id = 2").getValue(context, String.class);
		Student stu2 = (Student) parser.parseExpression("#this").getValue(context);
		System.out.println("stu2 = " + stu2);
		System.out.println("stu1 == stu2 ? " + (stu1 == stu2));
		
		/*
		 * 引用bean
		 * 使用@beanName引用bean，在引用Bean时需要使用BeanResolver接口实现来查找Bean，Spring提供BeanFactoryResolver实现
		 */
//		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("helloworld.xml");
//		context.setBeanResolver(new BeanFactoryResolver(ctx));
//		IUserDao userDao = (IUserDao) parser.parseExpression("@userDao").getValue(context);
//		userDao.save();
	}
	
	@Test
	/**
	 * 集合操作
	 */
	public void testCollection(){
		ExpressionParser parser = new SpelExpressionParser();
		/*
		 * 内联List
		 * 对于文本表达式，将返回java.util.Collections.UnmodifiableRandomAccessList
		 * 只要有一个不是字面量表达式，将只返回原始List
		 */
		@SuppressWarnings("rawtypes")
		List list1 = (List) parser.parseExpression("{1, 2, 3}").getValue();
		@SuppressWarnings("rawtypes")
		List list2 = (List) parser.parseExpression("{1, 2, 1 + 2}").getValue();
		//java.util.Collections$UnmodifiableRandomAccessList
		System.out.println("{1, 2, 3} : " + list1.getClass().getName());
		System.out.println("{1, 2, 1 + 2} : " + list2.getClass().getName());
		//通过下标访问
		System.out.println("{1, 2, 3}[0] = " + parser.parseExpression("{1, 2, 3}[0]").getValue());
		
		/*
		 * 数组操作 
		 */
		// 定义一维数组并初始化
		int[] array1 = parser.parseExpression("new int[2]{1,2}").getValue(int[].class);
		// 定义一维数组不初始化
		int[] array2 = parser.parseExpression("new int[1]").getValue(int[].class);
		array1[0] = 2;
		array2[0] = 2;
		System.out.println("array1 = " + Arrays.toString(array1));
		System.out.println("array2 = " + Arrays.toString(array2));
		//多维数组不能再声明时初始化
		
		/*
		 * map操作 
		 */
		@SuppressWarnings("rawtypes")
		Map map = parser.parseExpression("{'k1':'v1', 'k2':'v2'}").getValue(Map.class);
		System.out.println("{'k1':'v1', 'k2':'v2'} : " + map);
		// 通过key访问value
		System.out.println("{'k1':'v1', 'k2':'v2'}['k1'] = " + parser.parseExpression("{'k1':'v1', 'k2':'v2'}[k1]").getValue());
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	/**
	 * 集合投影
	 * 根据集合中的元素中通过选择来构造另一个集合，该集合和原集合具有相同数量的元素；SpEL使用"(list|map).![投影表达式]"来进行投影运算
	 * #this表示集合中的每个元素，在map中则表示Map.Entry
	 */
	public void testCollectionProjection(){
		ExpressionParser parser = new SpelExpressionParser();
		//准备数据
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		list.add("a1");
		list.add("b2");
		list.add("c3");
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("k1", "v1");
		map.put("k2", "v2");
		
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("c", list);
		context.setVariable("m", map);
		
		System.out.println("#c.![#this] = " + parser.parseExpression("#c.![#this]").getValue(context));
		System.out.println("#c.![#this.charAt(1)] = " + parser.parseExpression("#c.![#this.charAt(1)]").getValue(context));
		System.out.println("#m.![#this] = " + parser.parseExpression("#m.![#this]").getValue(context));
		System.out.println("#m.![#this.key] = " + parser.parseExpression("#m.![#this.key]").getValue(context));
		System.out.println("#m.![#this.value] = " + parser.parseExpression("#m.![#this.value]").getValue(context));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	/**
	 * 集合选择
	 * 根据原集合通过条件表达式选择出满足条件的元素并构造为新的集合
	 * SpEL使用"(list|map).?[选择表达式]"，其中选择表达式结果必须是boolean类型，如果true则选择的元素将添加到新集合中，false将不添加到新集合中
	 */
	public void testCollectionSelect(){
		ExpressionParser parser = new SpelExpressionParser();
		//准备数据
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		@SuppressWarnings("rawtypes")
		Map map = new HashMap();
		map.put("k1", "v1");
		map.put("k2", "v2");
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("c", list);
		context.setVariable("m", map);
		
		System.out.println("#c.?[#this <= 2] = " + parser.parseExpression("#c.?[#this <= 2]").getValue(context));
		System.out.println("#m.?[#this.key != 'k1'] = " + parser.parseExpression("#m.?[#this.key != 'k1']").getValue(context));
	}
	
	
	@Test
	/**
	 * 模板表达式
	 * 模板表达式允许文本表达式有一个或多个表达式块组成，每个块由前缀+表达式+后缀组成，由ParserContext实现类来确定表达式中的模板前缀或后缀等
	 * 如${#abc}, "${" 为前缀， "}"为后缀，"#abc"为表达式
	 */
	public void testTemplateEp(){
		ExpressionParser parser = new SpelExpressionParser();
		@SuppressWarnings("rawtypes")
		List list = (List) parser.parseExpression("${{1, 2, 3}}", new ParserContext(){
			
			@Override
			public boolean isTemplate() {
				return true;
			}

			@Override
			public String getExpressionPrefix() {
				return "${";
			}

			@Override
			public String getExpressionSuffix() {
				return "}";
			}
			
		}).getValue();
		
		System.out.println("${{1, 2, 3}} = " + list);
	}
	
	
	
}
