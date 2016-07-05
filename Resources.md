# StartFromZero-Spring-Core
##Resources
为了弥补java.net.URL类在访问ClassPath、ServletContext等特定路径下资源能力的不足，Spring提供了org.springframework.core.io.Resource接口，支持更加强大的资源访问能力。  
###Resource接口
```java
public interface InputStreamSource {
	// 定位和打开资源，每次调用返回新的流对象，所以每次调用完需及时关闭
    InputStream getInputStream() throws IOException;

}
public interface Resource extends InputStreamSource {

    boolean exists();
	//不可以多次读，读取完需关闭
    boolean isOpen();

    URL getURL() throws IOException;

    File getFile() throws IOException;

    Resource createRelative(String relativePath) throws IOException;

    String getFilename();
	// 一般用于出错信息显示，通常返回文件的全路径名或真实的URL
    String getDescription();

}

```

Spring提供了多个Resource接口的实现：  
- UrlResource：包装了java.net.URL，一般用于访问所有可以通过URL访问的资源，如文件，HTTP资源，FTP资源等。每种URL都可以用标准的前缀表示：  
	- file: 用于访问文件系统资源
	- http:	用于通过http协议访问资源
	- ftp: 用于通过ftp协议访问资源
	- ...
如果路径中没有包含可识别的前缀（如：classpath），默认使用UrlResource
- ClassPathResource：根据指定的classloader访问classpath下的资源。路径中包好classpath前缀
- FileSystemResource：访问文件系统资源
- ServletContextResource：访问ServletContext相对路径下的资源
- InputStreamResource：访问指定的InputStream资源，不可多次读。只有在没有其他指定的Resource实现类时使用，一般首选ByteArrayResource或其他基于文件的实现
- ByteArrayResource：访问字节数组资源

###ResourceLoader接口
ResourceLader接口用于返回不同类型的Resource实例。  
```java
public interface ResourceLoader {

    Resource getResource(String location);
}
```
所有的Application Context都实现了ResourceLoader接口，所以可以通过Application Context获取Resource实例。  
当调用ApplicationContext的getResource方法时，如果路径没有指定前缀，将会根据当前的ApplicationContext的类型返回响应的Resource；如果指定了前缀则以前缀为准。例如：  
```java
Resource res1 = ctx.getResource("some/resource/path/myTemplate.txt");
Resource res2 = ctx.getResource("classpath:some/resource/path/myTemplate.txt");
```
如果ctx为ClassPathXmlApplicationContext，res1为ClassPathResource，res2为ClassPathResource；如果ctx为FileSystemXmlApplicationContext，res1为FileSystemResource，res2还是ClassPathResource。  
路径前缀与Resource之间的对应关系  

|前缀|例子|解释|
|:-|:-|:-|
|classpath:|classpath:com/myapp/config.xml|访问classpath下的资源|
|file:|file:///data/config.xml|通过URL访问文件系统资源|
|http:|http://myserver/logo.png|通过URL访问资源|
|无|/data/config.xml|依赖于ApplicationContext类型|

###ResourceLoaderAware接口
ResourceLoaderAware是一个标识性接口，表示该对象需要传递一个ResourceLoader的引用。  
```java
public interface ResourceLoaderAware {

    void setResourceLoader(ResourceLoader resourceLoader);
}
```
上面提到，所有的ApplicationContext都实现了ResourceLoader接口，所以，当一个实现了ResourceLoaderAware接口的类受Spring管理时，Spring Application Context会自动调用该类的`setResourceLoader`并把自己传递过去。  

###Resources的依赖注入
如果一个类中包含Resource属性，且该类又需要注册为Spring Bean，则只要为Resource属性提供字符串路径信息即可，Spring会自动将Resource路径转化为相应的Resource对象。例如mybean中包含名为template的Resource属性：  
```xml
<bean id="myBean" class="...">
    <property name="template" value="some/resource/path/myTemplate.txt"/>
</bean>
```
template具体的Resource类型基于上面讲的路径前缀或当前Application Context类型。  

###构建ApplicationContext

###资源路径中通配符的使用

###关于FileSystemResource的注意点