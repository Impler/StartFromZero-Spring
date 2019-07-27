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

##### 2.3.3.11 Bean配置的继承性
一个Bean的配置可以继承另一个Bean，包括 class, scope, constructor, property，工厂方法等属性，子bean可以重写继承来的属性。但 depend-on, autowire mode, 依赖检查, singleton, lazy-init将不参与继承。abstract bean只作为模板，不会被容器实例化。  


#### 2.3.4 Lookup方法注入
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
上述虽能解决问题，但背弃了依赖注入的原则，使代码与Spring耦合更深。Spring提供了一种更高级的方式，解决此类问题，叫做Lookup方法注入。  
方法注入是Spring提供的重写Bean的方法，返回另一个Bean的能力。Spring通过CGLIB产生单例Bean子类的字节码，然后重写单例Bean的方法。因此，该单例Bean应为非final修饰的，返回单例Bean的方法也应为非final修饰的，其一般格式为：  
`<public|protected> [abstract] <返回类型> 方法名(无参);`  

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

#### 2.4.2 Aware接口
Aware意为知道的，有某方面知识的。Spring提供了大量的Aware接口，能够让其实现者很容易获得对应的内置组件对象。例如`ApplicationContextAware`、`BeanFactoryAware`等。  
`ApplicationContextAware`接口如下，:  
```java
public interface ApplicationContextAware extends Aware {
	void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
```

### 2.5 Bean的扩展
Spring IOC容器提供了特殊的集成接口便于开发者添加自定义的插件。例如`BeanPostProcessor`、`BeanFactoryPostProcessor`、`FactoryBean`。  

#### 2.5.1 BeanPostProcessor接口
BeanPostProcessor接口提供了两个回调方法，分别在Spring容器初始化Bean之前和之后被调用。这样我们就可以通过实现该接口来添加自定义的处理逻辑。  
```java
public interface BeanPostProcessor {
	// 初始化前置回调
	Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;
	// 初始化后置回调
	Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
```
BeanPostProcessor的作用域仅在当前Spring容器中起效。BeanPostProcessor Bean是区别于其他普通的Spring Bean的，其配置方式不需要指定id、name等属性，Spring容器会自动扫描所有的BeanPostProcessors。由于其在其他普通Bean实例化前就开始工作，所以BeanPostProcessors 需要在Spring容器启动后就会立刻实例化。多个BeanPostProcessor之间的执行顺序依照各自的order属性或实现Order接口方法。  
结合上述生命周期的初始化方法，postProcessBeforeInitialization()先于@PostConstruct，postProcessAfterInitialization后于init-method。 

#### 2.5.2 BeanFactoryPostProcessor接口 
BeanFactoryPostProcessor接口允许在Spring容器初始化所有Bean之前修改bean的配置信息。在语义上与BeanPostProcessor类似，区别在于BeanPostProcessor作用于单个bean的初始化过程，而BeanFactoryPostProcessor作用于所有bean初始化前，它能够获取bean的配置信息，并能够在其初始化前修改这些配置。  
```java
public interface BeanFactoryPostProcessor {
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
```
BeanFactoryPostProcessor的作用域以及多个之间的执行顺序同BeanPostProcessor。  

#### 2.5.3 FactoryBean接口
FactoryBean接口用于创建对象自身的工厂Bean。如果一个对象的实例化过程很复杂，配置起来很繁琐，就可以通过FactoryBean来实现。像大家熟知的Mybatis SqlSessionFactory就是一个FactoryBean。
```java
public interface FactoryBean<T> {
	T getObject() throws Exception;
	Class<?> getObjectType();
	boolean isSingleton();
}
```
直接通过FactoryBean的id或name调用context.getBean()方法，返回的是工厂创建的对象。如果需要获取工厂对象本身，需要在id或name前拼接一个`$`。  

