package com.study.spring._05aop._00dynamic_proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

import net.sf.cglib.proxy.Enhancer;

import org.junit.Test;

import com.study.spring._05aop._00dynamic_proxy._01jdk.FooService;
import com.study.spring._05aop._00dynamic_proxy._01jdk.FooServiceImpl;
import com.study.spring._05aop._00dynamic_proxy._01jdk.FooServiceInvocationHandler;
import com.study.spring._05aop._00dynamic_proxy._02cglib.BarService;
import com.study.spring._05aop._00dynamic_proxy._02cglib.BarServiceMethodInterceptor;

public class DynamicProxyTest {

	@Test
	public void testJDK() throws IOException {
		FooService foo = new FooServiceImpl();
		FooServiceInvocationHandler handler = new FooServiceInvocationHandler(foo);
		
		FooService proxy = (FooService) Proxy.newProxyInstance(foo.getClass().getClassLoader(), foo.getClass().getInterfaces(), handler);
		
		proxy.doService();
		
		exportDynamicClassFile(FooService.class, proxy);
	}
	
	@Test
	public void testCglib() throws IOException{
		Enhancer en = new Enhancer();
		en.setSuperclass(BarService.class);
		en.setCallback(new BarServiceMethodInterceptor());
		BarService proxy = (BarService) en.create();
		proxy.doService();
		
		exportDynamicClassFile(BarService.class, proxy);
	}
	
	/**
	 * 导出class文件
	 * @param target
	 * @param proxy
	 * @throws IOException
	 */
	public void exportDynamicClassFile(Class<?> target, Object proxy) throws IOException{
		@SuppressWarnings("restriction")
		byte[] bytes = sun.misc.ProxyGenerator.generateProxyClass("$Proxy", proxy.getClass().getInterfaces());
		String dir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + target.getPackage().getName().replaceAll("\\.", "\\" + File.separator);
		File file = new File(dir, "$Proxy.class");
		System.out.println("代理类生成在:" + file.getPath());
		FileOutputStream out = new FileOutputStream(file);
		out.write(bytes);
		out.close();
	}

}
