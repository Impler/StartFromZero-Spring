package com.study.spring.core.resources;

import org.junit.Test;

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
 * Resource template = ctx.getResource("file:///some/resource/path/myTemplate.txt");将返回FileSystemResource对象，而不论application context是何种类型
 * Resource template = ctx.getResource("http://myhost.com/resource/path/myTemplate.txt");将返回UrlResource对象，而不论application context是何种类型
 * 
 * ResourceLoaderAware接口
 * ResourceLoaderAware是一个标识性接口，表示该对象需要一个ResourceLoader的引用
 * public interface ResourceLoaderAware {
 * 		void setResourceLoader(ResourceLoader resourceLoader);
 * }
 * application context会自动调用受spring托管的实现了ResourceLoader接口的bean的setResourceLoader方法，将自己注入进去。因为application context 也是ResourceLoader的实例
 * 
 */
public class TestResource {

	@Test
	public void test(){
		
	}
}
