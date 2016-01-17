package com.study.spring.core.resources;

/**
 * @author Impler
 * @date 2016-01-06
 * 
 * 为了弥补java.net.URL类在访问低级别资源上能力的不足，如Classpath下的资源或ServletContext下的资源，Spring提供了org.springframework.core.io.Resource接口来担任访问资源的角色。
 * Spring提供了多个Resource接口的实现
 * - UrlResource 包装了java.net.URL，用于访问所有可以通过URL访问的资源，如文件，HTTP资源，FTP资源等。
 * 		每种URL都可以用标准的前缀表示：
 * 		- file: 用于访问文件系统资源
 * 		- http:	用于通过http协议访问资源
 * 		- ftp: 用于通过ftp协议访问资源
 * 		- ...
 * - ClassPathResource 根据指定的classloader访问classpath下的资源
 * - FileSystemResource 访问文件系统资源
 * - ServletContextResource 访问ServletContext相对路径下的资源
 * - InputStreamResource 访问指定的InputStream资源，只有在没有其他指定的Resource实现类时使用，一般首选ByteArrayResource或其他实现
 * - ByteArrayResource 访问指定的字节数组资源
 * 
 * ResourceLoader接口
 * ResourceLoader接口的实现用于返回Resource实例，所有的application context都实现了该接口，所以可以从application context中获取Resource实例
 * 当从一个application context中调用getResource()方法，如果资源路径没有显示指定类型，将返回与当前context一致的Resource对象，
 * 例如：Resource template = ctx.getResource("some/resource/path/myTemplate.txt");
 * 当ctx为ClassPathXmlApplicationContext实例时，将返回ClassPathResource对象，同样的，如果ctx为FileSystemXmlApplicationContext实例，将返回FileSystemResource对象
 * 你也可以指定具体的资源路径来获取资源
 * 例如：
 * Resource template = ctx.getResource("classpath:some/resource/path/myTemplate.txt");将返回ClassPathResource对象，而不论application context是何种类型
 * Resource template = ctx.getResource("file:///some/resource/path/myTemplate.txt");将返回UrlResource对象，而不论application context是何种类型
 * Resource template = ctx.getResource("http://myhost.com/resource/path/myTemplate.txt");将返回UrlResource对象，而不论application context是何种类型
 * 
 * ResourceLoaderAware接口
 * ResourceLoaderAware是一个标识性接口，表示该对象需要一个ResourceLoader的引用
 * public interface ResourceLoaderAware {
 * 		void setResourceLoader(ResourceLoader resourceLoader);
 * }
 * application context会自动调用受spring托管的实现了ResourceLoader接口的bean的setResourceLoader方法，将自己注入进去。因为application context 也是ResourceLoader的实例
 * 
 * spring的资源路径可以是明确指定的资源，也可以是含有classpath*:前缀或Ant风格的表达式
 * classpath*:前缀
 * 与classpath:前缀对应的，classpath*:前缀会扫描classpath下多个Jar包或文件，并将扫描到的多个资源组合在一起。该前缀适用于分模块打包的应用，每个模块单独打jar包，classpath*:可以扫描到位于不同jar包内的配置文件
 * 
 * Ant风格资源地址
 * 3种匹配符：
 * ?: 匹配文件名中的一个字符
 * *: 匹配文件名中的任意个字符
 * **: 匹配多层路径
 * 例如:
 * classpath:com/t?st.xml匹配classpath:com/test.xml和classpath:com/tast.xml
 * file:D:/config/*.xml匹配config目录下所有xml格式文件
 * classpath:com/** /test.xml匹配com类路径及子孙路径下的test.xml
 * 
 * FileSystemResource 相对路径与绝对路径
 * FileSystemResource 将资源路径当做相对于当前工作目录的相对路径对待，不管路径是否以/开头，如果想要使用相对于根路径的绝对路径，最好使用file:前缀返回UrlResource
 * 例如：
 * FileSystemXmlApplicationContext ctx = ...;
 * ctx.getResource("some/resource/path/myTemplate.txt");
 * 与下面的配置结果是一样的
 * FileSystemXmlApplicationContext ctx = ...;
 * ctx.getResource("/some/resource/path/myTemplate.txt");
 */
public class TestResource {
}