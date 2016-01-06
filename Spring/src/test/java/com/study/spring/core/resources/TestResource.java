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
 */
public class TestResource {

	@Test
	public void test(){
		
	}
}
