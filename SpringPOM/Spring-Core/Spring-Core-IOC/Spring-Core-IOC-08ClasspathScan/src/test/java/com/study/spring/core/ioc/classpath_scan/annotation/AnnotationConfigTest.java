package com.study.spring.core.ioc.classpath_scan.annotation;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.spring.core.ioc.classpath_scan.component.UserController;

public class AnnotationConfigTest {

    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = context.getBean(UserController.class);
        userController.save();
        context.close();
    }

}
