# IOC容器
## 1 简介
控制反转（Inversion of Control (IoC)）又称依赖注入（dependency injection (DI)）。即一个对象及其所有依赖对象的创建交由容器管理。容器负责将依赖通过构造方法、属性或工厂方法传递到目标对象中。  
org.springframework.beans和org.springframework.context是Spring IOC容器的核心实现。其中包含最重要的两个接口*BeanFactory*和*ApplicationContext*。  
*BeanFactory*接口提供基础的配置和管理对象功能  
*ApplicationContext*接口是*BeanFactory*子接口，除具有*BeanFactory*的功能外，还能够结合Spring AOP、提供国际化支持、事件发布等。  
Spring Bean是一个POJO，只不过其实例化、装配和管理过程交由Spring IOC容器来处理。Spring Bean可以通过XML、注解或Java代码的形式配置。  

*ApplicationContext*接口就是Spring IOC容器，其通过读取Bean的配置信息，然后实例化和装配他们。可以通过XML、注解、代码的形式配置Bean。

## 2 Bean初探
Bean的任何一种配置方式，最终都会被Spring解析成`BeanDefinition`对象。`BeanDefinition`对象一般包含下面的基本信息：  
- 全限定类名：Bean的实际实现类
- Bean的行为属性，包括生命周期以及生命周期的回调方法等
- Bean的外部依赖
- Bean的基本属性

```xml
<bean id="beanId" name="beanAlias1 beanAlias1 beanAlias3" 
	class="cn.com.xxx.xxx" 
	init-method="xxx"
	destroy-method="xxx">
	<property name="xxx" value=""></property>
	<property name="xxx" ref="otherBean"></property>
</bean>
```

一般情况下，Spring Bean的注册和创建均是在ApplicationContext内部完成。当然Spring也支持注册已存在的在外部创建的对象。通过BeanFactory的registerSingleton(..)或registerBeanDefinition(..)完成。  

### 2.1 Bean的命名
每个Bean都可以拥有1或多个标识符，但这些标识符在当前容器中必须是唯一的。在xml配置中，bean元素的id和name属性用来定义bean的标识符。一般情况下，id属性指定唯一的一个值，name属性可以指定多个值，之间用，；或空格间隔。此外，也可以在bean元素的外部使用alias元素为bean定义别名，形如：  
```xml
<alias name="dataSource" alias="ModuleA-dataSource"/>
<alias name="dataSource" alias="ModuleA-dataSource" />
```
意为一个名为dataSource的bean取了两个别名ModuleA-dataSource、ModuleA-dataSource。这样在模块A和B中可以通过不同的别名引用依赖同一个Bean dataSource。  

### 2.2 Bean的实例化
Bean Definition就像是用来创建对象的模板，Spring容器通过该模板创建一个个具体的实例对象。  
在xml配置中，bean元素的class属性通常表示待实例化的全限定类名，其对应BeanDefinition中的Class属性，Spring通过反射机制，创建该类型的实例。另外，class属性也可以表示包含静态工厂方法的静态工厂类的全限定类名，Spring通过调用该工厂类的指定工厂方法创建对象实例，该实例可以是任意类型。  
注意，一个静态内部类的xml配置，bean元素的class属性应为外部类的全限定类名+$+内部类名的形式，如com.example.Foo$Bar。即为该静态内部类编译后的class文件的文件名。  
综上，可以通过三种方式实例化Bean:  
- 通过构造方法实例化，相当于new一个对象
- 通过静态工厂方法实例化
- 通过普通工厂Bean的工厂方法实例化

#### 2.2.1 通过构造方法实例化
```xml
<!-- 构造方法实例化，相当于new一个对象 -->
<!-- 调用默认构造方法-->
<bean id="xxx" class="examples.ExampleBean"/>
<!-- 调用带参构造方法 -->
<bean id="xxx" class="examples.ExampleBean">
	   <constructor-arg name="xxx" value="xxx" />
</bean>

<!-- 实例化一个静态内部类，使用$连接外部类名 -->
<bean id="xxx" class="examples.ExampleBean$StaticBean"/>
```
#### 2.2.2 通过静态工厂方法实例化
```xml
<bean id="xxx" class="examples.ExampleBeanStaticFactory" factory-method="createFoo" />
```
#### 2.2.3 通过普通静态方法实例化
```xml
<bean id="factory" class="examples.ExampleBeanFactory" />
<bean id="xxx" factory-bean="factory" factory-method="createFoo" />
```

## 依赖注入的三种方式
1. 构造方法注入  
	
2. setter方法注入  
	
3. 工厂方法注入  