### 2.6 基于注解的配置
基于注解的配置相较于冗长的xml配置来说显得更加简洁。但是xml的配置方式对于代码来说是无侵入的，而注解需要修改源代码，并重新编译。二者可以单独使用也可以一起使用。通过注解实现的注入早于基于xml的配置，因此，如果两种方式作用在同一个Bean上，xml会覆盖注解的配置。  
使用注解的方式配置Bean，需要在xml中添加annotation-config的配置：  
```xml
<context:annotation-config/>
```
该标签默认注册了4个BeanPostProcessor：AutowiredAnnotationBeanPostProcessor、CommonAnnotationBeanPostProcessor、PersistenceAnnotationBeanPostProcessor以及RequiredAnnotationBeanPostProcessor，用于扫描并注册被指定注解标识的Bean。  
注意`<context:annotation-config/>`只在当前Context中生效。  

#### 2.6.1 @Required注解
@Required注解标识在属性的setter方法上，表示该Bean在初始化时必须依赖该属性，否则将会抛出BeanCreationException异常。  

#### 2.6.2 @Autowired和@Qualifier注解
@Autowired注解按类型自动完成依赖注入，可以标识在构造方法、普通方法、属性等元素上。除一般类型对象的注入外，也可以实现集合类型和Map类型对象的依赖注入。  
```java
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
	boolean required() default true;
}
```
注意：@Autowired, @Inject, @Resource和@Value注解功能均通过BeanPostProcessor的方式实现，因此在BeanPostProcessor和BeanFactoryPostProcessor实现中不能使用上述注解，必须通过XML或@Bean的方式配置依赖。  

##### 2.6.2.1 @Autowired普通注入
@Autowired注解默认情况下要求必须有且仅有一个候选Bean进行依赖注入，否则将抛出UnsatisfiedDependencyException异常，相当于@Required注解。可以调整@Autowired(required=false)兼容没有候选Bean存在的情况。    

##### 2.6.2.2 @Autowired注入集合
当对集合或数组类型对象进行依赖注入时，Spring会将所有满足类型条件的Bean全部注入进来，如果希望依赖的多个Bean是顺序的，这些Bean可以通过实现org.springframework.core.Ordered接口实现。  

##### 2.6.2.3 @Autowired注入Map
Spring同样支持以String类型为key的Map对象注入，其中key为Bean的名称，value为Bean对象。  

##### 2.6.2.4 @Qualifier注解精确匹配
上面提到@Autowired是按类型注入的，所以经常会匹配多个同类型的候选Bean。这时候就可以搭配@Qualifier精确匹配依赖。@Qualifier注解包含一个value参数，表示Bean的名称。默认情况下，Bean的name或id作为该Bean的qualifier，也可以在`<bean />`中使用`<qualifier />`指定，多个Bean可以拥有相同的qualifier，这样就可以将他们注入到集合中去。  

```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
	String value() default "";
}
```

#### 2.6.3 JSR-250注解
Spring同样支持Java标准注解，例如@Resource，@PostConstruct，@PreDestroy等。  
##### 2.6.3.1 @Resource注解
类似Spring的@Autowired注解，用于自动注入依赖。不同的是@Autowired默认按类型匹配依赖，而@Resource按Bean名称匹配依赖。  
- 当标识在属性上时，默认匹配与该属性相同的Bean
- 当标识在setter方法时，默认匹配与该方法对应属性名相同的Bean

@Resource装配顺序如下：
- 如果同时指定了name和type，则从Spring Context中找到唯一匹配的Bean进行装配，找不到则抛出异常；
- 如果指定了name，则从Spring Context中找到名称或id与之匹配的Bean进行装配，找不到则抛出异常；
- 如果指定了type，则从Spring Context中找到类型匹配的唯一Bean进行装配，找不到或找到多个，都会抛出异常；
- 如果既没有指定name，也没有指定type，则自动按照byName的方式进行装配，如果没有匹配，则回退为一个原始类型进行匹配。

##### @PostConstruct和@PreDestroy
见2.4.1 生命周期回调方法。  

