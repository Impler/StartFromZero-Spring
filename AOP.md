# AOP
AOP (Aspect-Oriented Programming) 面向切面编程是OOP (Object-Oriented Programming)之外的另外一种编程思想。OOP编程的核心是类，而AOP编程的核心的切面，关注的是跨越多个类或对象的横切逻辑。  
AOP是Spring框架的重要组成，但IOC容器并不依赖它，可以把它当做一种功能强大的中间件使用。  
AOP在Spring中可以提供声明式的企业级服务用来代替EJB，例如最常见的声明式事务管理；此外还允许用户自定义切面来实现更多的功能。  

## 基本概念
- Join point: 程序执行中特定位置。例如方法执行前、执行后、抛出异常时等。  
- Advice：增强。在特定的连接点植入的额外程序逻辑，一般可以分为前置增强、后置增强、环绕增强等。技术实现上，一般采用拦截器链来实现。  
- Pointcut：切点。满足一定条件的连接点。Advice中包含了切点表达式，作用在满足该表达式的连接点上。Spring默认使用AspectJ 切点表达式。  
- Introduction：引介。为目标对象新增额外的属性或方法。  
- Aspect：切面。切面由Pointcut和Advice增强组成，既包括了横切逻辑的定义也包括了连接点的定义。Spring AOP的工作就是将（切面定义的）横切逻辑织入到（切面定义的）连接点中。  
- Target object：目标对象。被织入增强的普通对象。Spring AOP采用运行时代理，目标对象最终返回的是一个代理对象。  
- AOP proxy：AOP框架创建的目标对象的代理对象。一般基于JDK动态代理或CGLIB动态代理。  
- Weaving：将增强添加到目标对象指定连接点的过程。三种织入方式：编译期织入、类装载时织入、运行时织入。AspectJ采用的是编译期织入。  

增强类型：
- Before advice：前置增强。在目标方法执行前实施增强。  
- After returning advice：后置增强。在目标方法正确执行后实施增强。  
- After throwing advice：异常抛出增强。在目标方法抛出异常时实施增强。  
- After(finally) advice：finally增强。在目标方法执行后，不管是否抛出异常，实施增强。
- Around advice：环绕增强。在方法执行前和后实施增强。  