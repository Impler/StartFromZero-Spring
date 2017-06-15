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
初始化工作比较简单，包括设置一些标志位、处理下properties文件等。  
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

// f3 此处以<context:component-scan />
public BeanDefinition parse(Element element, ParserContext parserContext) {
	String basePackage = element.getAttribute(BASE_PACKAGE_ATTRIBUTE);
	basePackage = parserContext.getReaderContext().getEnvironment().resolvePlaceholders(basePackage);
	String[] basePackages = StringUtils.tokenizeToStringArray(basePackage,
			ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);

	// Actually scan for bean definitions and register them.
	ClassPathBeanDefinitionScanner scanner = configureScanner(parserContext, element);
	// 在类路径下扫描所有的Bean，并注册下来
	Set<BeanDefinitionHolder> beanDefinitions = scanner.doScan(basePackages);
	registerComponents(parserContext.getReaderContext(), beanDefinitions, element);

	return null;
}
```

## 3. 为BeanFactory配置内部工作组件 
```java
protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
	// Tell the internal bean factory to use the context's class loader etc.
	beanFactory.setBeanClassLoader(getClassLoader());
	beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader()));
	beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));

	// Configure the bean factory with context callbacks.
	beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
	beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
	beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
	beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
	beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);
	beanFactory.ignoreDependencyInterface(EnvironmentAware.class);

	// BeanFactory interface not registered as resolvable type in a plain factory.
	// MessageSource registered (and found for autowiring) as a bean.
	beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
	beanFactory.registerResolvableDependency(ResourceLoader.class, this);
	beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
	beanFactory.registerResolvableDependency(ApplicationContext.class, this);

	// Detect a LoadTimeWeaver and prepare for weaving, if found.
	if (beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
		beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
		// Set a temporary ClassLoader for type matching.
		beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
	}

	// Register default environment beans.
	if (!beanFactory.containsLocalBean(ENVIRONMENT_BEAN_NAME)) {
		beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
	}
	if (!beanFactory.containsLocalBean(SYSTEM_PROPERTIES_BEAN_NAME)) {
		beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
	}
	if (!beanFactory.containsLocalBean(SYSTEM_ENVIRONMENT_BEAN_NAME)) {
		beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
	}
}
```
## 4. 允许在BeanFactory实例化后修改配置信息
Spring为开发者开放了修改容器配置的接口，允许AbstractApplicationContext子类或自定义的BeanFactoryPostProcessor们修改容器的一些配置信息。  
此刻仅仅完成了BeanFactory的创建和BeanDefinition的注册工作，Bean的实例化还没有进行。因此，此处对BeanDefinition的修改，将直接作用于后续的Bean实例化工作。  
### 4.1 在子类中进行修改操作
```java
// org.springframework.context.support.AbstractApplicationContext
protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
	// 默认没有任何实现
}
```
### 4.2 通过BeanFactoryPostProcessor接口进行修改操作
```java
protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
	// 调用所有的BeanFactoryPostProcessor
	PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors());

	// Detect a LoadTimeWeaver and prepare for weaving, if found in the meantime
	// (e.g. through an @Bean method registered by ConfigurationClassPostProcessor)
	if (beanFactory.getTempClassLoader() == null && beanFactory.containsBean(LOAD_TIME_WEAVER_BEAN_NAME)) {
		beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
		beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
	}
}
```
## 5. 实例化并注册BeanPostProcessor
按照PriorityOrdered、Ordered和普通的实现顺序依次实例化并注册BeanPostProcessor。  
```java
// org.springframework.context.support.AbstractApplicationContext
protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
	PostProcessorRegistrationDelegate.registerBeanPostProcessors(beanFactory, this);
}
```
## 6. 实例化MessageSource
实例化为国际化和参数化服务的资源组件。  
```java
// org.springframework.context.support.AbstractApplicationContext
protected void initMessageSource() {
	// 省略
	... 
}
```
## 7. 实例化ApplicationEventMulticaster
实例化ApplicationEventMulticaster。  
```java
// org.springframework.context.support.AbstractApplicationContext
protected void initApplicationEventMulticaster() {
	// 省略
	...
}
```
## 8. 在子类中实例化一些特殊的bean
为子类提供模板方法，实现一些特定的逻辑。  
```java
// org.springframework.context.support.AbstractApplicationContext
protected void onRefresh() throws BeansException {
	// 默认没有任何实现
}
```
## 9. 注册ApplicationListener
注册ApplicationListener，并发布需要及早调用的事件。  
```java
// org.springframework.context.support.AbstractApplicationContext
protected void registerListeners() {
	// 省略
	...
}
```
## 10. 实例化所有非lazy-init的单例bean
实例化BeanDefinition中，所有非lazy-init的单例bean。  
```java
// org.springframework.context.support.AbstractApplicationContext
protected void finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory) {
	// Initialize conversion service for this context.
	// 实例化ConversionService Bean
	if (beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) &&
			beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class)) {
		beanFactory.setConversionService(
				beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
	}

	// Register a default embedded value resolver if no bean post-processor
	// (such as a PropertyPlaceholderConfigurer bean) registered any before:
	// at this point, primarily for resolution in annotation attribute values.
	// 如果不存在，实例化并注册StringValueResolver
	if (!beanFactory.hasEmbeddedValueResolver()) {
		beanFactory.addEmbeddedValueResolver(new StringValueResolver() {
			@Override
			public String resolveStringValue(String strVal) {
				return getEnvironment().resolvePlaceholders(strVal);
			}
		});
	}

	// Initialize LoadTimeWeaverAware beans early to allow for registering their transformers early.
	// 实例化LoadTimeWeaverAware Bean
	String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
	for (String weaverAwareName : weaverAwareNames) {
		getBean(weaverAwareName);
	}

	// Stop using the temporary ClassLoader for type matching.
	beanFactory.setTempClassLoader(null);

	// Allow for caching all bean definition metadata, not expecting further changes.
	beanFactory.freezeConfiguration();

	// Instantiate all remaining (non-lazy-init) singletons.
	// 一般非lazy-init Bean的实例化
	beanFactory.preInstantiateSingletons();
}

```
## 11. 发布Event
```java
protected void finishRefresh() {
	// Initialize lifecycle processor for this context.
	initLifecycleProcessor();

	// Propagate refresh to lifecycle processor first.
	getLifecycleProcessor().onRefresh();

	// Publish the final event.
	publishEvent(new ContextRefreshedEvent(this));

	// Participate in LiveBeansView MBean, if active.
	LiveBeansView.registerApplicationContext(this);
}
```