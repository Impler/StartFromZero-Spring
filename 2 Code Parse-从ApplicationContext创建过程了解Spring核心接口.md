# 2 从ApplicationContext创建过程了解Spring核心接口

ApplicationContext 是Spring的核心，其创建的过程就是将xml、properties等方式的配置信息一步步构成Spring得以运转的各个基础设施。而这些基础设施的构建主要在`AbstractApplicationContext`的`refresh()`方法中完成。  
```java
public void refresh() throws BeansException, IllegalStateException {
	synchronized (this.startupShutdownMonitor) {
		// Prepare this context for refreshing.
		// 1. 构造context之前的初始化工作
		prepareRefresh();

		// Tell the subclass to refresh the internal bean factory.
		// 2. 创建BeanFactory实例，并且加载BeanDefinition
		ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

		// Prepare the bean factory for use in this context.
		// 3. 为BeanFactory配置内部工作组件 
		prepareBeanFactory(beanFactory);

		try {
			4. 允许在BeanFactory实例化后修改配置信息
			// Allows post-processing of the bean factory in context subclasses.
			// 4.1 在子类中进行修改操作
			postProcessBeanFactory(beanFactory);
			// 4.2 通过BeanFactoryPostProcessor接口进行修改操作
			// Invoke factory processors registered as beans in the context.
			invokeBeanFactoryPostProcessors(beanFactory);

			// Register bean processors that intercept bean creation.
			// 5. 实例化并注册BeanPostProcessor
			registerBeanPostProcessors(beanFactory);

			// Initialize message source for this context.
			// 6. 实例化MessageSource
			initMessageSource();
			
			// Initialize event multicaster for this context.
			// 7. 实例化ApplicationEventMulticaster
			initApplicationEventMulticaster();

			// Initialize other special beans in specific context subclasses.
			// 8. 在子类中实例化一些特殊的bean
			onRefresh();

			// Check for listener beans and register them.
			// 9. 注册ApplicationListener
			registerListeners();

			// Instantiate all remaining (non-lazy-init) singletons.
			// 10. 实例化所有非lazy-init的单例bean
			finishBeanFactoryInitialization(beanFactory);

			// Last step: publish corresponding event.
			// 11. 发布Event
			finishRefresh();
		}

		catch (BeansException ex) {
			if (logger.isWarnEnabled()) {
				logger.warn("Exception encountered during context initialization - " +
						"cancelling refresh attempt: " + ex);
			}

			// Destroy already created singletons to avoid dangling resources.
			destroyBeans();

			// Reset 'active' flag.
			cancelRefresh(ex);

			// Propagate exception to caller.
			throw ex;
		}

		finally {
			// Reset common introspection caches in Spring's core, since we
			// might not ever need metadata for singleton beans anymore...
			resetCommonCaches();
		}
	}
}
```

## 2.1 构造context之前的初始化工作
## 2.2 创建BeanFactory实例，并且加载BeanDefinition
## 3. 为BeanFactory配置内部工作组件 
## 4. 允许在BeanFactory实例化后修改配置信息
### 4.1 在子类中进行修改操作
### 4.2 通过BeanFactoryPostProcessor接口进行修改操作
## 5. 实例化并注册BeanPostProcessor
## 6. 实例化MessageSource
## 7. 实例化ApplicationEventMulticaster
## 8. 在子类中实例化一些特殊的bean
## 9. 注册ApplicationListener
## 10. 实例化所有非lazy-init的单例bean
## 11. 发布Event