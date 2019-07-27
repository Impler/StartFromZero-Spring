package com.study.spring.core.ioc.javaconfig.annotationconfigapplicationcontext;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaAppConfigTest {

    @Test
    public void testSimple() {
        // 将@Configuration标识的class作为入参，常见Spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaAppConfig.class);
        Foo foo = context.getBean(Foo.class);
        Assert.assertNotNull(foo);
        context.close();
    }

    @Test
    public void testConfigClassAndComponetClassInput() {
        // 将@Configuration标识的class和Spring组件注解标识的class作为入参，创建Spring容器
        // 自动完成依赖注入
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaAppConfig.class,
                FooService.class);
        FooService fooService = context.getBean(FooService.class);
        fooService.foo();
        Assert.assertNotNull(fooService.getBarService());
        context.close();
    }
    
    @Test
    public void testRegist() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 通过编程一步步注册配置类的方式实例化Spring容器
        context.register(JavaAppConfig.class);
        context.refresh();
        Foo foo = context.getBean(Foo.class);
        Assert.assertNotNull(foo);
        context.close();
    }

    
    @Test
    public void testAutoScanByAnnotation() {
        //  启用自动扫描
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AutoScanJavaAppConfig.class);
        FooService fooService = context.getBean(FooService.class);
        fooService.foo();
        Assert.assertNotNull(fooService.getBarService());
        context.close();
    }
    
    @Test
    public void testAutoScanByProgram() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.study.spring.core.ioc.javaconfig.annotationconfigapplicationcontext");
        context.refresh();
        FooService fooService = context.getBean(FooService.class);
        fooService.foo();
        Assert.assertNotNull(fooService.getBarService());
        context.close();
    }
}
