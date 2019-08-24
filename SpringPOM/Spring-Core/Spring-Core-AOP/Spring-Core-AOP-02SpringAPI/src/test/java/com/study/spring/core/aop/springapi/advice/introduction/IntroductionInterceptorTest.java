package com.study.spring.core.aop.springapi.advice.introduction;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;

import com.study.spring.core.aop.springapi.advice.introduction.FooService;
import com.study.spring.core.aop.springapi.advice.introduction.IServiceBar;
import com.study.spring.core.aop.springapi.advice.introduction.IServiceFoo;
import com.study.spring.core.aop.springapi.advice.introduction.MyIntroductionAdvice;


public class IntroductionInterceptorTest {

    @Test
    public void test() throws IOException {
        ProxyFactory factory = new ProxyFactory();
        // 新建目标对象
        IServiceFoo target = new FooService();
        factory.setTarget(target);
        // 配置引介增强
        factory.addAdvice(new MyIntroductionAdvice());
        // 必须使用基于继承的代理方式
        factory.setProxyTargetClass(true);
        // 创建代理对象
        Object proxy = factory.getProxy();
        Assert.assertTrue(proxy instanceof IServiceFoo);
        ((IServiceFoo)proxy).doFoo();
        Assert.assertTrue(proxy instanceof IServiceBar);
        ((IServiceBar)proxy).doBar();
        exportDynamicClassFile(proxy.getClass(), proxy);
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
}