### 2.7 类路径扫描和组件注解
上述的例子中多数还是使用XML的形式定义Bean，虽然引入了注解，例如：@Autowired、@Resource等，但注解也只是用于完成依赖注入的工作。  

#### 2.7.1 组件注解
Spring提供了类路径扫描的功能，查找被组件注解标识的Class，并自动完成Bean的注册。  这些组件注解包括@Component、@Repository、@Service、@Controller等。其中@Component是一个通用的注解，被标识的类将会作为SpringBean被Spring探查到。从功能上来说，@Repository、@Service、@Controller注解与@Component注解一样，但是前者体现了更多的语义信息：  
- @Repository: 用来标识DAO层的Bean
- @Service: 用来标识Service层的Bean
- @Controller: 用来标识Controller层的Bean
因此，推荐使用语义组件注解。  

#### 2.7.2 类路径扫描
类路径扫描的功能可以通过注解和XML配置的方式实现：  
注解方式使用@Configuration配合@ComponentScan注解实现：
```java
@Configuration
/**
 * 扫描并注册com.study.spring.core.ioc.classpath_scan及其子包中的组件类
 * 多个之间使用逗号、空格、分号等间隔
 */
@ComponentScan("com.study.spring.core.ioc.classpath_scan")
public class SpringConfig {
}
```

XML配置使用`<context:component-scan base-package="" />`标签扫描：  
```xml
<!-- 扫描com.study.spring.core.ioc.classpath_scan包及其子包下的组件类，多个之间用逗号、空格或分号间隔 -->
	<context:component-scan base-package="com.study.spring.core.ioc.classpath_scan" />
```
`<context:component-scan />`标签自动扫描和注册的功能是依赖`AutowiredAnnotationBeanPostProcessor`和`AutowiredAnnotationBeanPostProcessor`两个处理器实现的。此外，该标签的功能涵盖了上面用的到`<context:annotation-config/>`，只要使用`<context:component-scan />`就够了  


#### 2.7.3 扫描过滤
默认情况下，标识了@Component、@Repository、@Service、@Controller注解的类都会被扫描并注册为SpringBean。Spring提供了额外的过滤功能满足扫描特定组件的需要。  
过滤器类型分类：  

|过滤器类型|示例|注释|
|:-|:-|:-|
|annotation|org.example.SomeAnnotation|基于注解的匹配规则（默认）|
|assignable|org.example.SomeClass|基于类之间继承关系的匹配规则|
|aspectj|org.example..*Service+|基于切面的匹配规则|
|regex|org\.example\.Default.*|基于正则表达式的匹配规则|
|custom|org.example.MyTypeFilter|自定义匹配规则，实现org.springframework.core.type.TypeFilter接口|

使用注解的配置:  
```java
@Configuration
/**
 * 扫描org.example包及其子包下，类名满足.*Stub.*Repository正则匹配且非@标识的类
 */
@ComponentScan(basePackages = "org.example",
        includeFilters = @Filter(type = FilterType.REGEX, pattern = ".*Stub.*Repository"),
        excludeFilters = @Filter(Repository.class))
public class AppConfig {
    ...
}
```
使用xml的配置:  
```xml
<beans>
    <context:component-scan base-package="org.example">
        <context:include-filter type="regex"
                expression=".*Stub.*Repository"/>
        <context:exclude-filter type="annotation"
                expression="org.springframework.stereotype.Repository"/>
    </context:component-scan>
</beans>
```

#### 2.7.4 其他功能注解
Spring支持在组件Bean中定义其他Bean。例如：  
```java
@Component
public class FactoryMethodComponent {
    
	@Bean
    @Qualifier("public")
    public TestBean publicInstance() {
        return new TestBean("publicInstance");
    }
}
```
上面的组件注解中均包含name属性，用于配置当前类被Spring容器实例化后的Bean 名称，如果不配置，默认情况下，以类名首字母小写的形式表示。例如，该Bean的名称为movieFinderImpl：  
```java
@Component
public class MovieFinderImpl {
}
```
此外，组件注解还可以和@Scope、@Qualifiter注解搭配使用，分别用于配置Bean的作用域和检索名。  

