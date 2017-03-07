package com.study.spring._01core._04extension_points.bean_post_processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;


public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor, Ordered {

	/**
	 * 在容器初始化bean之前执行
	 * 即在 InitializingBean->afterPropertiesSet方法、init-method配置、default-init-method配置、@PostConstruct之前执行
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("-->postProcessBeforeInitialization: " + beanName + ", bean:" + bean);
		return bean;
	}

	/**
	 * 在容器初始化bean后执行
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("-->postProcessAfterInitialization: " + beanName + ", bean:" + bean);
		return bean;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
