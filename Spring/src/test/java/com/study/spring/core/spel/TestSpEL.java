package com.study.spring.core.spel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
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
	 * 文本表达式
	 * 文本表达式支持字符串，日期，数字（整型，实数，十六进制等），布尔型和null，字符串用单引号包裹
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
		int numVal = (int) numExp.getValue();
		boolean boolVal = (boolean) boolExp.getValue();
		Object nullVal = nullExp.getValue();
		System.out.println("strVal: " + strVal);
		System.out.println("numVal: " + numVal);
		System.out.println("boolVal: " + boolVal);
		System.out.println("nullVal: " + nullVal);
	}
	
	@Test
	public void test(){
		Student stu = new Student(1, "张三", 'M');
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name");
		EvaluationContext context = new StandardEvaluationContext(stu);
		String name = (String) exp.getValue(context);
		System.out.println(name);
	}
	
}
