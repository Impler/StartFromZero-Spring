package com.study.spring.bean.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
/**
 * ApplicationContect会为每个bean创建一个BeanDefinition对象，当所有BeanDefinition对象都创建完后，会调用BeanFactoryPostProcessor的处理方法
 * BeanFactoryPostProcessor操作的是bean的配置信息，在bean实例化之前而非bean实例
 * @author Impler
 * @date 2016年4月23日
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDef = beanFactory.getBeanDefinition("simpleUser");
		//覆盖名为name的property值
		beanDef.getPropertyValues().addPropertyValue("name", "kitty");
		
		//尽管BeanFactoryPostProcessor从技术上可以操作bean实例，但是违反了bean标准的声明周期，所以不建议这样使用
		//beanFactory.getBean("simpleUser");
	}

}