#### 2.7.5 支持JSR330标准依赖注入注解
Spring3.0后支持JSR 330标准的依赖注入注解。首先需要引入依赖：  
```xml
<dependency>
    <groupId>javax.inject</groupId>
    <artifactId>javax.inject</artifactId>
    <version>1</version>
</dependency>
```
Spring注解与JSR330标准注解比较
|Spring注解|JSR330注解|区别与限制|
|:-|:-|:-|
|@Autowired|@Inject|@Inject没有required属性|
|@Component|@Named|-|
|@Scope("singleton")|@Singleton|JSR330默认作用域类似于Spring的prototype|
|@Qualifier|@Named|-|
|@Value|-|没有对应的注解|
|@Required|-|没有对应的注解|
|@Lazy|-|没有对应的注解|

### 2.8 基于JAVA的配置
#### 2.8.1 基本概念
Spring Java配置的核心在于两点，使用@Configuration标识类和使用@Bean标识方法。  
被@Bean注解标识的方法返回的对象将会被Spring IOC容器管理，即作为Spring Bean。其功能上类似于`<bean />`标签。@Bean注解可以放在任意@Component修饰的类中，但一般和@Configuration搭配使用。  
被@Configuration注解标识的类是Spring Bean定义的地方。其功能上类似于`<beans />`标签。  
例如：  
```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```
#### 2.8.2 AnnotationConfigApplicationContext
AnnotationConfigApplicationContext用于创建基于Java配置的Spring容器。它可以接收@Configuration标识的class对象，则配置类本身及其内部的@Bean标识的方法都将被注册为Spring Bean。此外，它还可以接收Spring组件注解，以及JSR330标准注解，在创建Spring Bean的同时，还会自动完成依赖注入。  
使用@ComponentScan开启组件自动扫描：  
```java
@Configuration
@ComponentScan(basePackages = "com.acme")
public class AppConfig  {
}
```
等同于：  
```xml
<beans>
    <context:component-scan base-package="com.acme"/>
</beans>
```
#### 2.8.3 @Bean注解
@Bean注解是一个方法级别的注解，等同于xml配置中的`<bean />`标签，用于配置Bean的定义。  
##### 2.8.3.1 声明一个Bean
```java
@Configuration
public class AppConfig {
    @Bean
    public Foo createFoo() {
        return new Foo();
    }
}
```
##### 2.8.3.2 配置Bean的声明周期回调方法
```java
@Configuration
public class AppConfig {
    @Bean(initMethod = "init")
    public Foo foo() {
        return new Foo();
    }
    @Bean(destroyMethod = "destroy")
    public Bar bar() {
        return new Bar();
    }
}
```
##### 2.8.3.3 结合@Scope注解配置Bean的作用域
```java
@Configuration
public class AppConfig {
    @Bean
	@Scope("prototype")
    public Foo foo() {
        return new Foo();
    }
}
```
##### 2.8.3.4 配置Bean的别名
```java
@Configuration
public class AppConfig {
    @Bean(name = { "dataSource", "subsystemA-dataSource", "subsystemB-dataSource" })
    public DataSource dataSource() {
        // instantiate, configure and return DataSource bean...
    }
}
```
#### 2.8.4 @Configuration注解
@Configuration注解是一个类级别注解，是定义Bean的地方，等同于xml中的`<beans />`标签。在类的内部可以定义多个公有的被@Bean注解修饰的方法。此外，还可以定义内部bean的依赖。  
##### 2.8.4.1 内部Bean的依赖注入
```java
@Configuration
public class AppConfig {
    @Bean
    public Bar bar() {
        return new Bar();
    }
    @Bean
    public Foo foo() {
        // 此处并非直接方法调用，而是在已注册的Bean中查找Bar
        return new Foo(bar());
    }
}
```
#### 2.8.5 Lookup方法注入
Lookup方法注入上面使用Xml的配置中已经提到过，适用于单例Bean依赖prototype Bean的场景。  
方法注入的核心是将对prototype的依赖放在方法中，Spring通过CGLIB创建的代理对象，在方法调用时，注入prototype依赖。  
```java
/**
* prototype bean
*/
public class PrototypeBean {
}

/**
* 单例bean
*/
public class SingletonBean {
    /**
     * 获取依赖的prototype bean方法
     * 该方法可以为抽象方法，不必实现它，后面会重写该方法
     * @return
     */
    public PrototypeBean getPrototypeBean() {
        // 此处永不会被调用
        System.out.println("call getPrototypeBean()");
        return null;
    }
}

@Configuration
public class LookupMethodAnnoConfig {

    @Bean
    @Scope("prototype")
    public PrototypeBean createPrototypeBean() {
        System.out.println("create a PrototypeBean");
        return new PrototypeBean();
    }
    
    
    @Bean
    public SingletonBean createSingletonBean() {
        return new SingletonBean() {
            @Override
            /**
             * 重写对prototype bean依赖的方法
             */
            public PrototypeBean getPrototypeBean() {
                // 此处并非直接方法调用，而是从IOC容器中获取Bean
                System.out.println("get prototypeBean");
                return createPrototypeBean();
            }
        };
    }
}
```
#### 2.8.6 混合使用Java和Xml配置
Spring提供了@Import注解用于多个配置类之间的引用，功能上类似于`<import />`标签。此外，还支持Java和Xml两种配置方式的混合使用。  
在xml中引入Java的配置可以使用`<context:annotation-config/>`搭配`<bean />`或`<context:component-scan/>`标签;在Java配置中引用Xml的配置使用@ImportResource注解。  
```xml
<beans>
    <!-- 识别@Configuration注解 -->
    <context:annotation-config/>
	<!--注册配置Bean-->
	<bean class="xxx.xxx.ConfigurationClass">
</beans>
<!--或者使用包扫描注册配置Bean-->
<beans>
    <!--自动扫描并注册配置Bean-->
    <context:component-scan base-package="xxx.xxx"/>
</beans>
```
```java
@Configuration
// 引入xml配置文件
@ImportResource("xxx.xml")
public class AppConfig {
}
```
#### 2.8.7 环境抽象
Spring集成了抽象的环境信息，主要包括profiles和properties两部分。profile是一组Bean定义的逻辑集合，适用于根据不同环境配置不同Beans的需要。properties主要是提供处理资源文件的能力。  

