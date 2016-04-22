# StartFromZero-Spring
##简介
Spring支持声明式事务处理，通过RMI或者Web Service远程访问，并且为数据的持久化提供诸多选项。还提供了功能齐全的MVC框架，可以透明化的在应用中继承AOP
##依赖注入(Dependency Injection)和控制反转（Inversion of Control）
一个对象所有依赖对象的创建交由容器管理。容器负责将依赖通过构造方法、属性或工厂方法传递到目标对象中。  
org.springframework.beans和org.springframework.context是Spring IOC容器的核心实现。其中包含最重要的两个接口*BeanFactory*和*ApplicationContext*。
*BeanFactory*接口提供基础功能性功能配置  
*ApplicationContext*接口是*BeanFactory*子接口，能够很好的和Spring AOP集成在一起。负责实例化、配置合装配Bean.
###依赖注入的三种方式
1. 构造方法注入  
	
2. setter方法注入  
	
3. 工厂方法注入  
	 
###功能及配置详解
