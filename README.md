#简介
Spring是构建企业级应用的一个轻量级框架。Spring由多个模块组成，但我们可以轻易的挑选应用所需的模块，而不用引用不需要的东西。Spring提供了IOC容器，声明式事务处理，通过RMI或者Web Service远程访问，并支持多种持久化技术，如Hibernate，JPA，Mybatis等。还提供了功能齐全的Web层MVC框架，以及AOP等。  
#依赖注入(Dependency Injection)和控制反转（Inversion of Control）
一个对象所有依赖对象的创建交由容器管理。容器负责将依赖通过构造方法、属性或工厂方法传递到目标对象中。  
org.springframework.beans和org.springframework.context是Spring IOC容器的核心实现。其中包含最重要的两个接口*BeanFactory*和*ApplicationContext*。
*BeanFactory*接口提供基础的配置和功能  
*ApplicationContext*接口是*BeanFactory*子接口，能够很好的和Spring AOP集成在一起。负责实例化、配置合装配Bean.
其中Spring Bean是一个POJO，只不过其实例化、装配和管理过程交由Spring来处理。Spring Bean可以通过XML、注解或Java代码的形式配置。  
##依赖注入的三种方式
1. 构造方法注入  
	
2. setter方法注入  
	
3. 工厂方法注入  
