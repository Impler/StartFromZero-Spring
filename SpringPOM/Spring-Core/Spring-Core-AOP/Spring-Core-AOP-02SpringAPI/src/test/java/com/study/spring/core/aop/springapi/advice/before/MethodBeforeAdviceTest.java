package com.study.spring.core.aop.springapi.advice.before;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.aop.springapi.advice.before.BarServiceImpl;
import com.study.spring.core.aop.springapi.advice.before.FooService;
import com.study.spring.core.aop.springapi.advice.before.IBarService;
import com.study.spring.core.aop.springapi.advice.before.KnownException;
import com.study.spring.core.aop.springapi.advice.before.KnownExceptionMethodBeforeAdvice;
import com.study.spring.core.aop.springapi.advice.before.MyMethodBeforeAdvice;
import com.study.spring.core.aop.springapi.advice.before.UnknownExceptionMethodBeforeAdvice;

public class MethodBeforeAdviceTest {

    @Test
    public void testBeforeByNativeCglib() throws IOException, KnownException {
        ProxyFactory proxyFactory = new ProxyFactory();
        FooService target = new FooService();
        // 设置目标对象
        proxyFactory.setTarget(target);
        // 设置advice
        proxyFactory.addAdvice(new MyMethodBeforeAdvice());
        // 没有配置接口，则采用CGLIB创建代理对象
        // 代理对象会对target中的每个方法都实施增强
        FooService proxy = (FooService) proxyFactory.getProxy();
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();
        exportDynamicClassFile(FooService.class, proxy);
    }

    @Test
    public void testBeforeByNativeJdk() {
        ProxyFactory proxyFactory = new ProxyFactory();
        IBarService target = new BarServiceImpl();
        proxyFactory.setTarget(target);
        // 配置接口，则采用JDK创建代理对象
        proxyFactory.setInterfaces(target.getClass().getInterfaces());
        proxyFactory.addAdvice(new MyMethodBeforeAdvice());
        IBarService proxy = (IBarService) proxyFactory.getProxy();
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();

    }

    @Test
    public void testBeforeByForceCglibOfOptimize() {
        ProxyFactory proxyFactory = new ProxyFactory();
        IBarService target = new BarServiceImpl();
        proxyFactory.setTarget(target);
        // 配置接口
        proxyFactory.setInterfaces(target.getClass().getInterfaces());
        proxyFactory.addAdvice(new MyMethodBeforeAdvice());
        // 启用优化，选择cglib代理
        proxyFactory.setOptimize(true);
        IBarService proxy = (IBarService) proxyFactory.getProxy();
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();

    }

    @Test
    public void testBeforeByForceCglibOfProxyTargetClass() {
        ProxyFactory proxyFactory = new ProxyFactory();
        IBarService target = new BarServiceImpl();
        proxyFactory.setTarget(target);
        // 配置接口
        proxyFactory.setInterfaces(target.getClass().getInterfaces());
        proxyFactory.addAdvice(new MyMethodBeforeAdvice());
        // 选择cglib代理
        proxyFactory.setProxyTargetClass(true);
        IBarService proxy = (IBarService) proxyFactory.getProxy();
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();

    }

    @Test
    public void testBeforeBySpringConfigOfFooService() throws KnownException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advice-before.xml");
        FooService proxy = (FooService) context.getBean("fooProxy");
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();
        context.close();
    }

    @Test
    public void testBeforeBySpringConfigOfBarService() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/beans-aop-springapi-advice-before.xml");
        IBarService proxy = (IBarService) context.getBean("barProxy");
        System.out.println("代理对象：" + proxy.getClass());
        proxy.save();
        context.close();
    }

    /**
     * 导出class文件
     * 
     * @param target
     * @param proxy
     * @throws IOException
     */
    private void exportDynamicClassFile(Class<?> target, Object proxy) throws IOException {
        @SuppressWarnings("restriction")
        byte[] bytes = sun.misc.ProxyGenerator.generateProxyClass("$Proxy", proxy.getClass().getInterfaces());
        String dir = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                + "java" + File.separator + target.getPackage().getName().replaceAll("\\.", "\\" + File.separator);
        File file = new File(dir, "$Proxy.class");
        System.out.println("代理类生成在:" + file.getPath());
        FileOutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.close();
    }
    
    @Test(expected=Exception.class)
    public void testUnknownExceptionInAdvice() {
        ProxyFactory proxyFactory = new ProxyFactory();
        FooService target = new FooService();
        // 设置目标对象
        proxyFactory.setTarget(target);
        // 设置advice
        proxyFactory.addAdvice(new UnknownExceptionMethodBeforeAdvice());
        FooService proxy = (FooService) proxyFactory.getProxy();
        System.out.println("代理对象：" + proxy.getClass());
        try {
            // 如果增强中抛出目标方法未知的异常，这里捕获不到
            proxy.save();
        } catch (KnownException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testKnownExceptionInAdvice() {
        ProxyFactory proxyFactory = new ProxyFactory();
        FooService target = new FooService();
        // 设置目标对象
        proxyFactory.setTarget(target);
        // 设置advice
        proxyFactory.addAdvice(new KnownExceptionMethodBeforeAdvice());
        FooService proxy = (FooService) proxyFactory.getProxy();
        System.out.println("代理对象：" + proxy.getClass());
        try {
            proxy.save();
        } catch (KnownException e) {
            e.printStackTrace();
        }
    }

}
