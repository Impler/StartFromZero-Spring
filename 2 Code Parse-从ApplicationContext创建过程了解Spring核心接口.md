# 2 从ApplicationContext创建过程了解Spring核心接口

ApplicationContext 是Spring的核心，其创建的过程就是将xml、properties等方式的配置信息一步步构成Spring得以运转的各个基础设施。而这些基础设施的构建主要在`AbstractApplicationContext`的`refresh()`方法中完成。  
```java
// org.springframework.context.support.AbstractApplicationContext
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
```java
// org.springframework.context.support.AbstractApplicationContext
protected void prepareRefresh() {
	this.startupDate = System.currentTimeMillis();
	this.closed.set(false);
	this.active.set(true);
	initPropertySources();
	getEnvironment().validateRequiredProperties();
	this.earlyApplicationEvents = new LinkedHashSet<ApplicationEvent>();
}
```
初始化工作比较简单，包括设置一些标志位、处理下properties文件等。  

## 2.2 创建BeanFactory实例，并且加载BeanDefinition
```java
// org.springframework.context.support.AbstractApplicationContext
protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
	// f1 创建BeanFactory实例，并且加载BeanDefinition
	refreshBeanFactory();
	ConfigurableListableBeanFactory beanFactory = getBeanFactory();
	return beanFactory;
}
// f1
// org.springframework.context.support.AbstractRefreshableApplicationContext
protected final void refreshBeanFactory() throws BeansException {
	// 如果当前已经有BeanFactory存在，销毁它、关闭它
	if (hasBeanFactory()) {
		destroyBeans();
		closeBeanFactory();
	}
	try {
		// 无论如何，重新new一个BeanFactory实例，默认new DefaultListableBeanFactory()
		DefaultListableBeanFactory beanFactory = createBeanFactory();
		beanFactory.setSerializationId(getId());
		customizeBeanFactory(beanFactory);
		// f2 加载BeanDefinition并保存在BeanFactory中
		loadBeanDefinitions(beanFactory);
		synchronized (this.beanFactoryMonitor) {
			this.beanFactory = beanFactory;
		}
	}
	catch (IOException ex) {
		...
	}
}

// f2
// org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader
protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
	if (delegate.isDefaultNamespace(root)) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node node = nl.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				// 是否为默认命名空间beans下的元素
				// e.g. <bean/>、<import/>、<alias/>等
				if (delegate.isDefaultNamespace(ele)) {
					parseDefaultElement(ele, delegate);
				}
				else {
					// f3其他命名空间下的bean的处理
					// e.g <context:component-scan />、<cache:annotation-driven/>等
					delegate.parseCustomElement(ele);
				}
			}
		}
	}
	else {
		delegate.parseCustomElement(root);
	}
}

// f3
// 
```

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