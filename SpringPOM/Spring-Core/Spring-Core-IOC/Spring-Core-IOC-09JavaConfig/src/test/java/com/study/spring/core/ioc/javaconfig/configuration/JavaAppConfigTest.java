package com.study.spring.core.ioc.javaconfig.configuration;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

public class JavaAppConfigTest {

    @Test
    public void test() {
       AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JavaAppConfig.class);
       Foo foo = context.getBean(Foo.class);
       Assert.notNull(foo);
       foo.foo();
       context.close();
    }

}
