package com.study.spring.core.ioc.extendion_points.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class FooBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition beanDef = beanFactory.getBeanDefinition("foo");
		beanDef.getPropertyValues().add("name", "Tom");
		System.out.println("-->postProcessBeanFactory modify foo bean definition");
	}

}
