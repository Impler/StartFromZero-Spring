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

### 2.3 依赖注入
依赖注入（DI）是配置对象依赖的过程。当一个对象通过构造器或工厂方法创建时，他所依赖的对象可以通过构造器传参、setter方法或工厂方法传参的方式从外部配置进来。由于对象失去了对自身依赖的控制，转而交由容器来管理，因此又称为控制反转（Inversion  Of Control）。基于依赖注入原则的代码更加简洁，代码之间耦合性更低。对象只需要声明依赖而不需要主动寻找依赖甚至不知道依赖的对象到底是谁。  
依赖注入的两种方式：  
- 构造方法注入
- Setter方法注入

#### 2.3.1 构造方法注入
通过调用指定的有参构造函数初始化Bean。  
`<constructor-arg />`标签用于通过构造函数进行依赖注入。其包含以下几个属性:  
- index: 参数位置索引
- type: 参数全限定类名
- name: 参数名称，只有在debug编译模式或构造函数使用@ConstructorProperties注解标识的情况下，Spring才能通过反射获取到参数名称
- ref: 注入复杂数据类型的Bean
- value: 注入基本类型值
其中，index、type、name都可以用来指定构造函数参数位置，可以单独使用，前提是保证参数之间无歧义。例如，两个String类型的参数，如果只使用type，稍不留意就有可能造成错误注入的情况。一般来说，使用index可以唯一无误的指定参数。  

```xml
<bean id="user" class="com.study.spring.core.ioc.di.domain.User">
    <constructor-arg type="java.lang.String" value="TOM" />
    <constructor-arg type="int" value="23" />
    <constructor-arg type="char" value="M" />
    <constructor-arg type="java.lang.String" value="China" />
</bean>
```

#### 2.3.2 Setter方法注入
在实例化Bean后，调用其对应的Setter方法进行初始化。  
`<property />`用于实现Setter方法注入。其包含以下几个属性:  
- name: 属性名称，按照Setter格式规范命名
- ref: 注入自定义类型的Bean
- name: 注入基本类型值

```xml
<bean id="fooSetter" class="com.study.spring.core.ioc.di.domain.Foo">
    <property name="bar" ref="bar" />
    <property name="baz" ref="baz" />
</bean>
```
#### 2.3.3 依赖注入配置详解
##### 2.3.3.1 value属性
`value`属性指定字符串形式的基本数据类型字面值。  
```xml
<property name="name" value="jack" />
```
##### 2.3.3.1 `<ref />`或ref属性
`<ref />`或`ref`属性指定复杂数据类型的Bean。  
```xml
<!-- ref属性 -->
<property name="bar" ref="bar">
<!-- ref标签 -->
<property name="baz">
    <!-- bean:引用当前容器内的Bean，parent:应用父容器内的Bean -->
    <ref bean="xxx" />
</property>
```
##### 2.3.3.2 `<idref />`
`<idref />`用于引用其他Bean的id属性字面值，而非Bean本身。   
```xml
<property name="name">
	<idref bean="xxx"/>
</property>

<!-- 相当于 -->
<property name="name" value="xxx" />
```
使用`<idref />`的好处是Spring会验证该id是否存在，如果不存在将会抛出异常。
##### 2.3.3.3 内部Bean
在一个bean的`<property/>`或`<constructor-arg/>`元素内定义的bean（<bean/>）称为内部Bean。 Spring忽略内部Bean的 id、name以及scope属性，内部Bean跟随外部Bean创建而创建。  
```xml
<bean id="xxx" class="xxx">
	<property name="target">
		<!-- 内部Bean -->
		<bean class="xxx">
			<property name="xxx" value="xxx"/>
		</bean>
	</property>
</bean>
```
##### 2.3.3.4 `<null />`和空字符串
如果向一个对象的属性中注入null值，使用`<null />`标签；`value=""`注入的是空字符串。  
```xml
<bean id="xxx" class="xxx">
	<property name="target">
		<null />
	</property>
</bean>
```
##### 2.3.3.5 集合
spring支持List，Set，Map，Properties等集合属性的注入。  
```xml
<bean id="xx" class="xxx">
	<!-- 注入properties对象 -->
	<property name="properties">
	    <props>
	    	<!-- key-value均为字面值 -->
	        <prop key="xx">xxx</prop>
	        <prop key="xx">xxx</prop>
	    </props>
	</property>
	<!-- 注入 list对象-->
	<property name="list">
	    <list>
	    	<!-- 字面值 -->
	        <value>xxx</value>
	        <!-- 引用其他bean -->
	        <ref bean="xxx" />
	    </list>
	</property>
	<!-- 注入map对象 -->
	<property name="map">
	    <map>
	    	<!-- 字面值 -->
	        <entry key="xxx" value="xxx"/>
	        <!-- 可以引用其他bean -->
	        <entry key ="xxx" value-ref="xxx"/>
	    </map>
	</property>
	<!-- 注入set对象 -->
	<property name="set">
	    <set>
	    	<!-- 字面值 -->
	        <value>xxx</value>
	        <!-- 引用其他bean -->
	        <ref bean="xxx" />
	    </set>
	</property>
</bean>
```
map中的key/value，以及set的value属性值可以使用下列元素：  
bean | ref | idref | list | set | map | props | value | null  
以上这些集合元素还有一个`merge`属性，用于子bean中的集合元素与父bean中的集合元素合并。  
对于map、props、set等无序集合来说，子bean会覆盖父bean集合中相同的元素，而list是无序的，因此不会覆盖。  

