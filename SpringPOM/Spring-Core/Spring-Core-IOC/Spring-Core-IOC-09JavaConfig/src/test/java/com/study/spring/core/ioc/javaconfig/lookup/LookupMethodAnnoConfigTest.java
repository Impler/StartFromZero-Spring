package com.study.spring.core.ioc.javaconfig.lookup;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LookupMethodAnnoConfigTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LookupMethodAnnoConfig.class);
        SingletonBean singletonBean = context.getBean(SingletonBean.class);
        PrototypeBean pro1 = singletonBean.getPrototypeBean();
        PrototypeBean pro2 = singletonBean.getPrototypeBean();
        Assert.assertNotEquals(pro1, pro2);
        context.close();
    }

}
