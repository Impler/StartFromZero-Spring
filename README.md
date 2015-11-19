# StartFromZero-Spring
##简介
Spring支持声明式事务处理，通过RMI或者Web Service远程访问，并且为数据的持久化提供诸多选项。还提供了功能齐全的MVC框架，可以透明化的在应用中继承AOP
##依赖注入(Dependency Injection)和控制反转（Inversion of Control）
Dependency injection (DI) is a process whereby objects define their dependencies, that is, the other
objects they work with, only through constructor arguments, arguments to a factory method, or properties
that are set on the object instance after it is constructed or returned from a factory method. The container
then injects those dependencies when it creates the bean. This process is fundamentally the inverse,
hence the name Inversion of Control (IoC), of the bean itself controlling the instantiation or location of
its dependencies on its own by using direct construction of classes, or the Service Locator pattern.
Code is cleaner with the DI principle and decoupling is more effective when objects are provided with
their dependencies. The object does not look up its dependencies, and does not know the location
or class of the dependencies. As such, your classes become easier to test, in particular when the
dependencies are on interfaces or abstract base classes, which allow for stub or mock implementations
to be used in unit tests.  
###依赖注入的三种方式
1. 构造方法注入  
	
2. setter方法注入  
	
3. 工厂方法注入  
	 