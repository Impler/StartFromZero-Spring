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
Spring支持自动注入依赖。可以有效减少配置的复杂度，同时也可以减少项目后续更新迭代时改动。  
`<bean />`节点的`autowire`属性用来配置自动装配，默认值为`no`，即不使用自动装配，依赖必须手动指定。  
自动装配可分为：  
- no: 不使用自动装配
- default: 采用父级配置，即使用`<beans />`的`default-autowire`属性。
- byName: 按参数名称自动装配，即Spring会自动将与属性名相同的Bean自动注入进来。
- byType: 按参数类型自动装配，即Spring会自动将与属性类型相同的Bean自动注入进来。
- constructor: 构造器自动注入，类似于byType，即Spring会自动将于构造函数参数类型相同的Bean自动注入进来。
构造器自动注入方式有异于其他两种自动注入方式的地方是，如果匹配的Bean不存在，构造器自动注入方式将会抛出异常，而其他两者不会。  
对于按类型自动注入方式，包括构造器自动注入方式，如果存在多个依赖类型的Bean，Spring会将他们全部注入进来，但前提是，该属性应为集合类型或key为String类型的Map类型，否则也将抛出异常。  
值得注意的是，基本数据类型、String类型和Class类型是不支持自动注入的。显示使用`<property />`或`<constructor />`的配置会覆盖自动注入的行为。  
如果一个Bean不希望被其他Bean自动注入，可以配置`autowire-candidate="false"`。  

#### 2.3.4 方法注入
Spring容器中的大部分都是单例的。单例Bean依赖单例Bean、非单例Bean依赖非单例Bean，作用域相同的Bean之间的依赖关系，可以通过将一个Bean作为另一个Bean的成员属性来解决。但是当两个Bean的生命周期不一致时，例如：单例Bean A 需要在每次调用时，使用非单例Bean B，上面的方法就行不通了。  
一个可行的办法是放弃依赖注入，在单例Bean上实现ApplicationContextAware 接口，每次调用时，直接让容器创建一个非单例Bean出来。  
```java
public class SingletonBeanOfAppAware implements ApplicationContextAware {
	private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	public void call(){
		// return a prototype bean at every invoke call() method
		PrototypeBean pro = this.applicationContext.getBean(PrototypeBean.class);
		// do other business...
	}
}
```
上述虽能解决问题，但背弃了依赖注入的原则，使代码与Spring耦合更深。Spring提供了一种更高级的方式，解决此类问题，叫做方法注入。  
方法注入是Spring提供的重写Bean的方法，返回另一个Bean的能力。Spring通过CGLIB产生单例Bean子类的字节码，然后重写单例Bean的方法。因此，该单例Bean应为非final修饰的，返回单例Bean的方法也应为非final修饰的，其一般格式为：  
`<public|protected> [abstract] <返回类型> 方法名(无参);`。  

#### 2.3.5 Bean的作用域
Spring支持的生命周期类型为：  
- singleton: 同一个ApplicationContext共享一个单实例Bean。
- prototype: 每次调用返回一个新的实例Bean。
- request: 每次Http请求创建一个新的实例。
- session: 同一个HttpSession，共享一个Bean。
- global session: 同一个全局Session共享一个Bean。
- application: 同一个ServletContext共享一个Bean。
request、session、global session、application作用域均需要在Web Application容器中使用。  

### 2.4 Bean的本质

#### 2.4.1 生命周期回调方法
Bean的生命周期是以对象的创建开始，以对象的销毁结束，分别对应`InitializingBean`和`DisposableBean`接口。Spring支持在Bean的生命周期过程中通过生命周期回调方法加入自定义的交互行为。  

##### 2.4.1.1 初始化回调方法
`InitializingBean`接口允许在Spring容器实例化Bean并将所有必要的属性设置完毕后执行特定的初始化操作。  
```java
public interface InitializingBean {
	void afterPropertiesSet() throws Exception;

}
```
但这种方式需要代码与Spring高度耦合，不推荐使用。  
在`<bean />`节点上使用`init-method`属性也可以配置初始化回调方法:  
```xml
<bean id="xxx" class="xxx"
    init-method="initMethod">
</bean>
```
此外，Spring支持JSR-250标准的初始化回调注解`@PostConstruct`也能达到同样的效果:  
```xml
<!-- 启用注解扫描 ，扫描@PostConstruct注解-->
<context:annotation-config/>
```
```java
@PostConstruct
public void postConstruct(){
    System.out.println("call @PostConstruct");
}
```
综上三种方式，Bean初始化过程的执行顺序为：构造方法-->Setter方法-->@PostConstruct方法-->InitializingBean接口方法-->init-method方法。  

##### 2.4.1.2 销毁回调方法
`DisposableBean`接口允许Bean在销毁前执行自定义操作。  
```java
public interface DisposableBean {
	void destroy() throws Exception;
}
```
同样的，这种方式因为耦合度高，不推荐使用。  
在`<bean />`节点上使用`destroy-method`属性也可以配置销毁回调方法:  
```xml
<bean id="xxx" class="xxx"
    destroy-method="destroyMethod">
</bean>
```
此外，Spring支持JSR-250标准的销毁回调注解`@PreDestroy`也能达到同样的效果:  
```xml
<!-- 启用注解扫描 ，扫描@PreDestroy注解-->
<context:annotation-config/>
```
```java
@PreDestroy
public void preDestroy(){
    System.out.println("call @PreDestroy");
}
```
综上三种方式，Bean销毁执行顺序为：@PreDestroy-->DisposableBean接口方法-->destryo-method方法。  