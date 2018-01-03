package com.study.spring.aop.dynamic_proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

import org.junit.Test;

import com.study.spring.aop.dynamic_proxy.jdk.FooServiceImpl;
import com.study.spring.aop.dynamic_proxy.cglib.BarService;
import com.study.spring.aop.dynamic_proxy.cglib.CglibMethodInterceptor;
import com.study.spring.aop.dynamic_proxy.jdk.FooInvocationHandler;
import com.study.spring.aop.dynamic_proxy.jdk.FooService;

public class DaoInvocationHandlerTest {

    @Test
    public void testJdkProxy() throws IOException {
        FooService foo = new FooServiceImpl();
        FooInvocationHandler handler = new FooInvocationHandler(foo);
        FooService proxy = (FooService) Proxy.newProxyInstance(foo.getClass().getClassLoader(), foo.getClass().getInterfaces(), handler);
        proxy.save();
        exportDynamicClassFile(foo.getClass(), proxy);
    }

    
    @Test
    public void testCglib() throws IOException {
        CglibMethodInterceptor methodInterceptor = new CglibMethodInterceptor();
        BarService proxy = methodInterceptor.getProxy(BarService.class);
        proxy.save();
        exportDynamicClassFile(BarService.class, proxy);
    }
    
    /**
     * 导出class文件
     * @param target
     * @param proxy
     * @throws IOException
     */
    private void exportDynamicClassFile(Class<?> target, Object proxy) throws IOException{
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