##### 2.8.7.1 Profiles
例如我们需要在不同的环境使用不用的数据源，那么就可以定义不同的Profiles来适应这种需求。  
```java
// 此处简单模拟数据源接口
public interface DataSource {
    /**
     * 获取数据源名称
     * @return
     */
    String getName();
}
// 不同环境下的实现
public class DevDataSource implements DataSource {
    @Override
    public String getName() {
        return "DevDS";
    }
}
public class PrdDataSource implements DataSource {
    @Override
    public String getName() {
        return "PrdDS";
    }
}
public class EmbedDataSource implements DataSource {
    @Override
    public String getName() {
        return "EmbedDS";
    }
}
```
使用Java方式的配置
```java
@Configuration
/**
* 配置默认环境
*/
@Profile("default")
public class DefaultAnnoJavaConfig {
    @Bean
    public DataSource getEmbedDataSource() {
        return new EmbedDataSource();
    }
}
@Configuration
@Import(DefaultAnnoJavaConfig.class)
public class AnnoJavaConfig {
    @Bean
	/**
	* dev 环境
	*/
    @Profile("dev")
    public DataSource getDevDataSource() {
        return new DevDataSource();
    }
    @Bean
	/**
	* prd 生产环境
	*/
    @Profile("prd")
    public DataSource getProductionDataSource() {
        return new PrdDataSource();
    }
}

/**
* 激活Profile
*/
    @Test
    /**
     * DEV 环境的数据源
     */
public void testDevDataSource() {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	// 激活dev环境
	context.getEnvironment().setActiveProfiles("dev");
	context.register(AnnoJavaConfig.class);
	context.refresh();
	DataSource ds = context.getBean(DataSource.class);
	Assert.assertNotNull(ds);
	Assert.assertTrue(ds instanceof DevDataSource);
	Assert.assertEquals("DevDS", ds.getName());
	context.close();
}
```
使用XML方式的配置
```xml
<!-- 默认profile -->
<beans profile="default">
	<bean id="dataSource"
		class="com.study.spring.core.ioc.javaconfig.environment.profile.EmbedDataSource" />
</beans>
<!-- dev 环境 -->
<beans profile="dev">
	<bean id="dataSource"
		class="com.study.spring.core.ioc.javaconfig.environment.profile.DevDataSource" />
</beans>
<!-- prd 环境 -->
<beans profile="prd">
	<bean id="dataSource"
		class="com.study.spring.core.ioc.javaconfig.environment.profile.PrdDataSource" />
</beans>
```
```java
/**
* 激活Profile
*/
/**
     * DEV 环境的数据源
     */
public void testDevDataSource() {
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-profile.xml");
	context.getEnvironment().setActiveProfiles("dev");
	context.refresh();
	DataSource ds = context.getBean(DataSource.class);
	Assert.assertNotNull(ds);
	Assert.assertTrue(ds instanceof DevDataSource);
	Assert.assertEquals("DevDS", ds.getName());
	context.close();
}
```
除了使用代码激活Profile外，还可以通过环境变量或ServletContext参数`spring.profiles.active`激活指定的Profile，默认的profile配置使用参数`spring.profiles.default`。  

