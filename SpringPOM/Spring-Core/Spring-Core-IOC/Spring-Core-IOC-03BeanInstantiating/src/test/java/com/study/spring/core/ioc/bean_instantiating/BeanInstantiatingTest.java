package com.study.spring.core.ioc.bean_instantiating;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.core.ioc.bean_instantiating.Foo.Bar;

public class BeanInstantiatingTest {

    private ClassPathXmlApplicationContext context;
    
    @Before
    public void before(){
        context = new ClassPathXmlApplicationContext("beans-instantiating.xml");
    }
    
    @After
    public void after(){
        if(null != context){
            context.close();
        }
    }
    
    @Test
    public void test(){
       // instantiating by call default constructor method
       Foo f1 = (Foo) context.getBean("foo1");
       System.out.println(f1);
       
       // instantiating by call constructor with parameters method
       Foo f2 = (Foo) context.getBean("foo2");
       System.out.println(f2.getName());
       
       // instantiating by common factory method
       Foo f3 = (Foo) context.getBean("foo3");
       System.out.println(f3);
       
       // instantiating by static factory method
       Foo f4 = (Foo) context.getBean("foo4");
       System.out.println(f4);
       
       // instantiating static inner class
       Bar bar = (Bar) context.getBean("bar");
       System.out.println(bar);
    }
}