##### 2.3.3.6 使用命名空间p和c，简化配置
命名空间`p`用于简化Setter方式注入的配置。  
在beans节点中添加命名空间 xmlns:p="http://www.springframework.org/schema/p"，使用命名空间p后，在定义bean的时候，可以使用`p:属性=xxx`或`p:属性-ref=xxx`的行级属性形式代替`<property />`。  
```xml
<bean id="xxx" class="xxx" 
	p:name="xxx"
	p:spouse-ref="xxx"/>
<!-- 等同于 -->
<bean name="xxx" class="xxx">
    <property name="name" value="xxx"/>
    <property name="spouse" ref="xxx"/>
</bean>
```
命名空间`c`用于简化构造方法注入的配置。  
在beans节点中添加命名空间pxmlns:c="http://www.springframework.org/schema/c"，在使用构造方法注入的bean中，使用`c:属性=xxx`或`c:属性-ref=xxx`的行级属性形式代替`<constructor-arg />`。  
```xml
<bean id="xxx" class="xxx" 
	c:bar-ref="bar" 
	c:baz-ref="baz" 
	c:email="xxx"/>
<!-- 等同于 -->
<bean id="xxx" class="xxx">
    <constructor-arg ref="bar"/>
    <constructor-arg ref="baz"/>
    <constructor-arg value="xxx"/>
</bean>
```
##### 2.3.3.7 复合属性注入
Spring支持类似于OGNL形式的属性注入，例如为A类的B属性的C属性的D属性赋值。前提是对象图上的每个属性都不为空，否则将会抛异常。  
```xml
<bean id="foo" class="xxx.A">
    <property name="B.C.D" value="123" />
</bean>
```
##### 2.3.3.8 延迟实例化
默认情况下，Spring容器创建后会依次实例化单例Bean。使用延迟实例化配置，可以在Bean被调用时才实例化。  
在单个bean上使用`lazy-init="true"`配置延迟实例化。也可以在`<beans />`节点上使用`default-lazy-init="true"`统一配置，这样其子`<bean />`节点全部使用延迟策略。当然也可以在单个`<bean />`上覆盖`<beans />`节点上的默认配置。  
```xml
<!-- 单个Bean配置延迟实例化 -->
<bean id="lazy" class="com.foo.ExpensiveToCreateBean" lazy-init="true"/>
<!-- 批量配置延迟实例化 -->
<beans default-lazy-init="true">
    <!-- 子Bean将全部采用延迟实例化 -->
    <bean />
    <bean />
</beans>
```
##### 2.3.3.9 depend-on
尽管通过`ref`引入的依赖会先于当前Bean的实例化，但是不够明显。可以通过`depends-on`属性显式指定某些Bean先于当前Bean的实例化，多个之间逗号间隔。此外，当一个Bean被销毁时，其`depends-on`的Bean也会先于当前Bean销毁。  
```xml
<bean id="beanOne" class="ExampleBean" depends-on="manager1,manager2"/>
<bean id="manager1" class="ManagerBean" />
```

##### 2.3.3.10 自动装配