##### 2.8.7.2 PropertySource
Spring针对key-value形式的资源使用统一的PropertySource接口来处理，主要用于获取JVM环境变量、系统变量以及自定义properties资源文件等。例如在StandardEnvironment中，资源属性的获取主要从JVM参数`System.getProperties()`和系统环境变量`System.getenv()`中获取，重复的键值，后置会覆盖前者。当然也可以使用`MutablePropertySources.addFirst()`方法配置优先级。  

##### 2.8.7.2.1 @PropertySource注解
@PropertySource注解提供了一种便利的、可声明式的方式配置Spring的Environment信息。  
```java
@Configuration
@PropertySource("/spring.env.propertysource.properties")
public class JavaAppConfig {
    @Autowired
    Environment env;
    @Bean
    public Foo getFoo() {
        Foo foo = new Foo();
        foo.setName(env.getProperty("env.propertysource.name"));
        return foo;
    }
}
```
也可以和@Value注解配合使用，完成自动注入
```java
@Configuration
@PropertySource("/spring.env.propertysource.properties")
@ComponentScan("com.study.spring.core.ioc.javaconfig.environment.propertysource")
public class JavaAppConfig {
}

@Component
public class Bar {

    @Value("${env.propertysource.address}")
    private String address;
}
```
等效的xml配置：  
```xml
<context:property-placeholder
    location="classpath:spring.env.propertysource.properties" />

<!-- 或者 -->
<!-- 
<bean
    class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
    <property name="locations" value="classpath:spring.env.propertysource.properties" />
</bean>
-->
```
此外，任何`${xxx}`格式的变量不管在哪定义，只要在Environment对象中能找到对应的值，都会生效。例如：  
```java
@PropertySource("classpath:/com/${my.placeholder:default/path}/app.properties")
```
```xml
<import resource="com/bank/service/${customer}-config.xml"/>
```

#### 2.8.8 ApplicationContext扩展
##### 2.8.8.1 MessageSource