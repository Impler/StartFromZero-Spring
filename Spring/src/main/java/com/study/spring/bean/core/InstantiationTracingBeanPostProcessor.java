package com.study.spring.bean.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * BeanPostProcessor操作bean实例，即在Spring容器实例化bean后，BeanPostProcessor才开始工作。
 * BeanPostProcessor的作用范围只在当前容器内有效
 * 可以配置多个BeanPostProcessor实现，执行的顺序呢依靠Ordered接口的实现
 * @author Impler
 * @date 2016年4月23日
 */
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor, Ordered {

	/**
	 * 在容器初始化bean之前执行
	 * 即在 InitializingBean->afterPropertiesSet方法、init-method配置、default-init-method配置、@PostConstruct之前执行
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessBeforeInitialization: " + beanName);
		return bean;
	}

	/**
	 * 在容器初始化bean后执行
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("postProcessAfterInitialization: " + beanName);
		return bean;
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

}
