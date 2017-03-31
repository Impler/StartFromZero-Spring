package com.study.spring._05aop._01declaring_type._03spring_api._01advice._05introduction;

import org.junit.Test;

import com.study.spring._05aop._01declaring_type._03spring_api._01advice.WaiterA;
import com.study.spring.aop.Waiter;
import com.study.spring.test.BaseTestObject;

public class IntroductionAdviceTest extends BaseTestObject{
	
	@Override
	protected String getConfigFileName() {
		return "config/02aop/02declaring_type/03spring-api/01advice/05introduction-advice.xml";
	}

	@Test
	public void test() {
		// 此处必须使用目标类的类型
		// 不能使用目标类实现的接口类型
		WaiterA waiter = (WaiterA) super.getBean("introWaiter");
		System.out.println(waiter instanceof Waiter);
		waiter.serveTo("Tom");
		// 调用引介方法
		((IDoOthers)waiter).settleAccounts();
		
		// 从打印出的结果可以看出，代理类对象实现的接口中并没有目标类实现的Waiter接口
		Class<?>[] clazz = waiter.getClass().getInterfaces();
		for(Class<?> cla : clazz){
			System.out.println(cla);
		}
	}

}
